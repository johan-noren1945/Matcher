package trading.matchingengine.util;

import trading.matchingengine.logic.Order;

public class OrderIterator2 {

    private OrderList orderList;
    private Order currentOrder;

    public void init(OrderList orderList) {
        this.orderList = orderList;
    }

    public Order getFirst() {
        currentOrder = orderList.getFirst();
        return currentOrder;
    }

    public Order getNext() {
        if (currentOrder == null) {
            currentOrder = orderList.getFirst();
            return currentOrder;
        }
        currentOrder = currentOrder.getNext();
        return currentOrder;
    }

    public Order getLast() {
        currentOrder = orderList.getLast();
        return currentOrder;
    }

    public Order getCurrent() {
        return currentOrder;
    }

    public void remove() {
        Order previousOrder = currentOrder.getPrev();
        currentOrder.getOrderBook().removeOrder(currentOrder);
        currentOrder = previousOrder;
    }
}
