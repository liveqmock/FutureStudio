package cn.future.pay.pojo;

import java.io.Serializable;
import java.util.Date;

public class PCashAccountManunal  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5733625474315450416L;
	//需要事务
	private String id;
	private int version;
	private double inNumber;
	private Date date;
	private int paySystemCode;
	private String paySystemId;
	private String paySystemInfo;
	
	
	
}
