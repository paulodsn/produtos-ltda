package database.model;

public class OrderDetail {
    private Number productId;
    private Number orderId;
    private Number quantity;

    public Number getProductId() {
        return productId;
    }

    public void setProductId(Number productId) {
        this.productId = productId;
    }

    public Number getOrderId() {
        return orderId;
    }

    public void setOrderId(Number orderId) {
        this.orderId = orderId;
    }

    public Number getQuantity() {
        return quantity;
    }

    public void setQuantity(Number quantity) {
        this.quantity = quantity;
    }
}
