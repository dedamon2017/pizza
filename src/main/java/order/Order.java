package order;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

public class Order {
	private int id;
	private int customerId;
	private String customerName;
	private String address;
	private String phoneNumber;
	@JsonSerialize(using = converter.CustomDateSerializer.class)
	@JsonDeserialize(using = converter.CustomDateDeserializer.class)
	private Date recievedDate;
	@JsonSerialize(using = converter.CustomDateSerializer.class)
	@JsonDeserialize(using = converter.CustomDateDeserializer.class)
	private Date deliveryTime;
	private String courier;
	private boolean isCancel;
	private List<OrderLineItem> lineItemList;
	private BigDecimal sum;

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public Date getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(Date deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public BigDecimal getSum() {
		return sum;
	}

	public void setSum(BigDecimal sum) {
		this.sum = sum;
	}

	public List<OrderLineItem> getLineItemList() {
		return lineItemList;
	}

	public void setLineItemList(List<OrderLineItem> lineItemList) {
		this.lineItemList = lineItemList;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

}
