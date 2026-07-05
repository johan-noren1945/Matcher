package trading.matchingengine.logic;

public class User {
    private final int userId;
    private String name;

    public User(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
