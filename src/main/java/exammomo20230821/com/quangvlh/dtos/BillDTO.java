package exammomo20230821.com.quangvlh.dtos;

import java.util.Date;

import exammomo20230821.com.quangvlh.common.Convertion;
import exammomo20230821.com.quangvlh.common.Validator;
import exammomo20230821.com.quangvlh.enums.BillStatusEnum;
import exammomo20230821.com.quangvlh.enums.ErrorCodeEnum;
import exammomo20230821.com.quangvlh.exception.ExamMomoException;

public class BillDTO {
	private int id;
	private String type;
	private long amount;
	private Date dueDate;
	private String provider;
	
	private BillStatusEnum status;
	private Date schedule;
	

	public BillDTO(int id, String type, String amount, String dueDate, String provider, BillStatusEnum status) throws ExamMomoException {
		super();
		this.id = id;
		this.type = checkIsNull(type);
		this.amount = checkAmount(amount);
		this.dueDate = convertStringToDate(dueDate);
		this.provider = checkIsNull(provider);
		this.status = status;
	}

	private String checkIsNull(String value) throws ExamMomoException {
		if (Validator.isNull(value)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DATA_CREATE_BILL_WRONG_FORMAT);
		}
		return value;
	}

	private long checkAmount(String amount) throws ExamMomoException {
		if (Validator.isNull(amount)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_AMOUNT_BILL_IS_NULL);
		}
		return Convertion.convertStringToLong(amount);
	}

	private Date convertStringToDate(String dueDate) throws ExamMomoException {
		if (Validator.isNull(dueDate)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_DUE_DATE_BILL_IS_NULL);
		}
		return Convertion.convertStringToDate(dueDate);
	}
	
	public BillDTO updateSchedule(String scheduleDate) throws ExamMomoException {
		if (Validator.isNull(scheduleDate)) {
			throw new ExamMomoException(ErrorCodeEnum.ERROR_SCHEDULE_DATE_BILL_IS_NULL);
		}
		setSchedule(Convertion.convertStringToDate(scheduleDate));
		setStatus(BillStatusEnum.SCHEDULER);
		return this;
	}
	
	public BillDTO updatePay() throws ExamMomoException {
		setStatus(BillStatusEnum.PROCESSED);
		return this;
	}

	public String getType() {
		return type;
	}

	public long getAmount() {
		return amount;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public String getProvider() {
		return provider;
	}

	public BillStatusEnum getStatus() {
		return status;
	}

	public Date getSchedule() {
		return schedule;
	}

	public void setStatus(BillStatusEnum status) {
		this.status = status;
	}

	public void setSchedule(Date schedule) {
		this.schedule = schedule;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "BillDTO [id=" + id + ", type=" + type + ", amount=" + amount + ", dueDate=" + dueDate + ", provider="
				+ provider + ", status=" + status + ", schedule=" + schedule + "]";
	}
}
