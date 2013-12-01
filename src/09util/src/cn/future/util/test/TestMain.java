package cn.future.util.test;

import java.text.ParseException;

import cn.future.util.IdCardUtil;

public class TestMain {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		String s = IdCardUtil.IDCardValidate("510821198906246315");
		System.out.println(s);
	}

}
