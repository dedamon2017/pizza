package domain.PizzaItem;

public class PizzaItem {
	private int id;
	private String title;
	private float price;
	private String preview;
	private String previewLarge;
	private String description;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	public String getPreview() {
		return preview;
	}
	public void setPreview(String preview) {
		this.preview = preview;
	}
	public String getPreviewLarge() {
		return previewLarge;
	}
	public void setPreviewLarge(String previewLarge) {
		this.previewLarge = previewLarge;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
