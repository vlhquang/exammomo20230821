package exammomo20230821.com.quangvlh.action.impl;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.common.Convertion;
import exammomo20230821.com.quangvlh.common.StringPool;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.enums.BillStatusEnum;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class ScheduleAction extends ActionAbstract<BillDTO> {

	public ScheduleAction(String actionName) {
		super(actionName);
	}

	@Override
	public BillDTO handle(String value, PayPalAction payPalAction) throws ExamMomoException {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);
		
		String[] values = value.split(StringPool.SPACE);
		int lent = values.length;
		
		if (lent != 2) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_SCHEDULE_ENOUGH_PARAM);
		}
		
		int id = Convertion.convertStringToInteger(values[0]);
		BillDTO billDTO = payPalAction.getBill(id);
		if (billDTO == null) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_NOT_FOUND_BILL);
		}
		if (BillStatusEnum.PROCESSED==billDTO.getStatus()) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_BILL_IS_PROCESSED);
		}
		if (BillStatusEnum.SCHEDULER==billDTO.getStatus()) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_BILL_IS_SCHEDULED);
		}
		
		String scheduleDate = values[1];
		billDTO = payPalAction.updateSchedule(scheduleDate, billDTO);
		
		System.out.println("Payment for bill id "+id+" is scheduled on " + billDTO.getSchedule());
		
		return billDTO;
	}

}
