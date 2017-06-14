
package com.hwmao.util;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * @author Administrator
 *
 */


//比较器类  
public class MapValueComparator implements Comparator<Map.Entry<String, String>> {  
    public int compare(Entry<String, String> me1, Entry<String, String> me2) {  
        return me1.getValue().compareTo(me2.getValue());  
    }  
    
	//给map按照vlue进行排序
	public  ArrayList<Map.Entry<String,Double>> sortMap(Map map){
	     List<Map.Entry<String, Double>> entries = new ArrayList<Map.Entry<String, Double>>(map.entrySet());
	     Collections.sort(entries, new Comparator<Map.Entry<String, Double>>() {
	         public int compare(Map.Entry<String, Double> obj1 , Map.Entry<String, Double> obj2) {
	             return (int) (obj2.getValue() - obj1.getValue());
	         }
	     });
	      return (ArrayList<Entry<String, Double>>) entries;
	    }
}  
