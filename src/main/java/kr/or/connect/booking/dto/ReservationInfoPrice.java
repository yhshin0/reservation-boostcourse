package kr.or.connect.booking.dto;

public class ReservationInfoPrice {
  private int count;
  private int productPriceId;
  private int reservationInfoId;
  private int reservationInfoPriceId;
  public int getCount() {
    return count;
  }
  public void setCount(int count) {
    this.count = count;
  }
  public int getProductPriceId() {
    return productPriceId;
  }
  public void setProductPriceId(int productPriceId) {
    this.productPriceId = productPriceId;
  }
  public int getReservationInfoId() {
    return reservationInfoId;
  }
  public void setReservationInfoId(int reservationInfoId) {
    this.reservationInfoId = reservationInfoId;
  }
  public int getReservationInfoPriceId() {
    return reservationInfoPriceId;
  }
  public void setReservationInfoPriceId(int reservationInfoPriceId) {
    this.reservationInfoPriceId = reservationInfoPriceId;
  }
  @Override
  public String toString() {
    return "ReservationPrice [count=" + count + ", productPriceId=" + productPriceId
        + ", reservationInfoId=" + reservationInfoId + ", reservationInfoPriceId="
        + reservationInfoPriceId + "]";
  }
  
}
