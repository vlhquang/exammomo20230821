package exammomo20230821.com.quangvlh.common;

public class Validator {
	public static boolean isNull(String value) {
		if (value == null) {
			return true;
		}
		value = value.trim();
		if (value.length() == 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isNotNull(String value) {
		return !isNull(value);
	}
}
