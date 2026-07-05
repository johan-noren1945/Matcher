package trading.matchingengine.input;

import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.OrderBook;
import trading.matchingengine.logic.OrderFactory;
import trading.matchingengine.logic.User;
import trading.matchingengine.message.DeleteOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class DeleteOrderReciever {
    private final ReferenceDataRepository referenceDataRepository;

    public DeleteOrderReciever(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory) {
        this.referenceDataRepository = referenceDataRepository;
    }

    public void onDeleteOrder(final DeleteOrder deleteOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(deleteOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(deleteOrder.getUserId());
        if (user != null && orderBook != null) {
            Order order = orderBook.getOrder(deleteOrder.getOrderId());
            if (order != null) {
                if (order.getOrderBook().getOrderBookId() == deleteOrder.getOrderBookId() && order.getUser().getUserId() == deleteOrder.getUserId()) {
                    orderBook.removeOrder(order);
                }
            }
        }
    }
}
