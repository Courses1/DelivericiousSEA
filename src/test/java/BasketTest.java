import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static java.util.Collections.singletonList;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BasketTest {

    @Test
    void shouldAddTomatoSoupToBasket() {
        MenuItem tomatoSoup = new MenuItem("Tomato Soup");

        Basket basket = new Basket();
        basket.add(tomatoSoup);

        assertEquals(singletonList(tomatoSoup), basket.menuItems(), "Menu Items are not equal");
    }
}
