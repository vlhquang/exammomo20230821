package exammomo20230821.com.quangvlh.action.impl;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;
import exammomo20230821.com.quangvlh.paypal.PayPalActionFirstVersionImpl;

public class CreateBillActionTest {
	@Test
	public void testHandleInputIsNull() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CREATE_BILL", "");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
	}
	
	@Test
	public void testHandleInputWrongFormat() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CREATE_BILL", "200000/23/12/2023");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "200000;23/12/2023");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", ";200000;23/12/2023;");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;200000/23/12/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
	}
	
	@Test
	public void testHandleInputWrongFormatAmount() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CREATE_BILL", "type1;200000fgf;23/12/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;fdfdsfdsf;23/12/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
	}
	
	@Test
	public void testHandleInputWrongFormatDueDate() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CREATE_BILL", "type1;20000;32/12/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;20000;31/13/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_MONTH_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;20000;31/12/3000;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_YEAR_WRONG_FORMAT);
		}
		
		dto = new ScannerValueInputDTO("CREATE_BILL", "type1;20000;29/02/2023;VNPT");
		try {
			payPalAction.handlePayPalAction(dto);
			assertTrue(false);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertEquals(e.getErrorCodeEnum(), ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT_MAX_28);
		}
	}
	
	@Test
	public void testHandleInputSuccess() {
		PayPalAction payPalAction = new PayPalActionFirstVersionImpl();
		ScannerValueInputDTO dto = new ScannerValueInputDTO("CREATE_BILL", "type1;20000;31/12/2023;VNPT");
		try {
			BillDTO result = (BillDTO) payPalAction.handlePayPalAction(dto);
			assertTrue(result.getId() > 0);
		} catch (ExamMomoException e) {
			System.out.println(e.getMessage());
			assertTrue(false);
		}
	}
}
