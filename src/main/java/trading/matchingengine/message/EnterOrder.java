package trading.matchingengine.message;

import trading.matchingengine.logic.OrderType;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;

public class EnterOrder {
    private int orderBookId;
    private int userId;
    private long price;
    private long orderQuantity;

    private Side side;
    private OrderType orderType;
    private TimeInForce timeInForce;

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
