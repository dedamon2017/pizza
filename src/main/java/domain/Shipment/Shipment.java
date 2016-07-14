package domain.Shipment;

import java.time.LocalDateTime;

public class Shipment {
	private int ticket;
	private int orderId;
	private LocalDateTime recievedDateTime;
	private LocalDateTime deliveredDateTime;
	public int getTicket() {
		return ticket;
	}
	public void setTicket(int ticket) {
		this.ticket = ticket;
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
}
