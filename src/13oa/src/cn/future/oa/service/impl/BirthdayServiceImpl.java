package cn.future.oa.service.impl;

import java.util.HashMap;
import java.util.List;

import cn.future.common.dao.BaseDao;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.service.BirthdayService;

public class BirthdayServiceImpl implements BirthdayService {
	private BaseDao baseDao;
	@Override
	public List<PAccount> findBirthdayByMonth(String month) {
		// TODO Auto-generated method stub
		int m=Integer.parseInt(month);
		int start=m*100;
		int end=(m+1)*100;
		return this.findBirthdayByRange(start, end);
	}

	@Override
	public List<PAccount> findBirthdayByRange(int start, int end) {
		HashMap<String,Object> params=new HashMap<String,Object>();
		params.put("start", start);
		params.put("end", end);
		return baseDao.findAll(PAccount.class, "from cn.future.oa.pojo.PAccount as a where a.birthdayCode>:start and a.bitrhdayCode<:end and a.statusCode>0", 1, 999, params);
	}

	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

}
