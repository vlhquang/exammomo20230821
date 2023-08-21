package exammomo20230821.com.quangvlh.enums;

public enum ErrorCodeEnum {
	ERROR_VALUE_INPUT_IS_NULL("Value input is null"), ERROR_VALUE_INPUT_IS_VALID("Value input is valid"),
	ERROR_ACTION_NOT_SUPPORT("Action name not support"),
	ERROR_VALUE_MONEY_WRONG_FORMAT_OR_VALUE_OVERFLOW_LONG("Money wrong format or overflow value long"),
	ERROR_DATA_CREATE_BILL_WRONG_FORMAT("Data create bill wrong format"),
	ERROR_NOT_ENOUGH_FUN("Sorry! Not enough fund to proceed with payment"),
	ERROR_NOT_FOUND_BILL("Sorry! Not found a bill with such id"),
	ERROR_DUE_DATE_BILL_IS_NULL("Due date of bill is null"), ERROR_AMOUNT_BILL_IS_NULL("Amount of bill is null"),
	ERROR_DUE_DATE_BILL_WRONG_FORMAT_DDMMYYYY("Due date of bill wrong format dd/MM/yyyy"),
	ERROR_VALUE_WRONG_FORMAT_OR_VALUE_OVERFLOW_INTEGER("Value wrong format or value overflow integer"),
	ERROR_YEAR_WRONG_FORMAT("Year wrong format"), ERROR_MONTH_WRONG_FORMAT("Month wrong format"),
	ERROR_DAY_WRONG_FORMAT("Day wrong format"),
	ERROR_DAY_WRONG_FORMAT_MAX_28("Day wrong format beacause day max value 28"),
	ERROR_DAY_WRONG_FORMAT_MAX_29("Day wrong format beacause day max value 29"),
	ERROR_SCHEDULE_ENOUGH_PARAM("Schedule action enough param"), ERROR_SCHEDULE_DATE_BILL_IS_NULL("Schedule date of bill is null"), ERROR_BILL_IS_PROCESSED("Bill is processed"), ERROR_BILL_IS_SCHEDULED("Bill is scheduled"), 
;

	private String message;

	private ErrorCodeEnum(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

}
