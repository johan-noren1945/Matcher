package trading.matchingengine.output;

import trading.matchingengine.message.OrderChanged;
import trading.matchingengine.message.Trade;

public interface Sender {
    void sendOrderChanged(OrderChanged orderChanged);

    void sendTrade(Trade trade);
}
