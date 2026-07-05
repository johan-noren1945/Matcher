package trading.matchingengine.logic;

import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.message.UpdateOrder;

public class OrderFactory {
    private long nextOrderId = 1;
    private long timeStamp = 1;


    public Order createOrder(final EnterOrder enterOrder, final OrderBook orderBook, final User user) {
        Order order = new Order();
        order.setEnterOrder(enterOrder);
        order.setOrderBook(orderBook);
        order.setUser(user);
        order.setOrderId(nextOrderId++);
        order.setSide(enterOrder.getSide());
        order.setPrice(enterOrder.getPrice());
        order.setOrderQuantity(enterOrder.getOrderQuantity());
        order.setOrderType(enterOrder.getOrderType());
        order.setTimeInForce(enterOrder.getTimeInForce());
        order.setTimeStamp(timeStamp++);

        return order;
    }

    public boolean updateOrder(final Order order, final UpdateOrder updateOrder) {
        boolean rerank = false;
        if (order.getPrice() != updateOrder.getPrice() || order.getOrderQuantity() != updateOrder.getOrderQuantity()) {
            rerank = true;
        }
        order.setPrice(updateOrder.getPrice());
        order.setOrderQuantity(updateOrder.getOrderQuantity());
        if (rerank) {
            order.setTimeStamp(timeStamp++);
        }
        return rerank;
    }
}
