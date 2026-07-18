package trading.matchingengine.input;

import trading.matchingengine.logic.*;
import trading.matchingengine.message.DeleteOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class DeleteOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final Transaction transaction;

    public DeleteOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory, Transaction transaction) {
        this.referenceDataRepository = referenceDataRepository;
        this.transaction = transaction;
    }

    public void onDeleteOrder(final DeleteOrder deleteOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(deleteOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(deleteOrder.getUserId());
        if (user != null && orderBook != null) {
            Order order = orderBook.getOrder(deleteOrder.getOrderId());
            transaction.addOrder(order);
            if (order != null) {
                if (order.getOrderBook().getOrderBookId() == deleteOrder.getOrderBookId() && order.getUser().getUserId() == deleteOrder.getUserId()) {
                    orderBook.removeOrder(order);
                }
            }
            transaction.commit();
        }
    }
}
