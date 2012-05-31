// $Id: CompareUtil.java,v 1.1 2009/02/25 04:20:34 wanxing Exp $
package com.eh100.util;

import java.util.Comparator;
import java.util.Map;

public class CompareUtil {

    /**
     * constant represents the order of ascent
     */
    public static final int ORDER_ASC = 1;

    /**
     * constant represents the order of decent
     */
    public static final int ORDER_DEC = -1;

    /**
     * get a comparator by the value of propety, ascent
     * 
     * @param property
     * @return
     * @see getComparator(String property, int order)
     */
    public static Comparator getComparator(String property) {
        return getComparator(property, ORDER_ASC);
    }

    /**
     * get a comparator by the value of propety, with order: order
     * 
     * @param property
     * @param order
     * @return
     * @see Comparator getComparator(String property, int order, boolean
     *      ignoreCase)
     */
    public static Comparator getComparator(String property, int order) {
        return getComparator(new String[] { property }, new int[] { order });
    }

    /**
     * 
     * @param property
     * @param order
     * @param ignoreCase
     * @return
     */
    public static Comparator getComparator(String property, int order,
            boolean ignoreCase) {
        return getComparator(new String[] { property }, new int[] { order },
                true);
    }

    /**
     * 
     * @param properties
     * @return
     */
    public static Comparator getComparator(String[] properties) {
        int[] orders = new int[properties.length];
        for (int i = 0; i < properties.length; i++) {
            orders[i] = ORDER_ASC;
        }
        return getComparator(properties, orders);
    }

    /**
     * 
     * @param properties
     * @param orders
     * @return
     */
    public static Comparator getComparator(String[] properties, int[] orders) {
        return getComparator(properties, orders, true);
    }

    /**
     * 
     * @param properties
     * @param orders
     * @param ignoreCase
     * @return
     */
    public static Comparator getComparator(final String[] properties,
            final int[] orders, final boolean ignoreCase) {
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                int result = 0;
                for (int i = 0; i < properties.length; i++) {
                    Comparable c1 = (Comparable) BeanUtils.getProperty(o1,
                            properties[i]);
                    Comparable c2 = (Comparable) BeanUtils.getProperty(o2,
                            properties[i]);
                    if (c1 == null && c2 == null) {
                        result = 0;
                    } else if (c1 == null) {
                        result = -1 * orders[i];
                    } else if (c2 == null) {
                        result = 1 * orders[i];
                    } else if (ignoreCase && c1 instanceof String
                            && c2 instanceof String) {
                        result = ((String) c1).compareToIgnoreCase((String) c2)
                                * orders[i];
                    } else {
                        result = c1.compareTo(c2) * orders[i];
                    }
                    if (result != 0) {
                        break;
                    }
                }
                return result;
            }
        };
        return comparator;
    }

    public static Comparator reverseComparator() {
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                Comparable c1 = (Comparable) o1;
                Comparable c2 = (Comparable) o2;
                int result = 0;
                if (c1 == null && c2 == null) {
                } else if (c1 == null) {
                    result = c2.compareTo(c1);
                } else {
                    result = -c1.compareTo(o2);
                }
                return result;
            }
        };
        return comparator;
    }

    public static Comparator getComparatorMap(final String[] keyNames,
            final int[] orders, final boolean ignoreCase) {
        Comparator comparator = new Comparator() {
            public int compare(Object o1, Object o2) {
                int result = 0;
                for (int i = 0; i < keyNames.length; i++) {
                    Map m1 = (Map) o1;
                    Map m2 = (Map) o2;
                    Comparable c1 = (Comparable) m1.get(keyNames[i]);
                    Comparable c2 = (Comparable) m2.get(keyNames[i]);
                    if (c1 == null && c2 == null) {
                        result = 0;
                    } else if (c1 == null) {
                        result = -1 * orders[i];
                    } else if (c2 == null) {
                        result = 1 * orders[i];
                    } else if (ignoreCase && c1 instanceof String
                            && c2 instanceof String) {
                        result = ((String) c1).compareToIgnoreCase((String) c2)
                                * orders[i];
                    } else {
                        result = c1.compareTo(c2) * orders[i];
                    }
                    if (result != 0) {
                        break;
                    }
                }
                return result;
            }
        };
        return comparator;
    }
}
