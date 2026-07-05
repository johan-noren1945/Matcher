package trading.matchingengine.input;

import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.OrderBook;
import trading.matchingengine.logic.OrderFactory;
import trading.matchingengine.logic.User;
import trading.matchingengine.message.UpdateOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class UpdateOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;

    public UpdateOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
    }

    public void onUpdateOrder(final UpdateOrder updateOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(updateOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(updateOrder.getUserId());
        if (user != null && orderBook != null) {
            Order order = orderBook.getOrder(updateOrder.getOrderId());
            if (order != null) {
                boolean rerank = orderFactory.updateOrder(order, updateOrder);
                if (rerank) {
                    orderBook.removeOrder(order);
                    orderBook.insertOrder(order);
                }
            }
        }
    }
}
