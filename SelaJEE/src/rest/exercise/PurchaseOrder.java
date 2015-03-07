package rest.exercise;

public class PurchaseOrder {
	
	private String shipTo;
	private String billTo;
	private String desc;
	private float price;
	private int quantity;
	
	
	public PurchaseOrder()
	{
		
	}


	public String getShipTo() {
		return shipTo;
	}


	public void setShipTo(String shipTo) {
		this.shipTo = shipTo;
	}


	public String getBillTo() {
		return billTo;
	}


	public void setBillTo(String billTo) {
		this.billTo = billTo;
	}


	public String getDesc() {
		return desc;
	}


	public void setDesc(String desc) {
		this.desc = desc;
	}


	public float getPrice() {
		return price;
	}


	public void setPrice(float price) {
		this.price = price;
	}


	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	

}
