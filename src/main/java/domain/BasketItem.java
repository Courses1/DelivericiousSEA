package domain;

import java.math.BigDecimal;
import java.util.Objects;

public class BasketItem {
    private final MenuItem menuItem;
    private       int      quantity;

    public BasketItem(MenuItem menuItem, int quantity) {
        this.menuItem = menuItem;
        this.quantity = quantity;
    }

    public BasketItem(MenuItem menuItem) {
        this.menuItem = menuItem;
        this.quantity = 1;
    }

    public MenuItem getMenuItem() {
        return menuItem;
    }

    public int getQuantity() {
        return quantity;
    }

    public void reduceQuantity(int by) {
        this.quantity -= by;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasketItem that = (BasketItem) o;
        return quantity == that.quantity && Objects.equals(menuItem, that.menuItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(menuItem, quantity);
    }

    @Override
    public String toString() {
        return "BasketItem{" +
                "menuItem=" + menuItem +
                ", quantity=" + quantity +
                '}';
    }

    public Money price() {
        return menuItem.getPrice().multiplyBy(new BigDecimal(this.quantity));
    }
}
