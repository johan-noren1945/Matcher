package trading.matchingengine.util;

import trading.matchingengine.message.OrderChanged;
import trading.matchingengine.message.Trade;
import trading.matchingengine.output.Sender;

import java.util.ArrayList;
import java.util.List;

public class senderMock implements Sender {
    private final List<OrderChanged> orderChangedList = new ArrayList<>();
    private final List<Trade> tradeList = new ArrayList<>();
    @Override
    public void sendOrderChanged(OrderChanged orderChanged) {

    }

    @Override
    public void sendTrade(Trade trade) {

    }

    public OrderChanged getOrderChanged(final long orderId){
        for (OrderChanged orderChanged:orderChangedList){
            if (orderChanged.getOrderId() == orderId){
                return orderChanged;
            }
        }
        return null;
    }
    public Trade getTrade(final long tradeId){
        for (Trade trade:tradeList){
            if (trade.getTradeId() == tradeId){
                return trade;
            }
        }
        return null;
    }
}
