package com.eh100.util;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListUtils {

	static public <T> List<List<T>> splitList(List<T> list, int maxListSize) {
        List<List<T>> splittedList = new ArrayList<List<T>>();
        int itemsRemaining = list.size();
        int start = 0;

        while (itemsRemaining != 0) {
            int end = itemsRemaining >= maxListSize ? (start + maxListSize) : list.size();

            splittedList.add(list.subList(start, end));

            int sizeOfFinalList = end - start;
            itemsRemaining = itemsRemaining - sizeOfFinalList;
            start = start + sizeOfFinalList;
        }

        return splittedList;
    }

}