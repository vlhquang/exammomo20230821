package exammomo20230821.com.quangvlh.paypal;

import exammomo20230821.com.quangvlh.action.impl.CashInAction;
import exammomo20230821.com.quangvlh.action.impl.CreateBillAction;
import exammomo20230821.com.quangvlh.action.impl.DueDateAction;
import exammomo20230821.com.quangvlh.action.impl.ListBillAction;
import exammomo20230821.com.quangvlh.action.impl.ListPaymentAction;
import exammomo20230821.com.quangvlh.action.impl.PayAction;
import exammomo20230821.com.quangvlh.action.impl.ScheduleAction;
import exammomo20230821.com.quangvlh.action.impl.SearchBillByProviderAction;
import exammomo20230821.com.quangvlh.constants.ActionCodeConstants;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public class PayPalActionFirstVersionImpl extends PayPalActionAbstract {

	@Override
	protected void initMapAction() throws ExamMomoException {
		putMapActionByActionName(new CashInAction(ActionCodeConstants.CASH_IN));
		putMapActionByActionName(new DueDateAction(ActionCodeConstants.DUE_DATE));
		putMapActionByActionName(new ListBillAction(ActionCodeConstants.LIST_BILL));
		putMapActionByActionName(new ListPaymentAction(ActionCodeConstants.LIST_PAYMENT));
		putMapActionByActionName(new PayAction(ActionCodeConstants.PAY));
		putMapActionByActionName(new ScheduleAction(ActionCodeConstants.SCHEDULE));
		putMapActionByActionName(new SearchBillByProviderAction(ActionCodeConstants.SEARCH_BILL_BY_PROVIDER));
		putMapActionByActionName(new CreateBillAction(ActionCodeConstants.CREATE_BILL));
	}
}
