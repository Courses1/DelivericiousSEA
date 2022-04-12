package domain;

import org.junit.jupiter.api.Test;
import repository.BasketRepository;
import service.CouponSuggestionService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class BasketTest {
    private MenuItem tomatoSoup        = new MenuItem("Tomato Soup", Money.SGD(new BigDecimal("10.00")), MenuItemCategory.SOUP);
    private MenuItem seaFoodSalad      = new MenuItem("Sea food salad", Money.SGD(new BigDecimal("12.00")), MenuItemCategory.SEA_FOOD);
    private MenuItem chocolateIceCream = new MenuItem("Chocolate Ice Cream", Money.SGD(new BigDecimal("4.00")), MenuItemCategory.DESSERT_ICE_CREAM);

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

    @Test
    void shouldBeAbleToSuggestApplicableCoupons() throws BasketQuantityExceedException {
        Basket     basket           = new Basket();
        BasketItem tomatoBasketItem = new BasketItem(tomatoSoup, 10);
        basket.add(tomatoBasketItem);

        CouponSuggestionService service   = new CouponSuggestionService();
        Coupon                  couponOne = new Coupon("DELIVERICIOUS_10", BigDecimal.TEN);
        Coupon                  couponTwo = new Coupon("DELIVERICIOUS_05", new BigDecimal("5.00"));

        Optional<Coupon> suggestedCoupon = service.suggestCoupon(basket, List.of(couponOne, couponTwo));

        assertEquals("DELIVERICIOUS_10", suggestedCoupon.get().getCode());
    }
}
