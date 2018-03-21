package util;
import entity.FlowerPost;

import java.util.Comparator;
import java.util.Date;

public class ComparatorDate implements Comparator<Object> {

	public int compare(Object o1, Object o2) {
		
		Date begin = ((FlowerPost) o1).getPlantEndDate();
        Date end = ((FlowerPost) o2).getPlantEndDate();  
        if (begin.after(end)) {  
            return 1;  
        } else {  
            return -1;  
        }
	}

}
