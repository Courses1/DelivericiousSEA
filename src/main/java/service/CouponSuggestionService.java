package service;

import domain.Basket;
import domain.BasketItem;
import domain.Coupon;

import java.util.List;
import java.util.Optional;

import static domain.MenuItemCategory.SOUP;

public class CouponSuggestionService {
    public Optional<Coupon> suggestCoupon(Basket basket, List<Coupon> availableCoupons) {
        List<BasketItem> basketItems = basket.basketItems();
        if (isEligibleForDelivericious10(basketItems)) {
            return getFor(availableCoupons, "DELIVERICIOUS_10");
        }
        return Optional.empty();
    }

    private Optional<Coupon> getFor(List<Coupon> coupons, String code) {
        return coupons.stream().filter(coupon -> coupon.getCode().equals(code)).findAny();
    }

    private boolean isEligibleForDelivericious10(List<BasketItem> basketItems) {
        return basketItems
                .stream()
                .anyMatch(basketItem -> basketItem.belongsToCategory(SOUP) && basketItem.getQuantity() > 5);
    }
}
