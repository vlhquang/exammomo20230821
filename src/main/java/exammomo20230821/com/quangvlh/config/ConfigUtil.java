package exammomo20230821.com.quangvlh.config;

import exammomo20230821.com.quangvlh.paypal.PayPalAction;
import exammomo20230821.com.quangvlh.paypal.PayPalActionFirstVersionImpl;

public class ConfigUtil {
    public static PayPalAction getPayPalActionInstance() {
        return PayPalActionHelper.INSTANCE;
    }
 
    private static class PayPalActionHelper {
        private static final PayPalAction INSTANCE = new PayPalActionFirstVersionImpl();
    }
	
//	public static final Map<String, Action> MAP_ACTION_PAY_BY_ACTION_NAME;
//	
//	static {
//		MAP_ACTION_PAY_BY_ACTION_NAME = new HashMap<String, Action>();
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.CASH_IN, new CashInAction(ActionCodeConstants.CASH_IN));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.DUE_DATE, new DueDateAction(ActionCodeConstants.DUE_DATE));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.LIST_BILL, new ListBillAction(ActionCodeConstants.LIST_BILL));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.LIST_PAYMENT, new ListPaymentAction(ActionCodeConstants.LIST_PAYMENT));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.PAY, new PayAction(ActionCodeConstants.PAY));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.SCHEDULE, new ScheduleAction(ActionCodeConstants.SCHEDULE));
//		MAP_ACTION_PAY_BY_ACTION_NAME.put(ActionCodeConstants.SEARCH_BILL_BY_PROVIDER, new SearchBillByProviderAction(ActionCodeConstants.SEARCH_BILL_BY_PROVIDER));
//	}
}
