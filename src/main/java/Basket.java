import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<MenuItem> menuItems = new ArrayList<>();

    public void add(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public List<MenuItem> menuItems() {
        return this.menuItems;
    }
}
