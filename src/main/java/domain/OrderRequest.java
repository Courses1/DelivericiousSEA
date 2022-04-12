package domain;

import java.util.UUID;

public class OrderRequest {

    private final UUID        id;
    private final Basket      basket;
    private final OrderStatus status;

    public OrderRequest(Basket basket) {
        this.id     = UUID.randomUUID();
        this.basket = basket;
        this.status = OrderStatus.NEW;
    }

    public Basket basket() {
        return basket;
    }

    public OrderStatus orderStatus() {
        return status;
    }
}
