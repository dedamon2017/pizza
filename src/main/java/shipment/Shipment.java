package shipment;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;



public class Shipment {

	private int id;
	private int orderId;
	private String address;
	@JsonSerialize(using = converter.CustomDateSerializer.class)
	@JsonDeserialize(using = converter.CustomDateDeserializer.class)
	private Date recievedDate;
	@JsonSerialize(using = converter.CustomDateSerializer.class)
	@JsonDeserialize(using = converter.CustomDateDeserializer.class)
	private Date deliveredDate;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public Date getRecievedDate() {
		return recievedDate;
	}

	public void setRecievedDate(Date recievedDate) {
		this.recievedDate = recievedDate;
	}

	public Date getDeliveredDate() {
		return deliveredDate;
	}

	public void setDeliveredDate(Date deliveredDate) {
		this.deliveredDate = deliveredDate;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
