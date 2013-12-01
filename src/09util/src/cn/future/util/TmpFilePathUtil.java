package cn.future.util;

import com.baidu.bae.api.util.BaeEnv;

public class TmpFilePathUtil {
	/**
	 * 获取临时文件目录
	 * @return
	 */
	public static String findTmpFs(){
		String tmpfsPath = BaeEnv.getTmpfsPath();
		return tmpfsPath;
	}
}
