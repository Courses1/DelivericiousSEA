package service;

import domain.Basket;
import infra.BasketCheckedOutEvent;
import infra.EventPublisher;
import infra.PaymentService;

public class CheckoutService {
    private final PaymentService paymentService;
    private       EventPublisher eventPublisher;

    public CheckoutService(EventPublisher eventPublisher, PaymentService paymentService) {
        this.eventPublisher = eventPublisher;
        this.paymentService = paymentService;
    }

    public void checkout(Basket basket) throws PaymentFailedException {
        if (paymentService.pay(basket.totalPrice())) {
            basket.markAsCheckedOut();
            eventPublisher.publish(new BasketCheckedOutEvent(basket));
        } else
            throw new PaymentFailedException();
    }
}
