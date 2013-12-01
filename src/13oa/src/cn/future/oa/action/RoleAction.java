package cn.future.oa.action;

import java.util.List;

import cn.future.common.action.BaseAction;
import cn.future.common.exception.NotFindException;
import cn.future.common.service.CommonService;
import cn.future.oa.dto.DRole;
import cn.future.oa.pojo.Role;
import cn.future.oa.service.RoleService;

public class RoleAction  extends BaseAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -136060827269110909L;
	private String message="";
	private List<DRole> roles;
	
	//get / set
	private RoleService roleService;
	private CommonService commonService;
	private String id1;
	private String id2;
	private String id;
	private String data1;
	private String data2;
	/**
	 * 调整角色的级次
	 * @return
	 * @throws NotFindException 
	 * @throws NumberFormatException 
	 */
	public String switchPriority() throws NumberFormatException, NotFindException{
		commonService.updatePriority(Role.class, id1, Integer.valueOf(data1), id2, Integer.valueOf(data2));
		return SUCCESS;
	}
	/**
	 * 更新角色信息，id是role的id，data1是名字，data2是备注
	 * @return
	 * @throws Exception
	 */
	public String updateRole()throws Exception{
		roleService.updateRole(id,data1,data2);
		return SUCCESS;
	}
	/**
	 * 添加一个角色，角色的名字是data1，角色的备注是data2,
	 * 然后从cookies里面取用户id
	 * @return
	 */
	public String addRole()throws Exception{
		roleService.addRole(this.findCookieId(),data1,data2);
		return SUCCESS;
	}
	/**
	 * 更新角色的操作权限
	 * id1是角色id
	 * id2是操作权限的id
	 * data1是操作模式，0是删除，1是新增
	 * @return
	 */
	public String updateOperation(){
		//角色id，functionid，操作类型data 1为增加，0为减少
				if(id1!=null && id2!=null && data1!=null){
					if("0".equals(data1)){
						roleService.deleteRoleOperation(id1,id2);
						message="删除角色的操作权限成功";
					}else{
						roleService.addRoleOperation(id1,id2);
						message="增加角色的操作权限成功";
					}
				}else{
					message="请求信息错误";
				}
		return SUCCESS;
	}
	/**
	 * 根据用户的请求处理角色的菜单，data1为1新增，为0，删除。
	 * id1为角色id
	 * id2为菜单id
	 * @return
	 */
	public String updateFunction(){
		//角色id，functionid，操作类型data 1为增加，0为减少
		if(id1!=null && id2!=null && data1!=null){
			if("0".equals(data1)){
				roleService.deleteRoleFunction(id1,id2);
				message="删除角色的功能菜单成功";
			}else{
				roleService.addRoleFunction(id1,id2);
				message="增加角色的功能菜单成功";
			}
		}else{
			message="请求信息错误";
		}
		return SUCCESS;
	}
	
	/**
	 * 查找用户的角色信息
	 * @return
	 * @throws NotFindException 
	 */
	public String findAccountRoles() throws NotFindException{
		roles=roleService.findAccountRoles(id);
		return SUCCESS;
	}
	/**
	 * 不需要传回什么信息，仅仅判断用户是否有这个权限进行这个操作
	 * //TODO 暂时不做这个事情，等以后有空了加判断条件
	 * @return
	 * @throws NotFindException 
	 */
	public String findRoles() throws NotFindException{
		roles = roleService.findRoles();
		return SUCCESS;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	public String getMessage() {
		return message;
	}
	public List<DRole> getRoles() {
		return roles;
	}
	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
	public void setId1(String id1) {
		this.id1 = id1;
	}
	public void setId2(String id2) {
		this.id2 = id2;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setData1(String data1) {
		this.data1 = data1;
	}
	public void setData2(String data2) {
		this.data2 = data2;
	}
	
	
}
