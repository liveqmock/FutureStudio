package cn.future.pay.action;

import java.util.ArrayList;
import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.pay.dto.DPayRequest;
import cn.future.pay.pojo.PPayRequest;
import cn.future.pay.service.DPayRequestFactory;
import cn.future.pay.service.PayRequestQueryService;

public class PaymentQueryAction extends BaseAction{

	private static final long serialVersionUID = -8000893687998980588L;
	//set
	private PayRequestQueryService payRequestQueryService;
	private DPayRequestFactory dPayRequestFactory;
	
	//set get
	private int page=1;
	//get
	private int pageSize=20;
	private int count;
	private List<DPayRequest> payments;
	/**
	 * 查询充值记录
	 * @return
	 */
	public String list(){
		try {
			List<PPayRequest> pojos = new ArrayList<PPayRequest>();
			count = payRequestQueryService.findPayRequest(pojos, page, pageSize, null, null, null, null);
			dPayRequestFactory.initByPojo(pojos);
			dPayRequestFactory.initCustomers();
			payments = dPayRequestFactory.getDPayRequests();
		} catch (NotFindException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return SUCCESS;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public int getPageSize() {
		return pageSize;
	}

	public int getCount() {
		return count;
	}

	public List<DPayRequest> getPayments() {
		return payments;
	}

	public void setPayRequestQueryService(
			PayRequestQueryService payRequestQueryService) {
		this.payRequestQueryService = payRequestQueryService;
	}

	public void setdPayRequestFactory(DPayRequestFactory dPayRequestFactory) {
		this.dPayRequestFactory = dPayRequestFactory;
	}
	
}
