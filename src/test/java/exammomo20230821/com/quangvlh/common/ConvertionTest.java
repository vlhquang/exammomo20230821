//package exammomo20230821.com.quangvlh.common;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.assertThrows;
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import java.util.Calendar;
//import java.util.Date;
//
//import org.junit.jupiter.api.Test;
//
//import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
//import exammomo20230821.com.quangvlh.exception.ExamMomoException;
//
//public class ConvertionTest {
//	@Test
//	public void testConvertStringToDate() {
//		try {
//			assertEquals(createDate(2023, 12, 23).getTime(), Convertion.convertStringToDate("23/12/2023").getTime());
//		} catch (ExamMomoException e) {
//			//TODO
//		}
//		
//		Throwable throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("32/12/2023").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT.getMessage(), throwable.getMessage());
//		
//		throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("312/12/2023").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT.getMessage(), throwable.getMessage());
//		
//		throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("31/13/2023").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_MONTH_WRONG_FORMAT.getMessage(), throwable.getMessage());
//		
//		throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("29/02/2023").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT_MAX_28.getMessage(), throwable.getMessage());
//		
//		throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("30/02/2024").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT.getMessage(), throwable.getMessage());
//		
//		throwable = assertThrows(ExamMomoException.class, () -> {
//			Convertion.convertStringToDate("").getTime();
//		});
//		assertEquals(ErrorCodeEnum.ERROR_DUE_DATE_BILL_IS_NULL.getMessage(), throwable.getMessage());
//	}
//	
//	private Date createDate(int year, int month, int day) {
//		Calendar cal = Calendar.getInstance();
//		cal.set(Calendar.DAY_OF_MONTH, day);
//		cal.set(Calendar.MONTH, month-1);
//		cal.set(Calendar.YEAR, year);
//		cal.set(Calendar.HOUR_OF_DAY, 0);
//		cal.set(Calendar.MINUTE, 0);
//		cal.set(Calendar.SECOND, 0);
//		cal.set(Calendar.MILLISECOND, 0);
//		return cal.getTime();
//	}
//}
