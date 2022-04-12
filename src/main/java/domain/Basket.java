package domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Basket {
    private List<BasketItem> basketItems = new ArrayList<>();
    private UUID             id;

    private static final int BASKET_QUANTITY_LIMIT = 100;

    public Basket() {
        this.id = UUID.randomUUID();
    }

    public void add(BasketItem basketItem) throws BasketQuantityExceedException {
        if (totalQuantityWithAdditionTo(basketItem) > BASKET_QUANTITY_LIMIT) {
            throw new BasketQuantityExceedException();
        }
        basketItems.add(basketItem);
    }

    private int totalQuantityWithAdditionTo(BasketItem basketItem) {
        return totalQuantity() + basketItem.getQuantity();
    }

    public UUID id() {
        return this.id;
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

    public Money totalPrice() {
        return basketItems.stream()
                .map(BasketItem::price)
                .reduce(Money.zeroSGD(), Money::add);
    }

    private int totalQuantity() {
        return basketItems.stream()
                .map(BasketItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    public Basket repeat() {
        Basket clone = new Basket();
        clone.basketItems.addAll(this.basketItems);
        return clone;
    }
}
