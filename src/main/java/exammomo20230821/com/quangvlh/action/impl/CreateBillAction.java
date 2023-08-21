package exammomo20230821.com.quangvlh.action.impl;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class CreateBillAction extends ActionAbstract<BillDTO> {

	public CreateBillAction(String actionName) {
		super(actionName);
	}

	@Override
	public BillDTO handle(String value, PayPalAction payPalAction) throws ExamMomoException {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);
		try {
			String[] values = value.split(";");
			if (values.length != 4) {
				throw new ExamMomoException(ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
			}
			BillDTO billDTO = payPalAction.createBill(values[0], values[1], values[2], values[3]);
			
			System.out.println("Create success bill " + billDTO.toString());
			
			return billDTO;
		} catch (NumberFormatException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
	}
}
