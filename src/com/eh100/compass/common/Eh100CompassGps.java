package com.eh100.compass.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.compass.core.Compass;
import org.compass.core.CompassCallback;
import org.compass.core.CompassCallbackWithoutResult;
import org.compass.core.CompassException;
import org.compass.core.CompassSession;
import org.compass.core.CompassTemplate;
import org.compass.core.Resource;
import org.compass.core.spi.InternalCompassSession;
import org.compass.gps.device.jdbc.JdbcGpsDeviceException;
import org.compass.gps.device.jdbc.JdbcUtils;
import org.compass.gps.device.jdbc.ResultSetRowMarshallHelper;
import org.compass.gps.device.jdbc.mapping.TableToResourceMapping;
import org.compass.gps.device.jdbc.snapshot.JdbcAliasRowSnapshot;
import org.compass.gps.impl.SingleCompassGps;

public class Eh100CompassGps extends SingleCompassGps{
	
	
	 private Compass compass;
	 
	 private TableToResourceMapping bookVoMapping;
	 
	 private TableToResourceMapping bookMenuVoMapping;
	 
	 
	 
	 public TableToResourceMapping getBookVoMapping() {
		return bookVoMapping;
	}



	public void setBookVoMapping(TableToResourceMapping bookVoMapping) {
		this.bookVoMapping = bookVoMapping;
	}



	public TableToResourceMapping getBookMenuVoMapping() {
		return bookMenuVoMapping;
	}



	public void setBookMenuVoMapping(TableToResourceMapping bookMenuVoMapping) {
		this.bookMenuVoMapping = bookMenuVoMapping;
	}



	public Compass getCompass() {
		return compass;
	}



	public void setCompass(Compass compass) {
		this.compass = compass;
	}



//	public void executeForSaveIndex(CompassCallback callback) throws CompassException{
//		 CompassTemplate compassTemplate = new CompassTemplate(compass);
//		 
//		
//		 new CompassCallbackWithoutResult() {
//	            protected void doInCompassWithoutResult(CompassSession session) throws CompassException {
//	            	
//	            	  Connection connection = JdbcUtils.getConnection(dataSource);
//	                String query = "select * from ";
//	                PreparedStatement ps = null;
//	                try {
//	                    ps = createAndUpdateSnapshotEvent.getConnection().prepareStatement(query);
//	                    for (Iterator it = snapshots.iterator(); it.hasNext();) {
//	                        JdbcAliasRowSnapshot rowSnapshot = (JdbcAliasRowSnapshot) it.next();
//	                        Resource resource = ((InternalCompassSession) session).getCompass().getResourceFactory().createResource(bookVoMapping.getAlias());
//	                        ResultSetRowMarshallHelper marshallHelper = new ResultSetRowMarshallHelper(bookVoMapping, session,
//	                                dialect, resource);
//	                        ps.clearParameters();
//	                        List ids = rowSnapshot.getIds();
//	                        for (int i = 0; i < ids.size(); i++) {
//	                            String idValue = (String) ids.get(i);
//	                            dialect.setParameter(ps, i + 1, idValue);
//	                        }
//	                        ResultSet rs = ps.executeQuery();
//	                        if (!rs.next()) {
//	                            // it was deleted between the calls, do nothing
//	                            continue;
//	                        }
//	                        marshallHelper.marshallResultSet(rs);
//	                        if (useCreate) {
//	                            session.create(resource);
//	                        } else {
//	                            session.save(resource);
//	                        }
//	                        session.evictAll();
//	                    }
//	                } catch (SQLException e) {
//	                    throw new JdbcGpsDeviceException("Failed to execute query for create/update", e);
//	                } finally {
//	                    JdbcUtils.closeStatement(ps);
//	                }
//	            }
//	        }
//	 }
}
