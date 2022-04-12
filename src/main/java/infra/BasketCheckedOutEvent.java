package infra;

import domain.Basket;

import java.util.UUID;

public class BasketCheckedOutEvent extends Event {

    public BasketCheckedOutEvent(Basket basket) {
        this.id   = UUID.randomUUID();
        this.data = basket;
    }

    @Override
    public String toMessage() {
        return "Received basket with Id " + ((Basket) this.data).id().toString();
    }
}
