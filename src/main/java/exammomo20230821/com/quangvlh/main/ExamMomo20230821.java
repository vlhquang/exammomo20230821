package exammomo20230821.com.quangvlh.main;

import java.util.Scanner;

import exammomo20230821.com.quangvlh.action.Action;
import exammomo20230821.com.quangvlh.common.Validator;
import exammomo20230821.com.quangvlh.config.ConfigUtil;
import exammomo20230821.com.quangvlh.dtos.ScannerValueInputDTO;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public class ExamMomo20230821 {

	private static final String EXIT = "EXIT";

	private static boolean handle(String valueInput) throws ExamMomoException {

		if (Validator.isNull(valueInput)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_VALUE_INPUT_IS_NULL);
		}
		
		ScannerValueInputDTO scannerValueInputDTO = new ScannerValueInputDTO().analytic(valueInput);
		if (EXIT.equals(scannerValueInputDTO.getKey())) {
			return false;
		} else {
			Action<?> action = ConfigUtil.getPayPalActionInstance().getActionByActionName(scannerValueInputDTO);
			action.handle(scannerValueInputDTO.getValue(), ConfigUtil.getPayPalActionInstance());
			return true;
		}
	}

	public static void main(String[] args) {
		System.out.println("App running...");
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String valueInput = scanner.nextLine();
			try {
				if (!handle(valueInput)) {
					scanner.close();
					System.out.println("Good bye!!!!");
					break;
				}
			} catch (ExamMomoException e) {
				System.out.println(e.getMessage());
			}
		}
	}
}
