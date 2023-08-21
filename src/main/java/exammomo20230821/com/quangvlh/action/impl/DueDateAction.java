package exammomo20230821.com.quangvlh.action.impl;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class DueDateAction extends ActionAbstract<String> {

	public DueDateAction(String actionName) {
		super(actionName);
	}

	@Override
	public String handle(String value, PayPalAction payPalAction) {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);
		return null;
	}

}
