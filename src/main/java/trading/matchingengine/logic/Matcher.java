package trading.matchingengine.logic;

import trading.matchingengine.util.OrderIterator;

public class Matcher {
    private final Transaction transaction;
    private long tradeId = 1;

    public Matcher(Transaction transaction) {
        this.transaction = transaction;
    }

    public void MatchOrder(final Order order) {
        OrderIterator iterator = order.getOrderBook().getOrderIterator(order.getSide() == Side.BUY ? Side.SELL : Side.BUY);
        while (iterator.hasNext() && order.getLeavesQuantity() > 0) {
            Order passiveOrder = iterator.next();
            if (CanMatch(order, passiveOrder)) {
                long matchQuantity = Math.min(order.getLeavesQuantity(), passiveOrder.getLeavesQuantity());
                order.setLeavesQuantity(order.getLeavesQuantity() - matchQuantity);
                passiveOrder.setLeavesQuantity(passiveOrder.getLeavesQuantity() - matchQuantity);
                transaction.addOrder(passiveOrder);
                createTrade(order, passiveOrder, passiveOrder.getPrice(), matchQuantity);
                if (passiveOrder.getLeavesQuantity() == 0) {
                    iterator.remove();
                }
            } else {
                break;
            }
        }

    }

    private boolean CanMatch(final Order order, final Order passiveOrder) {
        boolean canMatch = false;
        if (order.getSide() == Side.BUY) {
            canMatch = order.getPrice() >= passiveOrder.getPrice();
        } else {
            canMatch = order.getPrice() <= passiveOrder.getPrice();
        }
        return canMatch;
    }

    private void createTrade(final Order order, final Order passiveOrder, final long tradePrice, final long tradedQuantity) {
        Trade trade = new Trade();
        trade.setTradeId(tradeId++);
        if (order.getSide() == Side.BUY) {
            trade.setBuyOrder(order);
            trade.setSellOrder(passiveOrder);
        } else {
            trade.setBuyOrder(passiveOrder);
            trade.setSellOrder(order);
        }
        trade.setTradePrice(tradePrice);
        trade.setTradedQuantity(tradedQuantity);
        transaction.addTrade(trade);
    }
}
