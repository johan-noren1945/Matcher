package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;
import trading.matchingengine.message.OrderChanged;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class UpdateOrderTest extends AbstractTester {
    @Test
    public void testUpdateOrder() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        OrderChanged orderChanged = senderMock.getOrderChanged(1);
        assertNotNull(orderChanged);
        verifyOrderChanged(orderChanged, ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, 10, TimeInForce.DAY);
        updateOrder(orderChanged.getOrderId(), ORDER_BOOK_ID, USER_ID, 99, 8);
        //Ordern updateras inte, istället skapas en ny order med ett annat id
        assertEquals(99, orderChanged.getPrice());
        assertEquals(8, orderChanged.getOrderQuantity());
    }
}
