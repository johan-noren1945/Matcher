package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UpdateOrderReceiverTest extends AbstractTester {
    @Test
    public void testUpdateOrder() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNotNull(order);
        updateOrder(order.getOrderId(), ORDER_BOOK_ID, USER_ID, 99, 8);
        assertEquals(99, order.getPrice());
        assertEquals(8, order.getOrderQuantity());
    }
}
