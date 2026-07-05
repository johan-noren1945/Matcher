package trading.matchingengine;

import org.junit.jupiter.api.BeforeEach;
import trading.matchingengine.input.EnterOrderReceiver;
import trading.matchingengine.logic.*;
import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class AbstractTester {

    protected int USER_ID = 1;
    protected int ORDER_BOOK_ID = 1;

    protected ReferenceDataRepository referenceDataRepository;
    protected EnterOrderReceiver enterOrderReceiver;

    @BeforeEach
    public void setUp() {
        referenceDataRepository = new ReferenceDataRepository();
        OrderFactory orderFactory = new OrderFactory();
        referenceDataRepository.addOrderBook(new OrderBook(ORDER_BOOK_ID));
        referenceDataRepository.addUser(new User(USER_ID));
        enterOrderReceiver = new EnterOrderReceiver(referenceDataRepository, orderFactory);
    }

    protected void enterLimitOrder(final int orderBookId,
                                   final int userId,
                                   final Side side,
                                   final long price,
                                   final long quantity,
                                   final TimeInForce timeInForce) {
        EnterOrder enterOrder = new EnterOrder();
        enterOrder.setOrderBookId(orderBookId);
        enterOrder.setUserId(userId);
        enterOrder.setSide(side);
        enterOrder.setPrice(price);
        enterOrder.setOrderQuantity(quantity);
        enterOrder.setOrderType(OrderType.LIMIT);
        enterOrder.setTimeInForce(timeInForce);
        enterOrderReceiver.onEnterOrder(enterOrder);
    }
}
