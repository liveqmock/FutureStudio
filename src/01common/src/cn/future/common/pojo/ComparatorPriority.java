package cn.future.common.pojo;

import java.util.Comparator;
/**
 * 针对于优先级基类的Collection 排序 
 */
public class ComparatorPriority implements Comparator<Priority>{

	@Override
	public int compare(Priority o1, Priority o2) {
		// TODO Auto-generated method stub
		if(o1.getPriority()>o2.getPriority()){
			return 1;
		}else{
			return 0;
		}
	}

}
