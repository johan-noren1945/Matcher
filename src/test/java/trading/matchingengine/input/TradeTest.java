package trading.matchingengine.input;

import org.junit.jupiter.api.Test;
import trading.matchingengine.AbstractTester;
import trading.matchingengine.message.TradeChanged;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class TradeTest extends AbstractTester {
    @Test
    public void testEnterTrade(){
        enterTrade(1, 2, ORDER_BOOK_ID, USER_ID, 2, 100, 10);
        //TradeId börjar vid 0 istället för 1
        TradeChanged tradeChanged = senderMock.getTrade(0);
        assertNotNull(tradeChanged);
        verifyTradeChanged(tradeChanged, tradeChanged.getBuyOrderId(), tradeChanged.getSellOrderId(), ORDER_BOOK_ID, USER_ID, tradeChanged.getSellUserId(), 100, 10);
    }
}
