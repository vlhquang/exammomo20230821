package exammomo20230821.com.quangvlh.action.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exammomo20230821.com.quangvlh.common.StringPool;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;
import exammomo20230821.com.quangvlh.paypal.PayPalActionFirstVersionImpl;

public class PayActionTest {
	@Test
	public void testHandleInputPayNotFoundId() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("PAY", "1");
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			action.handle(dto.getValue(), payPalAction);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_NOT_FOUND_BILL);
		}

		dto = new ScannerValueInputDTO("PAY", "1 2");
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			action.handle(dto.getValue(), payPalAction);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_NOT_FOUND_BILL);
		}
	}

	@Test
	public void testHandleInputPayNotEnough() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "500000");
		try {
			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(500000, result);
		} catch (ExamMomoException e) {
			assertTrue(false);
		}

		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;1000000;31/12/2023;VNPT");
		int id = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id = result.getId();
			assertTrue(id > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("PAY", String.valueOf(id));
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			action.handle(dto.getValue(), payPalAction);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_NOT_ENOUGH_FUN);
		}
	}
	
	@Test
	public void testHandleInputPayNotEnoughPayTwo() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "500000");
		try {
			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(500000, result);
		} catch (ExamMomoException e) {
			assertTrue(false);
		}

		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;500000;31/12/2023;VNPT");
		int id = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id = result.getId();
			assertTrue(id > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;1000000;31/12/2023;VNPT");
		int id2 = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id2 = result.getId();
			assertTrue(id2 > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("PAY", String.valueOf(id).concat(StringPool.SPACE).concat(String.valueOf(id2)));
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			action.handle(dto.getValue(), payPalAction);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_NOT_ENOUGH_FUN);
		}
	}
	
	@Test
	public void testHandleInputPaySuccess() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "700000");
		try {
			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(700000, result);
		} catch (ExamMomoException e) {
			assertTrue(false);
		}

		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;500000;31/12/2023;VNPT");
		int id = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id = result.getId();
			assertTrue(id > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("PAY", String.valueOf(id));
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(200000, result);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
	
	@Test
	public void testHandleInputPaySuccessTwoBill() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "700000");
		try {
			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(700000, result);
		} catch (ExamMomoException e) {
			assertTrue(false);
		}

		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;500000;31/12/2023;VNPT");
		int id = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id = result.getId();
			assertTrue(id > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;100000;31/12/2023;VNPT");
		int id2 = 0;
		try {
			CreateBillAction action = (CreateBillAction) payPalAction.getActionByActionName(dto);
			BillDTO result = action.handle(dto.getValue(), payPalAction);
			id2 = result.getId();
			assertTrue(id2 > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
		
		dto = new ScannerValueInputDTO("PAY", String.valueOf(id).concat(StringPool.SPACE).concat(String.valueOf(id2)));
		try {
			PayAction action = (PayAction) payPalAction.getActionByActionName(dto);
			long result = action.handle(dto.getValue(), payPalAction);
			assertEquals(100000, result);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
}
