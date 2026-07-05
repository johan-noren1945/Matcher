package trading.matchingengine.util;

import trading.matchingengine.logic.OrderBook;
import trading.matchingengine.logic.User;

import java.util.HashMap;

public class ReferenceDataRepository {
    private final HashMap< Integer, OrderBook> orderBooks = new HashMap();
    private final HashMap < Integer, User> users = new HashMap();

    public void addOrderBook (final OrderBook orderBook){
        orderBooks.put(orderBook.getOrderBookId(), orderBook);
    }
    public OrderBook getOrderBook (final int id){
        return orderBooks.get(id);
    }
    public void addUser (final User user){
        users.put(user.getUserId(), user);
    }
    public User getUser (final int id){
        return users.get(id);
    }
}
