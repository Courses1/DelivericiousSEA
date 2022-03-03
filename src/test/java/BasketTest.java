import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @Test
    void shouldAddMenuToBasket() {
        MenuItem tomatoSoup = new MenuItem("Tomato Soup", Money.SGD(new BigDecimal("10.00")));
        MenuItem seaFoodSalad = new MenuItem("Sea food salad",Money.SGD(new BigDecimal("12.00")));

        Basket basket = new Basket();
        basket.add(tomatoSoup);
        basket.add(seaFoodSalad);

        assertEquals(List.of(tomatoSoup, seaFoodSalad), basket.menuItems(), "Menu Items are not equal");
    }

}
