package exammomo20230821.com.quangvlh.action;

import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public interface Action<T> {
	String actionName();
	T handle(String value, PayPalAction payPalAction) throws ExamMomoException;
}
