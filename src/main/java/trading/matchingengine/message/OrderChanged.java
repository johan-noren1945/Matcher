package trading.matchingengine.message;

import trading.matchingengine.logic.OrderType;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;

public class OrderChanged {
    private long orderId;
    private int orderBookId;
    private int userId;
    private long price;
    private long orderQuantity;
    private long leavesQuantity;

    private Side side;
    private OrderType orderType;
    private TimeInForce timeInForce;

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public int getOrderBookId() {
        return orderBookId;
    }

    public void setOrderBookId(int orderBookId) {
        this.orderBookId = orderBookId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
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

    public long getLeavesQuantity() {
        return leavesQuantity;
    }

    public void setLeavesQuantity(long leavesQuantity) {
        this.leavesQuantity = leavesQuantity;
    }

    public Side getSide() {
        return side;
    }

    public void setSide(Side side) {
        this.side = side;
    }

    public OrderType getOrderType() {
        return orderType;
    }

    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }

    public TimeInForce getTimeInForce() {
        return timeInForce;
    }

    public void setTimeInForce(TimeInForce timeInForce) {
        this.timeInForce = timeInForce;
    }
}
