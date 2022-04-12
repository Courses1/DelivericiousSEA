package service;

import domain.Basket;
import domain.OrderRequest;
import infra.PaymentService;

public class CheckoutService {
  private final PaymentService paymentService;

  public CheckoutService(PaymentService paymentService) {
    this.paymentService = paymentService;
  }

  public OrderRequest checkout(Basket basket) throws PaymentFailedException {
    if (paymentService.pay(basket.totalPrice())) {
      basket.markAsCheckedOut();
      return new OrderRequest(basket);
    }
    throw new PaymentFailedException();
  }
}
