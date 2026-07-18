package trading.matchingengine.input;

import trading.matchingengine.logic.*;
import trading.matchingengine.message.UpdateOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class UpdateOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;
    private final MessageValidator messageValidator;
    private final Matcher matcher;
    private final Transaction transaction;

    public UpdateOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory, MessageValidator messageValidator, Matcher matcher, Transaction transaction) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
        this.messageValidator = messageValidator;
        this.matcher = matcher;
        this.transaction = transaction;
    }

    public void onUpdateOrder(final UpdateOrder updateOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(updateOrder.getOrderBookId());
        Order order = null;
        if (orderBook != null) {
            order = orderBook.getOrder(updateOrder.getOrderId());
        }
        if (messageValidator.validateUpdateOrder(updateOrder, order)) {
            boolean rerank = orderFactory.updateOrder(order, updateOrder);
            transaction.addOrder(order);
            if (rerank) {
                orderBook.removeOrder(order);
                matcher.MatchOrder(order);

                if (order.getLeavesQuantity() > 0) {
                    orderBook.insertOrder(order);
                }
            }
            transaction.commit();
        }
    }
}