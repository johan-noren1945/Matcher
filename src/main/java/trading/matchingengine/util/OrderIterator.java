package trading.matchingengine.util;

import trading.matchingengine.logic.Order;

import java.util.Iterator;

public class OrderIterator implements Iterator<Order> {
    private Order currentOrder;
    private OrderList orderList;

    public void init(final OrderList orderList) {
        this.orderList = orderList;
        currentOrder = orderList.getFirst();
    }

    @Override
    public void remove() {
        currentOrder = currentOrder.getPrev();
        orderList.removeOrder(currentOrder);
    }

    @Override
    public Order next() {
        Order tempOrder = currentOrder;
        currentOrder = tempOrder.getNext();
        return tempOrder;
    }

    @Override
    public boolean hasNext() {
        if (currentOrder == null) {
            currentOrder = orderList.getFirst();
        }
        return currentOrder != null;
    }
}
