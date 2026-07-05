package trading.matchingengine.logic;

import trading.matchingengine.message.EnterOrder;

public class Order {
    private OrderBook OrderBook;
    private User User;
    private EnterOrder EnterOrder;
    private long orderId;
    private long price;
    private long orderQuantity;
    private long leavesQuantity;

    private Side side;
    private OrderType orderType;
    private TimeInForce timeInForce;
    private long timeStamp;
    private Order next;
    private Order prev;


    public OrderBook getOrderBook() {
        return OrderBook;
    }

    public void setOrderBook(OrderBook orderBook) {
        OrderBook = orderBook;
    }

    public User getUser() {
        return User;
    }

    public void setUser(User user) {
        User = user;
    }

    public EnterOrder getEnterOrder() {
        return EnterOrder;
    }

    public void setEnterOrder(EnterOrder enterOrder) {
        EnterOrder = enterOrder;
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
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

    public long getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(long timeStamp) {
        this.timeStamp = timeStamp;
    }

    public Order getNext() {
        return next;
    }

    public void setNext(Order next) {
        this.next = next;
    }

    public Order getPrev() {
        return prev;
    }

    public void setPrev(Order prev) {
        this.prev = prev;
    }
}
