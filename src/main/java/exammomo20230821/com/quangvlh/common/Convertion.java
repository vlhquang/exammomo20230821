package exammomo20230821.com.quangvlh.common;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public class Convertion {
	private static int[] days = {31,29,31,30,31,30,31,31,30,31,30,31};
	private static final int MIN_YEAR = 1900;
	private static final int MAX_YEAR = 2999;
	private static final int MIN_MONTH = 1;
	private static final int MAX_MONTH = 12;
	
	public static long convertStringToLong(String value) throws ExamMomoException {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG);
		}
	}
	
	public static int convertStringToInteger(String value) throws ExamMomoException {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_WRONG_FORMAT_OR_VALUE_OVERFLOW_INTEGER);
		}
	}
	
	public static Date convertStringToDate(String dueDate) throws ExamMomoException {
		if (Validator.isNull(dueDate)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DUE_DATE_BILL_IS_NULL);
		}
		String[] values = dueDate.split("/");
		if (values.length != 3) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DUE_DATE_BILL_WRONG_FORMAT_DDMMYYYY);
		}
		
		int day = convertStringToInteger(values[0]);
		int month = convertStringToInteger(values[1]);
		int year = convertStringToInteger(values[2]);
		
		if (year < MIN_YEAR || year > MAX_YEAR) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_YEAR_WRONG_FORMAT);
		}
		if (month < MIN_MONTH || month > MAX_MONTH) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_MONTH_WRONG_FORMAT);
		}
		if (day < 1 || day > days[month-1]) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT);
		}
		
		if (month == 2 && (isYearNormal(year) && day > days[month-1]-1)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DAY_WRONG_FORMAT_MAX_28);
		}
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			return dateFormat.parse(dueDate);
		} catch (ParseException e) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DUE_DATE_BILL_WRONG_FORMAT_DDMMYYYY);
		}
	}

	private static boolean isYearNormal(int year) {
		//1600 % 100 = 16 => nhuan, 1800 % 100 = 18 => thuong
		int num = year % 100;
		if (num > 0) {
			return num % 4 != 0;
		} else {
			return ((year % 4) != 0);  
		}
	}
}
