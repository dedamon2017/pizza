package domain.Order;

import java.time.LocalDate;
import java.time.LocalTime;

import domain.Customer.Customer;


public class Order {
	private long id;
	private Customer customer;
	private LocalDate date;
	private LocalTime esimatedTime;
	private LocalTime deliveryTime;
	private String courier;
	private String address;
	private String phoneNumber;
	
	
	
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
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
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
