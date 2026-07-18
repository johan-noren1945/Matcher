package trading.matchingengine.logic;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
    private final List<Order> orders = new ArrayList<>();
    private final List <Trade> trades = new ArrayList<>();

    public void addOrder(final Order order){
        orders.add(order);
    }
    public void addTrade(final Trade trade){
        trades.add(trade);
    }

    public void commit(){

    }
}
