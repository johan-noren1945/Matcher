package trading.matchingengine;

import org.junit.jupiter.api.BeforeEach;
import trading.matchingengine.input.DeleteOrderReceiver;
import trading.matchingengine.input.EnterOrderReceiver;
import trading.matchingengine.input.UpdateOrderReceiver;
import trading.matchingengine.logic.*;
import trading.matchingengine.message.*;
import trading.matchingengine.output.MessageSender;
import trading.matchingengine.util.ReferenceDataRepository;
import trading.matchingengine.util.SenderMock;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AbstractTester {

    protected int USER_ID = 1;
    protected int ORDER_BOOK_ID = 1;

    protected ReferenceDataRepository referenceDataRepository;
    protected EnterOrderReceiver enterOrderReceiver;
    protected UpdateOrderReceiver updateOrderReceiver;
    protected DeleteOrderReceiver deleteOrderReceiver;
    protected SenderMock senderMock;

    @BeforeEach
    public void setUp() {
        referenceDataRepository = new ReferenceDataRepository();
        OrderFactory orderFactory = new OrderFactory();
        MessageValidator messageValidator = new MessageValidator();
        senderMock = new SenderMock();
        MessageSender messageSender = new MessageSender(senderMock);
        Transaction transaction = new Transaction(messageSender);
        Matcher matcher = new Matcher(transaction);
        referenceDataRepository.addOrderBook(new OrderBook(ORDER_BOOK_ID, 100000));
        referenceDataRepository.addUser(new User(USER_ID));
        enterOrderReceiver = new EnterOrderReceiver(referenceDataRepository, orderFactory, messageValidator, matcher, transaction);
        updateOrderReceiver = new UpdateOrderReceiver(referenceDataRepository, orderFactory, messageValidator, matcher, transaction);
        deleteOrderReceiver = new DeleteOrderReceiver(referenceDataRepository, orderFactory, transaction);
        senderMock.clear();
    }

    protected void addOrderBook(final int orderBookId, final long maxQuantity) {
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
                               final long quantity) {
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
                               final int userId) {
        DeleteOrder deleteOrder = new DeleteOrder();
        deleteOrder.setOrderId(orderId);
        deleteOrder.setOrderBookId(orderBookId);
        deleteOrder.setUserId(userId);
        deleteOrderReceiver.onDeleteOrder(deleteOrder);
    }

    protected void verifyOrderChanged(final OrderChanged orderChanged,
                                      final int orderBookId,
                                      final int userId,
                                      final Side side,
                                      final long price,
                                      final long orderQuantity,
                                      final long leavesQuantity,
                                      final TimeInForce timeInForce) {
        assertEquals(orderBookId, orderChanged.getOrderBookId());
        assertEquals(userId, orderChanged.getUserId());
        assertEquals(side, orderChanged.getSide());
        assertEquals(price, orderChanged.getPrice());
        assertEquals(orderQuantity, orderChanged.getOrderQuantity());
        assertEquals(leavesQuantity, orderChanged.getLeavesQuantity());
        assertEquals(timeInForce, orderChanged.getTimeInForce());

    }
    //Lägg till Trade metoder och verifyTradeChanged
    protected void enterTrade(final long buyOrderId,
                              final long sellOrderId,
                              final int orderBookId,
                              final int buyUserId,
                              final int sellUserId,
                              final long tradePrice,
                              final long tradedQuantity){
        TradeChanged tradeChanged = new TradeChanged();
        tradeChanged.setBuyOrderId(buyOrderId);
        tradeChanged.setSellOrderId(sellOrderId);
        tradeChanged.setOrderBookId(orderBookId);
        tradeChanged.setBuyUserId(buyUserId);
        tradeChanged.setSellUserId(sellUserId);
        tradeChanged.setTradePrice(tradePrice);
        tradeChanged.setTradedQuantity(tradedQuantity);
        senderMock.sendTrade(tradeChanged);

    }

    protected void verifyTradeChanged(final TradeChanged tradeChanged,
                                      final long buyOrderId,
                                      final long sellOrderId,
                                      final int orderBookId,
                                      final int buyUserId,
                                      final int sellUserId,
                                      final long tradePrice,
                                      final long tradedQuantity){
        assertEquals(buyOrderId, tradeChanged.getBuyOrderId());
        assertEquals(sellOrderId, tradeChanged.getSellOrderId());
        assertEquals(orderBookId, tradeChanged.getOrderBookId());
        assertEquals(buyUserId, tradeChanged.getBuyUserId());
        assertEquals(sellUserId, tradeChanged.getSellUserId());
        assertEquals(tradePrice, tradeChanged.getTradePrice());
        assertEquals(tradedQuantity, tradeChanged.getTradedQuantity());
    }
}
