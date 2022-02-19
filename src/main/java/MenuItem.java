import java.util.Objects;

public class MenuItem {
    private String name;

    public MenuItem(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        MenuItem otherMenuItem = (MenuItem) other;
        System.out.println(otherMenuItem.name);
        System.out.println(name);
        return Objects.equals(name, otherMenuItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "MenuItem{" +
                "name='" + name + '\'' +
                '}';
    }
}
