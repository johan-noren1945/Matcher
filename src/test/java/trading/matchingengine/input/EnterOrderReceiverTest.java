package trading.matchingengine.input;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.*;
import trading.matchingengine.util.ReferenceDataRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class EnterOrderReceiverTest extends AbstractTester {

    @Test
    public void testEnterLimitOrder() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNotNull(order);
        assertEquals(100, order.getPrice());
        assertEquals(10, order.getOrderQuantity());
    }
}