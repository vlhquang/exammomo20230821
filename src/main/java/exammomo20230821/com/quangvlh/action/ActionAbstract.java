package exammomo20230821.com.quangvlh.action;

public abstract class ActionAbstract<T> implements Action<T> {
	protected String actionName;

	public ActionAbstract(String actionName) {
		super();
		this.actionName = actionName;
	}
	
	@Override
	public String actionName() {
		return actionName;
	}
}
