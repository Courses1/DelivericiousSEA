package domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

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

        basket.remove(chocolateIceCream, 1);

        BasketItem expectedChocolateBasketItem = new BasketItem(chocolateIceCream, 2);
        assertEquals(basket.basketItems(), List.of(tomatoBasketItem, seaFoodBasketItem, expectedChocolateBasketItem), "Menu Items are not equal");

        assertEquals(Money.SGD(new BigDecimal("30.00")), basket.totalPrice(), "basket total price is not correct!");

        Basket repeatBasket = basket.repeat();
        assertEquals(repeatBasket.basketItems(), basket.basketItems(), "Basket items should be equal");
        assertNotEquals(repeatBasket.id(), basket.id(), "Basket Id should not be equal");
        assertNotEquals(repeatBasket, basket, "Basket Id should not be equal");
    }

}
