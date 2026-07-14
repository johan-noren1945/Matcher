package trading.matchingengine.logic;

import trading.matchingengine.message.EnterOrder;
import trading.matchingengine.message.UpdateOrder;

public class MessageValidator {

    public boolean validateEnterOrder(final EnterOrder enterOrder, final User user, final OrderBook orderBook) {
        boolean validate = false;

        if (enterOrder != null && user != null && orderBook != null) {
            if (enterOrder.getOrderQuantity() <= orderBook.getMaxQuantity()) {
                validate = true;
            }
        }

        return validate;
    }

    public boolean validateUpdateOrder(final UpdateOrder updateOrder, final Order order) {
        boolean validate = false;

        if (order != null) {
            if (updateOrder.getOrderQuantity() > 0
                    && updateOrder.getOrderQuantity() <= order.getOrderBook().getMaxQuantity()) {
                validate = true;
            }
        }
        return validate;
    }
}
