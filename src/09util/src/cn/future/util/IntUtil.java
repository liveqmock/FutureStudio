/**
 * Tony FutureStudio soft-qiyao@foxmail.com Jan 7, 2013 9:42:57 PM
 */

package cn.future.util;

public class IntUtil {
	
	
	/**
	 * 取出一个指定长度大小的随机正整数.
	 * 
	 * @param length
	 *            int 设定所取出随机数的长度。length小于11
	 * @return int 返回生成的随机数。
	 */
	public static int buildRandom(int length) {
		int num = 1;
		double random = Math.random();
		if (random < 0.1) {
			random = random + 0.1;
		}
		for (int i = 0; i < length; i++) {
			num = num * 10;
		}
		return (int) ((random * num));
	}

	/**
	 * 把对象转换为int数值.
	 * 
	 * @param obj
	 *            包含数字的对象.
	 * @return int 转换后的数值,对不能转换的对象返回0。
	 */
	public static int toInt(Object obj) {
		int a = 0;
		try {
			if (obj != null)
				a = Integer.parseInt(obj.toString());
		} catch (Exception e) {

		}
		return a;
	}
	
	/**
	 * 计算优先级的算法
	 * @param before
	 * @param after
	 * @return
	 */
	public static int findPriority(Integer before, Integer after){
		int k = 100000;
		if(before!=null && after!=null){
			k = (before.intValue()+after.intValue())/2;
		}else if(before!=null && after==null){
			k = before.intValue()*2;
		}else if(before== null && after!=null){
			k = after.intValue()/2;
		}
		return k;		
	}
	//以下是状态代码
	public static final int STATUS_DELETE = -10;
	public static final int STATUS_RECYCLE = -5;
	public static final int STATUS_FREEZE = -3;
	public static final int STATUS_UNACTIVE = 0;
	public static final int STATUS_ACTIVE = 1;
	public static String statusName(int k){
		if(k==-10)return "已删除";
		if(k==-5)return "回收站";
		if(k==-3)return "冻结";
		if(k==-0)return "停用";
		if(k==1)return "正常";
		return "未知状态";
	}
}
