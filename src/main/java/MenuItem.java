import java.util.Objects;

public class MenuItem {
    private String name;
    private Money price;

    public MenuItem(String name, Money price) {
        this.name = name;
        this.price = price;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MenuItem otherMenuItem = (MenuItem) other;
        return Objects.equals(name, otherMenuItem.name) &&
                Objects.equals(price, otherMenuItem.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
