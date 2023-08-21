package exammomo20230821.com.quangvlh.paypal;

import java.util.List;

import exammomo20230821.com.quangvlh.action.Action;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public interface PayPalAction {

	Action<?> getActionByActionName(ScannerValueInputDTO scannerValueInputDTO) throws ExamMomoException;
	long increaMoney(long money) throws ExamMomoException;
	long deincreaMoney(long money) throws ExamMomoException;
	BillDTO createBill(String type, String amount, String dueDate, String provider) throws ExamMomoException;
	BillDTO getBill(int id);
	long updatePay(long money, List<BillDTO> billDTOs) throws ExamMomoException;
	BillDTO updateSchedule(String scheduleDate, BillDTO billDTO) throws ExamMomoException;
	List<BillDTO> listPayment();
}
