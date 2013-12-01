package cn.future.oa.service;

import java.util.List;

import cn.future.oa.pojo.PAccount;

public abstract interface BirthdayService {
	/**
	 * 通过月份查找用户生日
	 * @param month
	 * @return
	 */
	public List<PAccount> findBirthdayByMonth(String month);
	/**
	 * 通过范围查找用户的生日
	 * @param start
	 * @param end
	 * @return
	 */
	public List<PAccount> findBirthdayByRange(int start, int end);
}
