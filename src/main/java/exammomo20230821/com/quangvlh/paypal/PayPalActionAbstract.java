package exammomo20230821.com.quangvlh.paypal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import exammomo20230821.com.quangvlh.action.Action;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
import exammomo20230821.com.quangvlh.enums.BillStatusEnum;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public abstract class PayPalActionAbstract implements PayPalAction {
	
	protected long money;
	protected Map<String, Action> mapActionByActionName = new HashMap<String, Action>();
	protected Map<Integer, BillDTO> mapBillDTOById = new HashMap<Integer, BillDTO>();
	
	protected Action getMapActionByActionName(String actionName) throws ExamMomoException {
		if (mapActionByActionName.isEmpty()) {
			initMapAction();
		}
		return mapActionByActionName.get(actionName);
	}
	
	protected void putMapActionByActionName(Action action) {
		mapActionByActionName.put(action.actionName(), action);
	}
	
	@Override
	public Object handlePayPalAction(ScannerValueInputDTO scannerValueInputDTO) throws ExamMomoException {
		Action action = getMapActionByActionName(scannerValueInputDTO.getKey());
		if (action == null) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_ACTION_NOT_SUPPORT);
		}
		return action.handle(scannerValueInputDTO.getValue(), this);
	}
	
	@Override
	public long increaMoney(long money) throws ExamMomoException {
		try {
			this.money = Math.addExact(this.money, money);
		} catch (ArithmeticException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
		return this.money;
	}
	
	@Override
	public long deincreaMoney(long money) throws ExamMomoException {
		try {
			this.money = Math.subtractExact(this.money, money);
		} catch (ArithmeticException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
		return this.money;
	}
	
	@Override
	public BillDTO createBill(String type, String amount, String dueDate, String provider) throws ExamMomoException {
		int id = mapBillDTOById.values().size() + 1;
		BillDTO billDTO = new BillDTO(id, type, amount, dueDate, provider, BillStatusEnum.NOT_PAID);
		mapBillDTOById.put(id, billDTO);
		return billDTO;
	}
	
	@Override
	public BillDTO getBill(int id) {
		// TODO Auto-generated method stub
		return mapBillDTOById.get(id);
	}
	
	@Override
	public long updatePay(long money, List<BillDTO> billDTOs) throws ExamMomoException {
		if (this.money < money) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_NOT_ENOUGH_FUN);
		}
		deincreaMoney(money);
		if (billDTOs != null) {
			for (BillDTO billDTO : billDTOs) {
				updateBill(billDTO.updatePay());
			}
		}
		return this.money;
	}
	
	@Override
	public List<BillDTO> listPayment() {
		return new ArrayList<BillDTO>(mapBillDTOById.values());
	}
	
	@Override
	public BillDTO updateSchedule(String scheduleDate, BillDTO billDTO) throws ExamMomoException {
		billDTO.updateSchedule(scheduleDate);
		return updateBill(billDTO);
	}
	
	public BillDTO updateBill(BillDTO billDTO) {
		mapBillDTOById.put(billDTO.getId(), billDTO);
		return billDTO;
	}

	protected abstract void initMapAction() throws ExamMomoException;
}
