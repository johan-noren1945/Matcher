package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;

import static org.junit.jupiter.api.Assertions.*;

public class EnterOrderReceiverTest extends AbstractTester {

    @Test
    public void testEnterLimitOrder() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNotNull(order);
        assertEquals(100, order.getPrice());
        assertEquals(10, order.getOrderQuantity());
    }
    @Test
    public void testOrderQuantityTooLarge(){
        final int orderBookId = 2;
        addOrderBook(orderBookId, 100);
        enterLimitOrder(orderBookId, USER_ID, Side.BUY, 99, 101, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(orderBookId).getOrder(1);
        assertNull(order);
    }
}