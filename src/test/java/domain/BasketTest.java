package domain;

import org.junit.jupiter.api.Test;
import repository.BasketRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {
    private MenuItem tomatoSoup        = new MenuItem("Tomato Soup", Money.SGD(new BigDecimal("10.00")));
    private MenuItem seaFoodSalad      = new MenuItem("Sea food salad", Money.SGD(new BigDecimal("12.00")));
    private MenuItem chocolateIceCream = new MenuItem("Chocolate Ice Cream", Money.SGD(new BigDecimal("4.00")));

    @Test
    void shouldAddMenuToBasket() throws BasketQuantityExceedException {
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


    @Test
    void shouldThrowExceptionIfQuantityInCartExceeds() {
        Basket basket = new Basket();

        BasketItem tomatoBasketItem = new BasketItem(tomatoSoup, 110);

        assertThrows(BasketQuantityExceedException.class, () -> basket.add(tomatoBasketItem));
    }

    @Test
    void shouldBeAbleToSaveBasket() throws BasketQuantityExceedException {
        Basket     basket           = new Basket();
        BasketItem tomatoBasketItem = new BasketItem(tomatoSoup, 10);
        basket.add(tomatoBasketItem);

        BasketRepository basketRepository = new BasketRepository();
        basketRepository.save(basket);
        Basket savedBasket = basketRepository.getById(basket.id());

        assertEquals(basket, savedBasket, "Basket items should be equal");
        assertEquals(basket.id(), savedBasket.id(), "saved Basket id should be equal");
        assertEquals(basket.basketItems(), savedBasket.basketItems(), "saved Basket item should be equal");

    }
}
