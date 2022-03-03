package domain;

public class BasketItem {
    private final MenuItem menuItem;
    private final int      quantity;

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public BasketItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = 1;
    }
}
