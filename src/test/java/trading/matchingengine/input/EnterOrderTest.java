package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Side;
import trading.matchingengine.logic.TimeInForce;
import trading.matchingengine.message.OrderChanged;

import static org.junit.jupiter.api.Assertions.*;

public class EnterOrderTest extends AbstractTester {

    @Test
    public void testEnterLimitOrder() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        OrderChanged orderChanged = senderMock.getOrderChanged(1);
        assertNotNull(orderChanged);
        verifyOrderChanged(orderChanged, ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, 10, TimeInForce.DAY);
    }
    //Ändra så de använder orderChanged istället för orderBook
    @Test
    public void testOrderQuantityTooLarge() {
        final int orderBookId = 2;
        addOrderBook(orderBookId, 100);
        enterLimitOrder(orderBookId, USER_ID, Side.BUY, 99, 101, TimeInForce.DAY);
        OrderChanged orderChanged = senderMock.getOrderChanged(1);
        assertNull(orderChanged);
    }

    @Test
    public void testOrderMatching() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 10, TimeInForce.DAY);
        OrderChanged orderChanged = senderMock.getOrderChanged(0);
        assertNull(orderChanged);
        //Inte null
        OrderChanged orderChanged2 = senderMock.getOrderChanged(1);
        assertNull(orderChanged2);
    }

    @Test
    public void testOrderQuantity() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 5, TimeInForce.DAY);

        OrderChanged buyOrderChanged = senderMock.getOrderChanged(1);
        assertNotNull(buyOrderChanged);
        verifyOrderChanged(buyOrderChanged, ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, 10, TimeInForce.DAY);
        //Den har fortfarande 10 som leaves
        assertEquals(5, buyOrderChanged.getLeavesQuantity());

        OrderChanged sellOrderChanged = senderMock.getOrderChanged(2);
        assertNull(sellOrderChanged);
    }

    @Test
    public void bestOrderPrice() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 102, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 102, 10, TimeInForce.DAY);

        OrderChanged orderChanged = senderMock.getOrderChanged(1);
        //Inte null
        assertNull(orderChanged);

        OrderChanged sell102 = senderMock.getOrderChanged(2);
        assertNotNull(sell102);
        verifyOrderChanged(sell102, ORDER_BOOK_ID, USER_ID, Side.SELL, 102, 10, 10, TimeInForce.DAY);
        assertEquals(10, sell102.getLeavesQuantity());
        //Problemet är att orderChanged skapar nya ordrar istället för att uppdatera gamla
    }


    //Lägg till metoder som testar olika matcher som order 1 som är buy är större än order 2
    //och då blir Leavesquantity 5 och vice versa
    //Testa med olika kvantiteter med på olika ordrar med annorlunda pris som kan bara matcha med de två bästa
    //Lägg till trades

}