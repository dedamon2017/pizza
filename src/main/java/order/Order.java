package order;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;




public class Order {
	private int id;
	private String customerId;
	private LocalDate date;
	private LocalTime esimatedTime;
	private LocalTime deliveryTime;
	private String courier;
	private String address;
	private String phoneNumber;
	private boolean isCancel;
	private List<OrderLineItem> lineItemList;
	
	
	
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
	
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public LocalTime getEsimatedTime() {
		return esimatedTime;
	}
	public void setEsimatedTime(LocalTime esimatedTime) {
		this.esimatedTime = esimatedTime;
	}
	public LocalTime getDeliveryTime() {
		return deliveryTime;
	}
	public void setDeliveryTime(LocalTime deliveryTime) {
		this.deliveryTime = deliveryTime;
	}
	public String getCourier() {
		return courier;
	}
	public void setCourier(String courier) {
		this.courier = courier;
	}
	
}
