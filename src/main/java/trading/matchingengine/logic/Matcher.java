package trading.matchingengine.logic;

import trading.matchingengine.util.OrderIterator;

public class Matcher {
    public void MatchOrder(final Order order) {
        OrderIterator iterator = order.getOrderBook().getOrderIterator(order.getSide() == Side.BUY ? Side.SELL : Side.BUY);
        while (iterator.hasNext() && order.getOrderQuantity() > 0) {
            Order passiveOrder = iterator.next();
            if (CanMatch(order, passiveOrder)) {
                long matchQuantity = Math.min(order.getLeavesQuantity(), passiveOrder.getLeavesQuantity());
                order.setLeavesQuantity(order.getLeavesQuantity() - matchQuantity);
                passiveOrder.setLeavesQuantity(passiveOrder.getLeavesQuantity() - matchQuantity);
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
}
