package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteOrderReceiverTest extends AbstractTester {
    //Lägg till metod där jag tar bort en order och en metod som felsöker på om order id:et inte finns
    @Test
    public void testDeleteOrder(){
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNotNull(order);
        deleteOrder(order.getOrderId(), ORDER_BOOK_ID, USER_ID);
        order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNull(order);

    }
    @Test
    public void testOrderNull(){
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(4);
        deleteOrder(order.getOrderId(), ORDER_BOOK_ID, USER_ID);
        assertNull(order);
    }
}
