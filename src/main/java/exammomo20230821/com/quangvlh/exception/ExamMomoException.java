package exammomo20230821.com.quangvlh.exception;

import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;

public class ExamMomoException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ErrorCodeEnum errorCodeEnum;

	public ExamMomoException(ErrorCodeEnum errorCodeEnum) {
		super();
		this.errorCodeEnum = errorCodeEnum;
	}
	
	@Override
	public String getMessage() {
		return errorCodeEnum.getMessage();
	}

	public ErrorCodeEnum getErrorCodeEnum() {
		return errorCodeEnum;
	}
}
