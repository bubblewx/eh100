// $Id: BeanUtils.java,v 1.3 2009/05/13 09:17:45 wanxing Exp $
package com.eh100.util;

import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

public class BeanUtils extends PropertyUtils {
    /**
     * wrap org.apache.commons.beanutils.BeanUtils.cloneBean(bean); throw a
     * RuntimeException when Exception occurs
     * 
     * @param bean
     * @return
     * @throws RuntimeException
     */
    public static Object cloneBean(Object bean) throws RuntimeException {
        try {
            return org.apache.commons.beanutils.BeanUtils.cloneBean(bean);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * wrap org.apache.commons.beanutils.PropertyUtils.getProperty(); throw
     * a RuntimeException when Exception occurs
     * 
     * @param bean
     * @param property
     * @throws RuntimeException
     */
    public static Object getProperty(Object bean, String property)
            throws RuntimeException {
        Object value = null;
        try {
            value = PropertyUtils.getProperty(bean, property);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        return value;
    }

    /**
     * wrap org.apache.commons.beanutils.PropertyUtils.setProperty(); throw
     * a RuntimeException when Exception occurs
     * @param bean
     * @param property
     * @param value
     * @throws RuntimeException
     */
    public static void setProperty(Object bean, String property, Object value)
            throws RuntimeException {
        try {
            if (property.indexOf(".") > -1) {
                String[] prpertys = property.split("\\.");
                Object child = getProperty(bean, prpertys[0]);
                if (child == null) {
                    child = BeanUtils.newInstance(PropertyUtils
                            .getPropertyType(bean, prpertys[0]));
                    org.apache.commons.beanutils.BeanUtils.setProperty(bean,
                            prpertys[0], child);
                }

                org.apache.commons.beanutils.BeanUtils.setProperty(child,
                        prpertys[1], value);

            } else {
                org.apache.commons.beanutils.BeanUtils.setProperty(bean,
                        property, value);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * compare two Object to see whether they are equal
     * 
     * @param bean1
     * @param bean2
     * @return
     */
    public static boolean equals(Object bean1, Object bean2) {
        if (bean1 == null && bean2 == null) {
            return true;
        }

        try {
            if (bean1.getClass().equals(bean2.getClass())) {
                if (bean1 == bean2) {
                    return true;
                }
                Map map1 = BeanUtils.describe(bean1);
                Map map2 = BeanUtils.describe(bean2);
                return map1.equals(map2);
            }
        } catch (Exception e) {
            /*
             * catch bean1 or bean2 NullPointException catch
             * BeanUtils.describe() exception catch BeanUtils.describe()
             * returned null map exception
             */
            return false;
        }

        return false;
    }

    /**
     * @see toString(Object bean, List travelledList)
     * @param bean
     * @return
     */
    public static String toString(Object bean) {
        return toString(bean, null);
    }

    /**
     * 
     * @param bean
     * @param travelledList
     * @return
     */
    public static String toString(Object bean, List travelledList) {
        if (bean == null) {
            return "null";
        }

        if (travelledList == null) {
            // travelledList = new Vector();
        }

        StringBuffer sb = new StringBuffer();
        sb.append("<javabean class=\"" + bean.getClass().getName() + "\">"
                + "\n");

        PropertyDescriptor[] propDescriptors = BeanUtils
                .getPropertyDescriptors(bean);

        for (int i = 0; i < propDescriptors.length; i++) {
            try {
                String propName = propDescriptors[i].getName();
                Class propClass = propDescriptors[i].getPropertyType();
                Object propValue = BeanUtils.getProperty(bean, propName);
                if (propValue != null
                        && (propValue.getClass().isPrimitive()
                                || (propValue.getClass().getName()
                                        .startsWith("java.lang")) || (propValue
                                .getClass().getName()
                                .startsWith("java.util.Date")))) {
                    if (propValue != null) {
                        propValue = propValue.toString();
                    }
                    sb.append("<property name=\"" + propName + "\" class=\""
                            + propClass.getName() + "\">");
                    sb.append(propValue);
                    sb.append("</property>\n");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        sb.append("</javabean>" + "\n");

        return sb.toString();
    }

    /**
     * @param dest
     * @param src
     */
    public static void copyProperties(Object dest, Object src) {
        try {
            org.apache.commons.beanutils.BeanUtils.copyProperties(dest, src);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object newInstance(Class clazz) {
        Object bean = null;
        try {
            bean = clazz.newInstance();
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
        return bean;
    }
}
