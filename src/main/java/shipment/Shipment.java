package shipment;

import java.time.LocalDateTime;

public class Shipment {
	private int id;
	private int orderId;
	private String address;
	private LocalDateTime recievedDateTime;
	private LocalDateTime deliveredDateTime;

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

	public LocalDateTime getRecievedDateTime() {
		return recievedDateTime;
	}

	public void setRecievedDateTime(LocalDateTime recievedDateTime) {
		this.recievedDateTime = recievedDateTime;
	}

	public LocalDateTime getDeliveredDateTime() {
		return deliveredDateTime;
	}

	public void setDeliveredDateTime(LocalDateTime deliveredDateTime) {
		this.deliveredDateTime = deliveredDateTime;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
}
