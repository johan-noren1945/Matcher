package trading.matchingengine.output;

import trading.matchingengine.logic.Order;
import trading.matchingengine.logic.Trade;
import trading.matchingengine.message.OrderChanged;
import trading.matchingengine.message.TradeChanged;

public class MessageSender {
    private final Sender sender;

    public MessageSender(Sender sender) {
        this.sender = sender;
    }

    public void sendOrderChanged(final Order order) {
        OrderChanged orderChanged = new OrderChanged();
        orderChanged.setOrderId(order.getOrderId());
        orderChanged.setOrderBookId(order.getOrderBook().getOrderBookId());
        orderChanged.setUserId(order.getUser().getUserId());
        orderChanged.setPrice(order.getPrice());
        orderChanged.setOrderQuantity(order.getOrderQuantity());
        orderChanged.setLeavesQuantity(order.getLeavesQuantity());
        orderChanged.setSide(order.getSide());
        orderChanged.setOrderType(order.getOrderType());
        orderChanged.setTimeInForce(order.getTimeInForce());

        sender.sendOrderChanged(orderChanged);

    }

    public void sendTrade(final Trade trade) {
        TradeChanged tradeChanged = new TradeChanged();
        tradeChanged.setTradeId(trade.getTradeId());
        tradeChanged.setBuyOrderId(trade.getBuyOrder().getOrderId());
        tradeChanged.setSellOrderId(trade.getSellOrder().getOrderId());
        tradeChanged.setOrderBookId(trade.getBuyOrder().getOrderBook().getOrderBookId());
        tradeChanged.setBuyUserId(trade.getBuyOrder().getUser().getUserId());
        tradeChanged.setSellUserId(trade.getSellOrder().getUser().getUserId());
        tradeChanged.setTradePrice(trade.getTradePrice());
        tradeChanged.setTradedQuantity(trade.getTradedQuantity());

        sender.sendTrade(tradeChanged);

    }
}
