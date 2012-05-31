package com.eh100.compass.common;

import java.util.ArrayList;
import java.util.List;

import org.compass.core.CompassHit;
import org.compass.core.CompassHits;
import org.compass.core.Property;
import org.compass.core.support.search.CompassSearchResults;

import com.eh100.entity.BookHotVo;
import com.eh100.entity.BookMenuVo;
import com.eh100.entity.BookVo;
import com.eh100.util.DateUtil;

public class Eh100CompassUtils {

	
	public static List<BookVo> buildVOFromCompassHits(final CompassSearchResults results  )

	{

	        List<BookVo> valueObjectList = new ArrayList<BookVo>();	 
	        
	        CompassHit[]  hits =  results.getHits();

	        for (int i = 0; i < hits.length; i++)

	        {
	        
	        	BookVo singleResult = new BookVo();

	            singleResult.setAuthor(getSafeString(hits[i], "author"));
	            singleResult.setBookName(getSafeString(hits[i], "bookName"));
	            singleResult.setChapterName(getSafeString(hits[i], "chapter"));
	            singleResult.setChapterUrl(getSafeString(hits[i], "chapterUrl"));
	            singleResult.setTargetSiteUrl(getSafeString(hits[i], "targetSiteUrl"));
	            singleResult.setTargetSiteName(getSafeString(hits[i], "targetSiteName"));
	            
	            singleResult.setCreateDate(DateUtil.parse(getSafeString(hits[i], "createDate"), "yyyy-MM-dd"));
	            
	            singleResult.setId(Long.valueOf(hits[i].getResource().getId()));
	            singleResult.setOrderSequence(getSafeString(hits[i], "orderSequence"));
	            valueObjectList.add(singleResult);

	        }
	        	return valueObjectList;
	}

	
	
	public static List<BookMenuVo> buildMenuVOFromCompassHits(final CompassSearchResults results  )

	{

	        List<BookMenuVo> valueObjectList = new ArrayList<BookMenuVo>();	 
	        
	        CompassHit[]  hits =  results.getHits();

	        for (int i = 0; i < hits.length; i++)

	        {
	        
	        	BookMenuVo singleResult = new BookMenuVo();

	            singleResult.setAuthor(getSafeString(hits[i], "author"));
	            singleResult.setBookName(getSafeString(hits[i], "bookName"));
	            singleResult.setChapterName(getSafeString(hits[i], "chapter"));
	             
	            String hotBookId = getSafeString(hits[i], "hotBookId");
	            
	            if(!"".equals(hotBookId)){
	            	singleResult.setHotBookId(Long.valueOf(getSafeString(hits[i], "hotBookId"))); 
	            }
	            singleResult.setCreateDate(DateUtil.parse(getSafeString(hits[i], "createDate"), "yyyy-MM-dd"));
	            
	            singleResult.setId(Long.valueOf(hits[i].getResource().getId()));
	            singleResult.setOrderSequence(getSafeString(hits[i], "orderSequence"));
	            valueObjectList.add(singleResult);

	        }
	        	return valueObjectList;
	}
	
	
	public static String getSafeString(final CompassHit hit, final String propName)

	{

	        Property property = hit.getResource().getProperty(propName);

	        if (property != null)

	        {

	               return property.getStringValue();

	        }

	        return "";

	}
	 
	

	
}
