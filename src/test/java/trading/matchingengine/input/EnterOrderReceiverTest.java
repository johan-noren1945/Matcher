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
    public void testOrderQuantityTooLarge() {
        final int orderBookId = 2;
        addOrderBook(orderBookId, 100);
        enterLimitOrder(orderBookId, USER_ID, Side.BUY, 99, 101, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(orderBookId).getOrder(1);
        assertNull(order);
    }

    @Test
    public void testOrderMatching() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 10, TimeInForce.DAY);
        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNull(order);
        Order order2 = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(2);
        assertNull(order2);
    }

    @Test
    public void testOrderQuantity() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 5, TimeInForce.DAY);

        Order buyOrder = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNotNull(buyOrder);
        assertEquals(5, buyOrder.getLeavesQuantity());

        Order sellOrder = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(2);
        assertNull(sellOrder);
    }

    @Test
    public void bestOrderPrice() {
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 102, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.SELL, 100, 10, TimeInForce.DAY);
        enterLimitOrder(ORDER_BOOK_ID, USER_ID, Side.BUY, 102, 10, TimeInForce.DAY);

        Order order = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(1);
        assertNull(order);

        Order sell102 = referenceDataRepository.getOrderBook(ORDER_BOOK_ID).getOrder(2);
        assertNotNull(sell102);
        assertEquals(10, sell102.getLeavesQuantity());
    }
    //Lägg till metoder som testar olika matcher som order 1 som är buy är större än order 2
    //och då blir Leavesquantity 5 och vice versa
    //Testa med olika kvantiteter med på olika ordrar med annorlunda pris som kan bara matcha med de två bästa

}