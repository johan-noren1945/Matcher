package trading.matchingengine.logic;

import trading.matchingengine.output.MessageSender;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final List<Order> orders = new ArrayList<>();
    private final List<Trade> trades = new ArrayList<>();
    private final MessageSender messageSender;

    public Transaction(MessageSender messageSender) {
        this.messageSender = messageSender;
    }

    public void addOrder(final Order order) {
        orders.add(order);
    }

    public void addTrade(final Trade trade) {
        trades.add(trade);
    }

    //Itererera orderChanged och trades här, likadant som i senderMock
    public void commit() {
        for (Order order : orders) {
            messageSender.sendOrderChanged(order);
        }

        for (Trade trade : trades) {
            messageSender.sendTrade(trade);
        }
    }
}
