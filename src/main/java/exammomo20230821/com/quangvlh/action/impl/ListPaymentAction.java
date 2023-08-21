package exammomo20230821.com.quangvlh.action.impl;

import java.util.List;
import java.util.stream.Collectors;

import exammomo20230821.com.quangvlh.action.ActionAbstract;
import exammomo20230821.com.quangvlh.dtos.BillDTO;
import exammomo20230821.com.quangvlh.paypal.PayPalAction;

public class ListPaymentAction extends ActionAbstract<List<BillDTO>> {

	public ListPaymentAction(String actionName) {
		super(actionName);
	}

	@Override
	public List<BillDTO> handle(String value, PayPalAction payPalAction) {
		System.out.println("START CLASS " + getClass().getName() + "... WITH DATA: " + value);

		List<BillDTO> billDTOs = payPalAction.listPayment();
		List<BillDTO> billDTOSorts = billDTOs.stream().sorted((o1, o2) -> {
			return (int) (o1.getDueDate().getTime() - o2.getDueDate().getTime());
		}).collect(Collectors.toList());
		
		for (BillDTO billDTO : billDTOSorts) {
			System.out.println(billDTO.toString());
		}

		return billDTOSorts;
	}

}
