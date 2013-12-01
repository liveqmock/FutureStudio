package cn.future.pay.service.baopay.impl;

import cn.future.common.dao.BaseDao;
import cn.future.pay.pojo.BaoRequest;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.baopay.BaopayRequestService;

public class BaopayRequestServiceImpl implements BaopayRequestService {
	private BaseDao baseDao;
	
	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public BaoRequest createBaoRequest(PPayRequest pp) {
		BaoRequest bao = new BaoRequest(pp);
		baseDao.save(pp);
		return bao;
	}

	public String getBankId(String bankId){
		//TODO 暂时全部走统一平台
		return "1000";
	}
}
