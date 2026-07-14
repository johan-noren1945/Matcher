package trading.matchingengine.input;

import trading.matchingengine.logic.MessageValidator;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.OrderBook;
import trading.matchingengine.logic.OrderFactory;
import trading.matchingengine.message.UpdateOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class UpdateOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;
    private final MessageValidator messageValidator;

    public UpdateOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory, MessageValidator messageValidator) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
        this.messageValidator = messageValidator;
    }

    public void onUpdateOrder(final UpdateOrder updateOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(updateOrder.getOrderBookId());
        Order order = null;
        if (orderBook != null) {
            order = orderBook.getOrder(updateOrder.getOrderId());
        }
        if (messageValidator.validateUpdateOrder(updateOrder, order)) {

            boolean rerank = orderFactory.updateOrder(order, updateOrder);
            if (rerank) {
                orderBook.removeOrder(order);
                orderBook.insertOrder(order);
            }
        }
    }
}