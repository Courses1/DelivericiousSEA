package domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> basketItems = new ArrayList<>();

    public void add(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    public List<BasketItem> basketItems() {
        return this.basketItems;
    }
}
