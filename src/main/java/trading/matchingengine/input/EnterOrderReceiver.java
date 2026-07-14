package trading.matchingengine.input;

import trading.matchingengine.logic.*;
import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class EnterOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;
    private final MessageValidator messageValidator;

    public EnterOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory, MessageValidator messageValidator) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
        this.messageValidator = messageValidator;
    }

    public void onEnterOrder (final EnterOrder enterOrder){
        OrderBook orderBook = referenceDataRepository.getOrderBook(enterOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(enterOrder.getUserId());
        if (messageValidator.validateEnterOrder(enterOrder, user, orderBook)){
            Order order = orderFactory.createOrder(enterOrder, orderBook, user);
            orderBook.insertOrder(order);
        }
    }
}
