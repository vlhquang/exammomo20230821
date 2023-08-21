package exammomo20230821.com.quangvlh.action.impl;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.common.Convertion;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class CashInAction extends ActionAbstract<Long> {

	public CashInAction(String actionName) {
		super(actionName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Long handle(String value, PayPalAction payPalAction) throws ExamMomoException {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);
		long temp = Convertion.convertStringToLong(value);
		long moneyCurrent = payPalAction.increaMoney(temp);
		System.out.println("Your available balance: " + moneyCurrent);
		return moneyCurrent;
	}
}
