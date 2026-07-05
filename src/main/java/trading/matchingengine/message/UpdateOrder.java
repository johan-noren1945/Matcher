package trading.matchingengine.message;

public class UpdateOrder {
    private long orderId;
    private int userId;
    private int orderBookId;
    private long price;
    private long orderQuantity;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getOrderBookId() {
        return orderBookId;
    }

    public void setOrderBookId(int orderBookId) {
        this.orderBookId = orderBookId;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public long getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(long orderQuantity) {
        this.orderQuantity = orderQuantity;
    }
}
