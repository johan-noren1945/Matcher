package trading.matchingengine.util;

import trading.matchingengine.logic.Order;

public class OrderList {
    private Order first;
    private Order last;
    private int count;

    public void insertOrder(final Order order) {
        if (first == null) {
            first = order;
            last = order;
        } else {
            Order tempOrder = first;
            boolean added = false;
            while (tempOrder != null) {
                if (order.getPrice() > tempOrder.getPrice()) {
                    order.setPrev(tempOrder.getPrev());
                    order.setNext(tempOrder);
                    added = true;
                    break;
                }
                tempOrder = tempOrder.getNext();
            }
            if (!added) {
                last.setNext(order);
                last = order;
            }
        }
        count++;
    }

    public void removeOrder(final Order order) {

        if (first == last) {
            first = null;
            last = null;
        } else if (order == first) {
            first = order.getNext();
        } else if (order == last) {
            last = order.getPrev();
        } else {
            order.getPrev().setNext(order.getNext());
            order.getNext().setPrev(order.getPrev());
        }
        order.setNext(null);
        order.setPrev(null);
        count--;
    }

    public Order getFirst() {
        return first;
    }

    public Order getLast() {
        return last;
    }

    public int getCount() {
        return count;
    }
}