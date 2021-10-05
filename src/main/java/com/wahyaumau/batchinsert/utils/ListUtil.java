package com.wahyaumau.batchinsert.utils;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
    public static <T> List<List<T>> createSubList(List<T> list, int subListSize){
        List<List<T>> listOfSubList = new ArrayList<>();
        for (int i = 0; i < list.size(); i+=subListSize) {
            if(i + subListSize <= list.size()){
                listOfSubList.add(list.subList(i, i + subListSize));
            }else{
                listOfSubList.add(list.subList(i, list.size()));
            }
        }
        return listOfSubList;
    }
}
