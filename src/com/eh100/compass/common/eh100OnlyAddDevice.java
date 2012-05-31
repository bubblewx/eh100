package com.eh100.compass.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;

import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.gps.CompassGpsException;
import org.compass.gps.device.jdbc.JdbcGpsDeviceException;
import org.compass.gps.device.jdbc.JdbcUtils;
import org.compass.gps.device.jdbc.ResultSetJdbcGpsDevice;
import org.compass.gps.device.jdbc.ResultSetRowMarshallHelper;
import org.compass.gps.device.jdbc.AbstractJdbcGpsDevice.IndexExecution;
import org.compass.gps.device.jdbc.mapping.ResultSetToResourceMapping;
import org.compass.gps.device.jdbc.snapshot.CreateAndUpdateSnapshotEvent;
import org.compass.gps.device.jdbc.snapshot.DeleteSnapshotEvent;
import org.compass.gps.device.jdbc.snapshot.JdbcAliasRowSnapshot;
import org.compass.gps.device.jdbc.snapshot.JdbcAliasSnapshot;
import org.compass.gps.device.jdbc.snapshot.JdbcSnapshot;

public class eh100OnlyAddDevice extends ResultSetJdbcGpsDevice{

	
	

	  

	/**
     * Performs the data change mirroring operation.
     */
    public synchronized void performMirroring() throws JdbcGpsDeviceException {
        if (!shouldMirrorDataChanges() || isPerformingIndexOperation()) {
            return;
        }
        if (this.getJdbcSnapshot() == null) {
            throw new IllegalStateException(
                    buildMessage("Versioning data was not properly initialized, did you index the device or loaded the data?"));
        }
        Connection connection = JdbcUtils.getConnection(dataSource);
        PreparedStatement ps = null;
        ResultSet rs = null;
        boolean dirtySnapshot = false;
        try {
            for (Iterator it = mappings.iterator(); it.hasNext();) {
                ResultSetToResourceMapping mapping = (ResultSetToResourceMapping) it.next();
                if (!mapping.supportsVersioning()) {
                    continue;
                }
                JdbcAliasSnapshot oldAliasSnapshot = this.getJdbcSnapshot().getAliasSnapshot(mapping.getAlias());
                if (oldAliasSnapshot == null) {
                    log.warn(buildMessage("No snapshot for alias [" + mapping.getAlias()
                            + "] even though there should be support for versioning ignoring the alias"));
                    continue;
                }
                JdbcAliasSnapshot newAliasSnapshot = new JdbcAliasSnapshot(mapping.getAlias());
                ArrayList createdRows = new ArrayList();
                ArrayList updatedRows = new ArrayList();
                if (log.isDebugEnabled()) {
                    log.debug(buildMessage("Executing version query [" + mapping.getVersionQuery() + "]"));
                }
                ps = connection.prepareStatement(mapping.getVersionQuery());
                if (getFetchSize() > 0) {
                    ps.setFetchSize(getFetchSize());
                }
                rs = ps.executeQuery();
                while (rs.next()) {

                    if (log.isDebugEnabled()) {
                        StringBuffer sb = new StringBuffer();
                        sb.append(buildMessage("Version row with values "));
                        ResultSetMetaData metaData = rs.getMetaData();
                        for (int i = 1; i <= metaData.getColumnCount(); i++) {
                            sb.append("[").append(metaData.getColumnName(i)).append(":");
                            String value = rs.getString(i);
                            if (rs.wasNull()) {
                                value = "(null)";
                            }
                            sb.append(value);
                            sb.append("] ");
                        }
                        log.debug(sb.toString());
                    }

                    JdbcAliasRowSnapshot newRowSnapshot = new JdbcAliasRowSnapshot();
                    ResultSetRowMarshallHelper marshallHelper = new ResultSetRowMarshallHelper(mapping, dialect,
                            newRowSnapshot, compassGps.getMirrorCompass());
                    marshallHelper.marshallResultSet(rs);

                    // new and old have the same ids
                    JdbcAliasRowSnapshot oldRowSnapshot = oldAliasSnapshot.getRow(newRowSnapshot);

                    // new row or updated row
                    if (oldRowSnapshot == null) {
                        createdRows.add(newRowSnapshot);
                    } else if (oldRowSnapshot.isOlderThan(newRowSnapshot)) {
                        updatedRows.add(newRowSnapshot);
                    }

                    newAliasSnapshot.putRow(newRowSnapshot);
                }

                if (!createdRows.isEmpty() || !updatedRows.isEmpty()) {
                    dirtySnapshot = true;
                    getSnapshotEventListener().onCreateAndUpdate(
                            new CreateAndUpdateSnapshotEvent(connection, dialect, mapping, createdRows, updatedRows,
                                    compassGps));
                }

                this.getJdbcSnapshot().putAliasSnapshot(newAliasSnapshot);
            }
        } catch (SQLException e) {
            throw new JdbcGpsDeviceException(buildMessage("Failed while mirroring data changes"), e);
        } finally {
            JdbcUtils.closeResultSet(rs);
            JdbcUtils.closeStatement(ps);
            JdbcUtils.closeConnection(connection);
        }
        if (isSaveSnapshotAfterMirror() && dirtySnapshot) {
            getSnapshotPersister().save(this.getJdbcSnapshot());
        }
    }


}
