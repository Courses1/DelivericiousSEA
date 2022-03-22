package domain;

import java.util.Objects;
import java.util.UUID;

public class MenuItem {
    private final UUID   id;
    private       String name;
    private       Money  price;

    public MenuItem(String name, Money price) {
        this.id    = UUID.randomUUID();
        this.name  = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MenuItem otherMenuItem = (MenuItem) other;
        return Objects.equals(id, otherMenuItem.id);
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
                '}';
    }
}
