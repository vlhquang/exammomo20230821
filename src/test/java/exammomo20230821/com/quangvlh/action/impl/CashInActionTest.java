//package exammomo20230821.com.quangvlh.action.impl;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.assertTrue;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.Test;
//
//import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
//import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
//import exammomo20230821.com.quangvlh.exception.ExamMomoException;
//import exammomo20230821.com.quangvlh.paypal.PayPalAction;
//import exammomo20230821.com.quangvlh.paypal.PayPalActionFirstVersionImpl;
//
//public class CashInActionTest {
//
//	@Test
//	public void testHandleInputNumber() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "10000000");
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			Long result = action.handle(dto.getValue(), payPalAction);
//			assertEquals(10000000, result);
//		} catch (ExamMomoException e) {
//			assertTrue(false);
//		}
//	}
//	
//	@Test
//	public void testHandleInputString() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "AAAAAAAAA");
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			action.handle(dto.getValue(), payPalAction);
//		} catch (ExamMomoException e) {
//			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
//		}
//	}
//	
//	@Test
//	public void testHandleInputNumberString() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "100000AAAAA");
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			action.handle(dto.getValue(), payPalAction);
//		} catch (ExamMomoException e) {
//			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
//		}
//	}
//	
//	@Test
//	public void testHandleInputMaxLong() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", String.valueOf(Long.MAX_VALUE));
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			Long result = action.handle(dto.getValue(), payPalAction);
//			assertEquals(Long.MAX_VALUE, result);
//		} catch (ExamMomoException e) {
//			assertTrue(false);
//		}
//	}
//	
//	@Test
//	public void testHandleInputDoubleMaxLong() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", String.valueOf(Long.MAX_VALUE));
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			action.handle(dto.getValue(), payPalAction);
//			action.handle(dto.getValue(), payPalAction);
//			assertTrue(false);
//		} catch (ExamMomoException e) {
//			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
//		}
//	}
//	
//	@Test
//	public void testHandleInputSuccess() {
//		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
//		ScannerValueInputDTO dto = new ScannerValueInputDTO("CASH_IN", "500000");
//		try {
//			CashInAction action = (CashInAction) payPalAction.getActionByActionName(dto);
//			Long result = action.handle(dto.getValue(), payPalAction);
//			assertEquals(500000, result);
//		} catch (ExamMomoException e) {
//			assertTrue(false);
//		}
//	}
//}
