// $Id: CollectionUtil.java,v 1.3 2009/05/13 09:17:45 wanxing Exp $
package com.eh100.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class CollectionUtil {

    private static Logger logger = Logger.getLogger(CollectionUtil.class);

    /**
     * combine property value of elements in collection, return a String List
     * 
     * @param collection
     * @param properties
     * @param delim
     * @return
     */
    public static List combineProperties(Collection collection,
            String[] properties, String delim) {
        List result = new ArrayList();
        for (Iterator iter = collection.iterator(); iter.hasNext();) {
            Object bean = iter.next();
            String element = "";
            for (int i = 0; i < properties.length; i++) {
                if (i > 0) {
                    element += delim;
                }
                Object value = BeanUtils.getProperty(bean, properties[i]);
                if (value != null) {
                    element += value;
                }
            }
            result.add(element);
        }
        return result;
    }

    /**
     * generate readable String presentation of an array
     * 
     * @param array
     * @param property
     * @param quot
     * @param delim
     * @return
     */
    public static String toString(Object[] array, String property, String quot,
            String delim) {
        return toString(Arrays.asList(array), property, quot, delim);
    }

    /**
     * generate readable String presentation of an collection
     * 
     * @param c
     * @param property
     * @param quot
     * @param delim
     * @return
     */
    public static String toString(Collection c, String property, String quot,
            String delim) {
        StringBuffer sb = new StringBuffer();
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = bean;
            if (property != null) {
                value = BeanUtils.getProperty(bean, property);
            }
            sb.append(quot + value + quot);
            if (iter.hasNext()) {
                sb.append(delim);
            }
        }
        return sb.toString();
    }

    /**
     * filter a collection, get a new list whose elements' property value meets
     * condition
     * 
     * @param c
     * @param property
     * @param condition
     * @return
     */
    public static List filter(Collection c, String property, Object condition) {
        List result = new ArrayList();
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = BeanUtils.getProperty(bean, property);
            if (condition != null && condition.equals(value)) {
                result.add(bean);
            }
        }
        return result;
    }
    
    
    
    /**
     * filter a collection, get a new list whose elements' property value meets
     * condition
     * 
     * @param c
     * @param property
     * @param condition
     * @return
     */
    public static List getList(Collection c, String property) {
        List result = new ArrayList();
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = BeanUtils.getProperty(bean, property);
            result.add(value);
            
        }
        return result;
    }

    /**
     * find an object condition
     * 
     * @param c
     * @param property
     * @param condition
     * @return
     */
    public static Object findDate(Collection c, String property, Date condition) {
        // List result = new ArrayList();
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Date value = (Date) BeanUtils.getProperty(bean, property);
            if (condition != null && condition.compareTo(value) == 0) {
                return bean;
            }
        }
        return null;
    }

    /**
     * filter a collection, get a new list whose elements' property value meets
     * values in condition
     * 
     * @param c
     * @param property
     * @param condition
     * @return
     */
    public static List filterAll(Collection collection, String property,
            Collection condition) {
        List result = new ArrayList();

        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = BeanUtils.getProperty(bean, property);
            if (condition != null && condition.contains(value)) {
                result.add(bean);
            }
        }
        return result;
    }

    /**
     * remove elements from a collection whose property value meets condition
     * 
     * @param c
     * @param property
     * @param condition
     */
    public static void remove(Collection c, String property, Object condition) {
        Iterator iter = c.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = BeanUtils.getProperty(bean, property);
            if (condition != null && condition.equals(value)) {
                iter.remove();
            }
        }
    }

    /**
     * remove elements from a collection whose property value meets values in
     * condition
     * 
     * @param collection
     * @param property
     * @param condition
     */
    public static void removeAll(Collection collection, String property,
            Collection condition) {
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object value = BeanUtils.getProperty(bean, property);
            if (condition != null && condition.contains(value)) {
                iter.remove();
            }
        }
    }

    /**
     * 
     * @param map
     * @param valueComparator
     */
    public static void sortML(Map map, Comparator valueComparator) {
        for (Iterator iter = map.values().iterator(); iter.hasNext();) {
            List valueList = (List) iter.next();
            if (valueComparator == null) {
                Collections.sort(valueList);
            } else {
                Collections.sort(valueList, valueComparator);
            }
        }
    }

    public static void sort(List list, String property) {

        Comparator compare = CompareUtil.getComparator(property);

        Collections.sort(list, compare);
    }

    /**
     * 
     * @param collection
     * @param properties
     * @param delim
     * @return
     */
    public static Map group(Collection collection, String[] properties,
            String delim) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        List groupList = null;
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            String key = "";
            for (int i = 0; i < properties.length; i++) {
                if (i > 0 && delim != null) {
                    key += delim;
                }
                key += BeanUtils.getProperty(bean, properties[i]);
            }
            if (map.containsKey(key)) {
                groupList = (List) map.get(key);
                groupList.add(bean);
            } else {
                groupList = new ArrayList();
                groupList.add(bean);
                map.put(key, groupList);
            }
        }
        return map;
    }

    /**
     * 
     * @param collection
     * @param keyProperty
     * @return
     */
    public static Map group(Collection collection, String keyProperty) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Object bean = null;
        Object key = null;
        List groupList = null;

        Iterator iter = collection.iterator();

        while (iter.hasNext()) {
            bean = iter.next();
            key = BeanUtils.getProperty(bean, keyProperty);
            if (key == null) {
                continue;
            }
            if (map.containsKey(key)) {
                groupList = (List) map.get(key);
                groupList.add(bean);
            } else {
                groupList = new ArrayList();
                groupList.add(bean);
                map.put(key, groupList);
            }
        }
        return map;
    }

    public static Map groupMap(Collection collection, String keyName) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Map eachMap = null;
        Object key = null;
        List groupList = null;

        Iterator iter = collection.iterator();

        while (iter.hasNext()) {
            eachMap = (Map) iter.next();
            key = eachMap.get(keyName);
            if (key == null) {
                continue;
            }
            if (map.containsKey(key)) {
                groupList = (List) map.get(key);
                groupList.add(eachMap);
            } else {
                groupList = new ArrayList();
                groupList.add(eachMap);
                map.put(key, groupList);
            }
        }
        return map;
    }

    public static int totalMapList(Collection collection, String keyName) {

        if (collection == null || collection.isEmpty()) {
            return 0;
        }

        Map eachMap = null;

        int total = 0;
        Iterator iter = collection.iterator();

        while (iter.hasNext()) {
            eachMap = (Map) iter.next();
            Object value = eachMap.get(keyName);
            if (value == null) {
                continue;
            }
            int tempQty = Integer.parseInt(value.toString());
            System.out.print(" tempQty is " + tempQty);
            total += tempQty;

        }
        return total;
    }

    public static Map group(Collection collection, String keyProperty,
            String valueProperty) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Object bean = null;
        Object key = null;
        Object value = null;
        List groupList = null;

        Iterator iter = collection.iterator();

        while (iter.hasNext()) {
            bean = iter.next();
            key = BeanUtils.getProperty(bean, keyProperty);
            value = BeanUtils.getProperty(bean, valueProperty);
            if (key == null) {
                continue;
            }
            if (map.containsKey(key)) {
                groupList = (List) map.get(key);
                groupList.add(value);
            } else {
                groupList = new ArrayList();
                groupList.add(value);
                map.put(key, groupList);
            }
        }
        return map;
    }

    public static boolean checkIfEqual(Collection collection,
            String valuePropertie, String valueFind, String[] properties,
            String[] findValues) {
        String delim = ",";
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return true;
        }

        List groupList = null;
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            String key = "";
            Object value = BeanUtils.getProperty(bean, valuePropertie);
            logger.debug("valuePropertie : " + valuePropertie);
            logger.debug("value : " + value);
            for (int i = 0; i < properties.length; i++) {
                if (i > 0 && delim != null) {
                    key += delim;
                }
                key += BeanUtils.getProperty(bean, properties[i]);
            }
            if (map.containsKey(key)) {
                groupList = (List) map.get(key);
                groupList.add(value);
            } else {
                groupList = new ArrayList();
                groupList.add(value);
                map.put(key, groupList);
            }

        }

        String key = "";
        for (int i = 0; i < findValues.length; i++) {
            if (i > 0 && delim != null) {
                key += delim;
            }
            key += findValues[i];
        }
        logger.debug("key is " + key);
        logger.debug("map.get(key) is " + map.get(key));
        List resultList = (List) map.get(key);

        Set checkSet = new HashSet(resultList);
        logger.debug("valueFind is " + valueFind);
        String[] resultArray = valueFind.split(",");

        List templist = Arrays.asList(resultArray);
        logger.debug("templist  size " + templist.size());
        logger.debug("resultList  size " + resultList.size());

        for (int i = 0; i < templist.size(); i++) {

            if (!checkSet.contains(templist.get(i).toString())) {
                return false;
            } else {
                checkSet.remove(templist.get(i).toString());
            }
        }

        if (checkSet.size() != 0) {
            return false;
        }

        return true;
    }

    /**
     * @param adjusts
     * @param properties
     * @param delim
     * @return
     */
    public static Map hash(Collection collection, String[] properties,
            String delim) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            String key = "";
            for (int i = 0; i < properties.length; i++) {
                if (i > 0 && delim != null) {
                    key += delim;
                }
                key += BeanUtils.getProperty(bean, properties[i]);
            }
            map.put(key, bean);
        }
        return map;
    }

    public static Map hash(Collection collection, String propertieKey,
            String propertieValue) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            String key = "";
            Object value = null;
            key = (String) BeanUtils.getProperty(bean, propertieKey);

            value = BeanUtils.getProperty(bean, propertieValue);
            if (map.get(key) == null) {
                map.put(key, value);
            }
        }
        return map;
    }

    public static Map hashIgnorType(Collection collection, String propertieKey,
            String propertieValue) {
        Map map = new HashMap();
        if (collection == null || collection.isEmpty()) {
            return map;
        }

        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            Object bean = iter.next();
            Object key = null;
            Object value = null;
            key = BeanUtils.getProperty(bean, propertieKey);

            value = BeanUtils.getProperty(bean, propertieValue);
            if (map.get(key) == null) {
                map.put(key, value);
            }
        }
        return map;
    }

    public static List toList(Object[] src) {
        List result = new ArrayList();
        if (src != null) {
            for (int i = 0; i < src.length; i++) {
                result.add(src[i]);
            }
        }
        return result;
    }
}
