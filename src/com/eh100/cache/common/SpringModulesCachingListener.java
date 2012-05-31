package com.eh100.cache.common;



import java.io.Serializable;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.Logger;
import org.springmodules.cache.CachingModel;
import org.springmodules.cache.interceptor.caching.CachingListener;

import com.engin.adaptors.CommonParser;

/**                                                                                                                                    
 *Purpose:                                                                    
 *@version $Id: SpringModulesCachingListener.java,v 1.2 2009/03/06 05:36:18 lixia Exp $                                                          
 *@author xiali2
 *@since 2009-3-5 
 */
public class SpringModulesCachingListener implements CachingListener {

	private static Logger logger = Logger.getLogger(SpringModulesCachingListener.class);
		
	
	/* (non-Javadoc)
     * @see org.springmodules.cache.interceptor.caching.CachingListener#onCaching(java.io.Serializable, java.lang.Object, org.springmodules.cache.CachingModel)
     */
    public void onCaching(Serializable serializable, Object obj,
            CachingModel model) {
        logger.info("put object to cache context...");
        logger.info("->serializable=" + serializable);
        logger.info("->object=" + obj);
        logger.info("->model=" + model);
    }
}
