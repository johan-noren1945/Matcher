package trading.matchingengine.logic;

public class Trade {
    private long tradeId;
    private Order buyOrder;
    private Order sellOrder;
    private long tradePrice;
    private long tradedQuantity;

    public long getTradeId() {
        return tradeId;
    }

    public void setTradeId(long tradeId) {
        this.tradeId = tradeId;
    }

    public Order getBuyOrder() {
        return buyOrder;
    }

    public void setBuyOrder(Order buyOrder) {
        this.buyOrder = buyOrder;
    }

    public Order getSellOrder() {
        return sellOrder;
    }

    public void setSellOrder(Order sellOrder) {
        this.sellOrder = sellOrder;
    }

    public long getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(long tradePrice) {
        this.tradePrice = tradePrice;
    }

    public long getTradedQuantity() {
        return tradedQuantity;
    }

    public void setTradedQuantity(long tradedQuantity) {
        this.tradedQuantity = tradedQuantity;
    }
}
