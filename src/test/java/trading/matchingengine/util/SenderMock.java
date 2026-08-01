package trading.matchingengine.util;

import trading.matchingengine.message.OrderChanged;
import trading.matchingengine.message.TradeChanged;
import trading.matchingengine.output.Sender;

import java.util.ArrayList;
import java.util.List;

public class SenderMock implements Sender {
    private final List<OrderChanged> orderChangedList = new ArrayList<>();
    private final List<TradeChanged> tradeList = new ArrayList<>();

    @Override
    public void sendOrderChanged(OrderChanged orderChanged) {
        orderChangedList.add(orderChanged);
    }

    @Override
    public void sendTrade(TradeChanged trade) {
        tradeList.add(trade);
    }

    public OrderChanged getOrderChanged(final long orderId) {
        for (OrderChanged orderChanged : orderChangedList) {
            if (orderChanged.getOrderId() == orderId) {
                return orderChanged;
            }
        }
        return null;
    }

    public TradeChanged getTrade(final long tradeId) {
        for (TradeChanged trade : tradeList) {
            if (trade.getTradeId() == tradeId) {
                return trade;
            }
        }
        return null;
    }

    public void clear(){
        orderChangedList.clear();
        tradeList.clear();
    }
}
