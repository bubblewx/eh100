/**
 *Project Name:CQUC RSNP
 *Confidential and Proprietary                                                                
 *Copyright 2008 By                                                                                     
 *Hewlett-Packard Development Company, L.P. 	              
 *All Rights Reserved                                                                                                                                                                                                                       
 */
package com.eh100.util;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**                                                                                                                                    
 *Purpose:                                                                    
 *@version $Id: BusinessUtil.java,v 1.3 2009/05/13 08:29:48 ruimin Exp $                                                          
 *@author ruimin
 *@since 2009-3-27 
 */
public class BusinessUtil {

    /**
     * convert universityCode from 5 chars to 9 chars                        
     * @param universityCode
     * @return
     * @author ruimin
     * @since  2009-3-27 
     */
    public static String convertUNCodeFromSSO(String universityCode) {
        // return "UPI-" + universityCode.trim();
        return universityCode;
    }

    /**
     * convert universityCode from 9 chars to 5 chars                        
     * @param universityCode
     * @return
     * @author ruimin
     * @since  2009-3-27 
     */
    public static String convertUNCodeToSSO(String universityCode) {
        // return StringUtils.right(universityCode.trim(), 5);
        return universityCode;

    }

    /**
     * get years list for html select element
     * @return List<String> years
     * @author ruimin
     * @since  2009-4-14
     */
    public static List<String> getConfigSelectedYears() {
        List<String> ret = new ArrayList<String>();
        Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        for (int i = year; i > year - 10; i--) {
            ret.add(String.valueOf(i));
        }
        return ret;
    }

}
