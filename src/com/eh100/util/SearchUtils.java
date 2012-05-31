/**
 *Project Name:CQUC RSNP
 *Confidential and Proprietary                                                                
 *Copyright 2008 By                                                                                     
 *Hewlett-Packard Development Company, L.P. 	              
 *All Rights Reserved                                                                                                                                                                                                                       
 */
package com.eh100.util;

/**                                                                                                                                    
 *Purpose:                                                                    
 *@version $Id: SearchUtils.java,v 1.1 2009/03/11 08:31:04 ruimin Exp $                                                          
 *@author ruimin
 *@since 2009-3-11 
 */
public class SearchUtils {
    public static String replaceOracleKeyWords(String org) {
        String ret = org.replace("\\", "\\\\");
        ret = ret.replace("_", "\\_");
        ret = ret.replace("%", "\\%");
        return ret;
    }
}
