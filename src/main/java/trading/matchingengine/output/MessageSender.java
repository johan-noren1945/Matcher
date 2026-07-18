package trading.matchingengine.output;

import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Trade;
import trading.matchingengine.message.OrderChanged;

public class MessageSender {
    private final Sender sender;

    public MessageSender(Sender sender) {
        this.sender = sender;
    }

    public void sendOrderChanged(final Order order){
        OrderChanged orderChanged = new OrderChanged();

        sender.sendOrderChanged(orderChanged);

    }
    public void sendTrade(final Trade trade){

    }
}
