package exammomo20230821.com.quangvlh.dtos;

import exammomo20230821.com.quangvlh.common.StringPool;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public class ScannerValueInputDTO {
	private String key;
	private String value;
	public ScannerValueInputDTO() {
		super();
	}
	
	public ScannerValueInputDTO(String key, String value) {
		super();
		this.key = key;
		this.value = value;
	}

	public ScannerValueInputDTO analytic(String valueInput) throws ExamMomoException {
		String[] values = valueInput.split(StringPool.SPACE);
		if (values.length == 0) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_INPUT_IS_VALID);
		}
		this.key = values[0];
		this.value = valueInput.substring(this.key.length()).trim();
		
		return this;
	}
	
	public String getKey() {
		return key;
	}
	public String getValue() {
		return value;
	}
}
