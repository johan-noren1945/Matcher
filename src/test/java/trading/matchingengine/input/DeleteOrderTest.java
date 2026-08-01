package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;
import trading.matchingengine.message.OrderChanged;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DeleteOrderTest extends AbstractTester {
    //Lägg till metod där jag tar bort en order och en metod som felsöker på om order id:et inte finns
    @Test
    public void testDeleteOrder(){
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        OrderChanged orderChanged = senderMock.getOrderChanged(1);
        assertNotNull(orderChanged);
        verifyOrderChanged(orderChanged, ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, 10, TimeInForce.DAY);
        deleteOrder(orderChanged.getOrderId(), ORDER_BOOK_ID, USER_ID);
        //När man gör deleteOrder så skapar den en ny order
        orderChanged = senderMock.getOrderChanged(0);
        assertNull(orderChanged);

    }
    @Test
    public void testOrderNull(){
        OrderChanged orderChanged = senderMock.getOrderChanged(4);
        deleteOrder(orderChanged.getOrderId(), ORDER_BOOK_ID, USER_ID);
        assertNull(orderChanged);
    }
}
