package cn.future.log.service.test;

import cn.future.log.service.impl.JdkLogService;

public class MainTest {
	public static void main(String[] args){
		JdkLogService s = new JdkLogService();
		s.info(MainTest.class,"main","测试");
	}
}
