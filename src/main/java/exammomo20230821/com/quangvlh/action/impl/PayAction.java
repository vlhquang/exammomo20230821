package exammomo20230821.com.quangvlh.action.impl;

import java.util.ArrayList;
import java.util.List;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.common.Convertion;
import exammomo20230821.com.quangvlh.common.StringPool;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.enums.BillStatusEnum;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class PayAction extends ActionAbstract<Long> {

	public PayAction(String actionName) {
		super(actionName);
	}

	@Override
	public Long handle(String value, PayPalAction payPalAction) throws ExamMomoException {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);
		
		String[] values = value.split(StringPool.SPACE);
		int lent = values.length;
		int sumSub = 0;
		List<BillDTO> billDTOs = new ArrayList<>();
		if (lent == 1) {
			BillDTO billDTO = payPalAction.getBill(Convertion.convertStringToInteger(values[0]));
			if (billDTO == null) {
				throw new ExamMomoException(ErrorCodeEnum.ERROR_NOT_FOUND_BILL);
			}
			if (billDTO.getStatus()==BillStatusEnum.NOT_PAID) {
				sumSub += billDTO.getAmount();
				billDTOs.add(billDTO);
			}
		} else if (lent > 1) {
			BillDTO billDTO;
			for(int i = 0; i < values.length; i++) {
				billDTO = payPalAction.getBill(Convertion.convertStringToInteger(values[i]));
				if (billDTO == null) {
					throw new ExamMomoException(ErrorCodeEnum.ERROR_NOT_FOUND_BILL);
				}
				if (billDTO.getStatus()==BillStatusEnum.NOT_PAID) {
					sumSub += billDTO.getAmount();
					
					billDTOs.add(billDTO);
				}
			}
		}
		
		long moneyCurrent = payPalAction.updatePay(sumSub, billDTOs);
		System.out.println("Your current balance is: " + moneyCurrent);
		
		return moneyCurrent;
	}
}
