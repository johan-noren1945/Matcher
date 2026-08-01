package trading.matchingengine.output;

import trading.matchingengine.message.OrderChanged;
import trading.matchingengine.message.TradeChanged;

public interface Sender {
    void sendOrderChanged(OrderChanged orderChanged);

    void sendTrade(TradeChanged trade);
}
