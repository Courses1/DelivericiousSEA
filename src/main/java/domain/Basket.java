package domain;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<BasketItem> basketItems = new ArrayList<>();

    public void add(BasketItem basketItem) {
        basketItems.add(basketItem);
    }

    public void remove(MenuItem menuItemToRemove, int quantity) {
        var optionalBasketItem = this.basketItems.stream().filter(basketItem -> basketItem.getMenuItem().equals(menuItemToRemove))
                .findFirst();
        if (optionalBasketItem.isEmpty()) {
            return;
        }
        var basketItem = optionalBasketItem.get();
        basketItem.reduceQuantity(quantity);

        if (basketItem.getQuantity() == 0) {
            this.basketItems.remove(basketItem);
        }
    }

    public List<BasketItem> basketItems() {
        return this.basketItems;
    }
}
