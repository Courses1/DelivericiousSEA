package domain;

import java.util.Objects;
import java.util.UUID;

public class MenuItem {
    private final UUID             id;
    private       String           name;
    private       Money            price;
    private       MenuItemCategory menuItemCategory;

    public MenuItem(String name, Money price, MenuItemCategory menuItemCategory) {
        this.menuItemCategory = menuItemCategory;
        this.id               = UUID.randomUUID();
        this.name             = name;
        this.price            = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass() != other.getClass())
            return false;
        MenuItem otherMenuItem = (MenuItem) other;
        return Objects.equals(id, otherMenuItem.id);
    }

    public Money getPrice() {
        return this.price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", price=" + price +
                ", menuCategory=" + menuItemCategory +
                '}';
    }

    public boolean belongsToCategory(MenuItemCategory menuItemCategory) {
        return this.menuItemCategory == menuItemCategory;
    }
}
