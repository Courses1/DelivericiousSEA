package domain;

import java.math.BigDecimal;

public class Coupon {
  private String     code;
  private BigDecimal discountPercentage;

  public Coupon(String code, BigDecimal discountPercentage) {
    this.code               = code;
    this.discountPercentage = discountPercentage;
  }

  public String getCode() {
    return code;
  }

  public BigDecimal getDiscountPercentage() {
    return discountPercentage;
  }

}
