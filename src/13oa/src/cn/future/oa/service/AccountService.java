package cn.future.oa.service;

import java.util.List;

import cn.future.common.exception.NotFindException;
import cn.future.common.exception.NotUniqueException;
import cn.future.oa.dto.DAccount;
import cn.future.oa.pojo.PAccount;
import cn.future.oa.pojo.PAccountType;
import cn.future.user.exception.UserNotAllowedAccess;
import cn.future.user.exception.UserPasswordUnmatchException;

public  abstract interface AccountService {
	/**
	 * 更改密码
	 * @param oldPassword
	 * @param newPassword
	 * @param userId
	 * @throws NotFindException 
	 */
	public void updatePassword(String oldPassword,String newPassword,String userId) throws UserPasswordUnmatchException, NotFindException ;
	/**
	 * Domain to Pojo
	 * @param a
	 * @param transMode 暂时使用1；  2的意思是获取部门信息
	 * @return
	 */
	public DAccount transferPAccount(PAccount a, int transMode );
	/**
	 * domain list to pojo list
	 * @param list
	 * @param transMode 暂时使用1
	 * @return
	 */
	public List<DAccount> transferPAccount(List<PAccount> list, int transMode );
	/**
	 * 查找一组职位下的账户
	 * @param deptId
	 * @return
	 */
	public List<PAccount> findPositionsAccount(String[] positions);
	/**
	 * 查找account，持久化对象
	 * @param id
	 * @return
	 */
	public PAccount findAccountById(String id)throws NotFindException ;

	/**
	 * 通过职位id查找该职位下的用户
	 * 直接查找
	 * @param id
	 * @param isAll true 查询所有，false查询statusCode>-1的，冻结的和可用账户
	 * @return
	 * @throws NotFindException
	 */
	public List<PAccount> findAccountByPositionId(String id,boolean isAll)throws NotFindException;
	/**
	 * 登陆服务。
	 * 通过账户判断是否存在账户，将密码进行加密，然后做匹配，判断账户状态是否大于等于IntUtil.status_active;
	 * @param account
	 * @param password
	 * @return
	 * @throws NotUniqueException 
	 */
	public PAccount signInService(String account,String password)throws NotFindException, UserPasswordUnmatchException, UserNotAllowedAccess, NotUniqueException;

	/**
	 * 在适当的时候清空缓存中的用户信息
	 * @param userid
	 */
	public void clearUserCache(String userid);
	
	/**
	 * 更新用户信息，这些操作需要记录日志
	 * @param userId
	 * @param id
	 * @param name
	 * @param employeeId
	 * @param account
	 * @param mobile
	 * @param email
	 * @param comments
	 * @param statusCode
	 * @param statusName
	 * @throws NotFindException 
	 */
	public PAccount updateAccount(String userId,String id, String name, String employeeId,
			String account, String mobile, String email, String comments,
			String statusCode, String statusName) throws NotFindException;
	/**
	 * 新增一个用户
	 * @param userid
	 * @param positionId
	 * @param account
	 */
	public PAccount addAccount(String userid, String positionId,PAccount account) throws NotFindException ;
	/**
	 * 检验工号是否唯一
	 * @param employeeId
	 * @return
	 */
	public int employeeIdCount(String employeeId);
	/**
	 * 
	 * @param account
	 * @return
	 */
	public int accountCount(String account);
	/**
	 * 查询所有的用户账户类型
	 * @return
	 */
	public List<PAccountType> findAllAccountType();
	
	public void updateAccount(PAccount add);
	/**
	 * 新增账户类型
	 * @param name
	 * @param comments
	 * @param priority
	 */
	public void addAccountType(String name, String comments, String priority);
	/**
	 * 修改账户信息
	 * @param id
	 * @param name
	 * @param comments
	 * @param priority
	 * @throws NotFindException 
	 */
	public void updateAccountType(String id, String name, String comments,
			String priority) throws NotFindException;
	/**
	 * 修改两个账户类型的优先级
	 * @param id1
	 * @param id2
	 * @throws NotFindException 
	 */
	public void updateAccountTypePriority(String id1, String id2) throws NotFindException;

}
