package domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @Test
    void shouldAddMenuToBasket() {
        MenuItem tomatoSoup        = new MenuItem("Tomato Soup", Money.SGD(new BigDecimal("10.00")));
        MenuItem seaFoodSalad      = new MenuItem("Sea food salad", Money.SGD(new BigDecimal("12.00")));
        MenuItem chocolateIceCream = new MenuItem("Chocolate Ice Cream", Money.SGD(new BigDecimal("4.00")));

        BasketItem tomatoBasketItem            = new BasketItem(tomatoSoup);
        BasketItem seaFoodBasketItem           = new BasketItem(seaFoodSalad);
        BasketItem chocolateIceCreamBasketItem = new BasketItem(chocolateIceCream, 3);

        Basket basket = new Basket();
        basket.add(tomatoBasketItem);
        basket.add(seaFoodBasketItem);
        basket.add(chocolateIceCreamBasketItem);

        assertEquals(basket.basketItems(), List.of(tomatoBasketItem, seaFoodBasketItem, chocolateIceCreamBasketItem), "Menu Items are not equal");
    }

}
