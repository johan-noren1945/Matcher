package trading.matchingengine.input;

import trading.matchingengine.logic.*;
import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class EnterOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;
    private final MessageValidator messageValidator;
    private final Matcher matcher;
    private final Transaction transaction;

    public EnterOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory, MessageValidator messageValidator, Matcher matcher, Transaction transaction) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
        this.messageValidator = messageValidator;
        this.matcher = matcher;
        this.transaction = transaction;
    }

    public void onEnterOrder(final EnterOrder enterOrder) {
        OrderBook orderBook = referenceDataRepository.getOrderBook(enterOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(enterOrder.getUserId());
        if (messageValidator.validateEnterOrder(enterOrder, user, orderBook)) {
            Order order = orderFactory.createOrder(enterOrder, orderBook, user);
            matcher.MatchOrder(order);
            transaction.addOrder(order);
            if (order.getLeavesQuantity() > 0) {
                orderBook.insertOrder(order);
            }
            transaction.commit();
        }
    }
}
