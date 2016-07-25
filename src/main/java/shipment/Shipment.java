package shipment;

import java.time.LocalDateTime;

public class Shipment {
	private int ticket;
	private String orderId;
	private String address;
	private LocalDateTime recievedDateTime;
	private LocalDateTime deliveredDateTime;
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
	}

	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
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
