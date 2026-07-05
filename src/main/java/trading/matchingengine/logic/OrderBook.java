package trading.matchingengine.logic;

import trading.matchingengine.util.OrderIterator;
import trading.matchingengine.util.OrderList;

import java.util.HashMap;

public class OrderBook {
    private final int orderBookId;
    private final OrderList buyOrders = new OrderList();
    private final OrderList sellOrders = new OrderList();
    private final HashMap < Long, Order > savedOrders = new HashMap();
    private final OrderIterator orderIterator = new OrderIterator();

    public OrderBook(int orderBookId) {
        this.orderBookId = orderBookId;
    }

    public int getOrderBookId() {
        return orderBookId;
    }

    public void insertOrder(final Order order) {
        if (order.getSide() == Side.BUY) {
            buyOrders.insertOrder(order);
        } else {
            sellOrders.insertOrder(order);
        }
        savedOrders.put(order.getOrderId(), order);
    }

    public Order removeOrder(final Order order ){
        if (order != null){
            if(order.getSide() == Side.BUY){
                buyOrders.removeOrder(order);
            }
            else {
                sellOrders.removeOrder(order);
            }
            savedOrders.remove(order.getOrderId());
        }
        return order;
    }

    public Order getOrder(long id){
        return savedOrders.get(id);
    }

    public OrderIterator getOrderIterator(Side side) {
        if(side == Side.BUY){
            orderIterator.init(buyOrders);
        }
        else {
            orderIterator.init(sellOrders);
        }
        return orderIterator;
    }
}
