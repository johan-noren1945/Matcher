package trading.matchingengine.input;

import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.OrderBook;
import trading.matchingengine.logic.OrderFactory;
import trading.matchingengine.logic.User;
import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class EnterOrderReceiver {
    private final ReferenceDataRepository referenceDataRepository;
    private final OrderFactory orderFactory;

    public EnterOrderReceiver(ReferenceDataRepository referenceDataRepository, OrderFactory orderFactory) {
        this.referenceDataRepository = referenceDataRepository;
        this.orderFactory = orderFactory;
    }
    public void onEnterOrder (final EnterOrder enterOrder){
        OrderBook orderBook = referenceDataRepository.getOrderBook(enterOrder.getOrderBookId());
        User user = referenceDataRepository.getUser(enterOrder.getUserId());
        if (user != null && orderBook != null){
            Order order = orderFactory.createOrder(enterOrder, orderBook, user);
            orderBook.insertOrder(order);
        }
    }
}
