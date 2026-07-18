package trading.matchingengine;

import org.junit.jupiter.api.BeforeEach;
import trading.matchingengine.input.DeleteOrderReceiver;
import trading.matchingengine.input.EnterOrderReceiver;
import trading.matchingengine.input.UpdateOrderReceiver;
import trading.matchingengine.logic.*;
import trading.matchingengine.message.DeleteOrder;
import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.message.UpdateOrder;
import trading.matchingengine.util.ReferenceDataRepository;

public class AbstractTester {

    protected int USER_ID = 1;
    protected int ORDER_BOOK_ID = 1;

    protected ReferenceDataRepository referenceDataRepository;
    protected EnterOrderReceiver enterOrderReceiver;
    protected UpdateOrderReceiver updateOrderReceiver;
    protected  DeleteOrderReceiver deleteOrderReceiver;

    @BeforeEach
    public void setUp() {
        referenceDataRepository = new ReferenceDataRepository();
        OrderFactory orderFactory = new OrderFactory();
        MessageValidator messageValidator = new MessageValidator();
        Transaction transaction = new Transaction();
        Matcher matcher = new Matcher(transaction);
        referenceDataRepository.addOrderBook(new OrderBook(ORDER_BOOK_ID, 100000));
        referenceDataRepository.addUser(new User(USER_ID));
        enterOrderReceiver = new EnterOrderReceiver(referenceDataRepository, orderFactory, messageValidator, matcher, transaction);
        updateOrderReceiver = new UpdateOrderReceiver(referenceDataRepository, orderFactory, messageValidator, matcher, transaction);
        deleteOrderReceiver = new DeleteOrderReceiver(referenceDataRepository, orderFactory, transaction);
    }
    protected void addOrderBook (final int orderBookId, final long maxQuantity){
        OrderBook orderBook = new OrderBook(orderBookId, maxQuantity);
        referenceDataRepository.addOrderBook(orderBook);
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
    protected void updateOrder(final long orderId,
                               final int orderBookId,
                               final int userId,
                               final long price,
                               final long quantity){
        UpdateOrder updateOrder = new UpdateOrder();
        updateOrder.setOrderId(orderId);
        updateOrder.setOrderBookId(orderBookId);
        updateOrder.setUserId(userId);
        updateOrder.setPrice(price);
        updateOrder.setOrderQuantity(quantity);
        updateOrderReceiver.onUpdateOrder(updateOrder);
    }
    protected void deleteOrder(final long orderId,
                                    final int orderBookId,
                                    final int userId){
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.setOrderId(orderId);
        deleteOrder.setOrderBookId(orderBookId);
        deleteOrder.setUserId(userId);
        deleteOrderReceiver.onDeleteOrder(deleteOrder);
    }
}
