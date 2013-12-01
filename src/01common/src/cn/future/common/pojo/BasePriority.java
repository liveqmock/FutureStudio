package cn.future.common.pojo;

/**
 * 基础Domain类，已经包含了id和优先级字段。
 * id, comments
 */
public class BasePriority extends Priority{
	private static final long serialVersionUID = 3040934687849889745L;
	public String id;
	public String comments;

	public BasePriority(BasePriority t){
		super(t.getPriority());
		this.id=t.getId();
		this.comments=t.getComments();
	}
	public BasePriority(){
		super();
	}
	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
}
