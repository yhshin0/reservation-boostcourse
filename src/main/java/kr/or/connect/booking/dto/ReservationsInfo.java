package kr.or.connect.booking.dto;

public class ReservationsInfo {
  private int cancelYn;
  private int productId;
  private int displayInfoId;
  private int reservationInfoId;
  private int totalPrice;
  private ProductDisplayInfo displayInfo;
  private String reservationName;
  private String reservationTelephone;
  private String reservationEmail;
  private String reservationDate;
  private String createDate;
  private String modifyDate;
  public int getCancelYn() {
    return cancelYn;
  }
  public void setCancelYn(int cancelYn) {
    this.cancelYn = cancelYn;
  }
  public int getProductId() {
    return productId;
  }
  public void setProductId(int productId) {
    this.productId = productId;
  }
  public int getDisplayInfoId() {
    return displayInfoId;
  }
  public void setDisplayInfoId(int displayInfoId) {
    this.displayInfoId = displayInfoId;
  }
  public int getReservationInfoId() {
    return reservationInfoId;
  }
  public void setReservationInfoId(int reservationInfoId) {
    this.reservationInfoId = reservationInfoId;
  }
  public int getTotalPrice() {
    return totalPrice;
  }
  public void setTotalPrice(int totalPrice) {
    this.totalPrice = totalPrice;
  }
  public ProductDisplayInfo getDisplayInfo() {
    return displayInfo;
  }
  public void setDisplayInfo(ProductDisplayInfo displayInfo) {
    this.displayInfo = displayInfo;
  }
  public String getReservationName() {
    return reservationName;
  }
  public void setReservationName(String reservationName) {
    this.reservationName = reservationName;
  }
  public String getReservationTelephone() {
    return reservationTelephone;
  }
  public void setReservationTelephone(String reservationTelephone) {
    this.reservationTelephone = reservationTelephone;
  }
  public String getReservationEmail() {
    return reservationEmail;
  }
  public void setReservationEmail(String reservationEmail) {
    this.reservationEmail = reservationEmail;
  }
  public String getReservationDate() {
    return reservationDate;
  }
  public void setReservationDate(String reservationDate) {
    this.reservationDate = reservationDate;
  }
  public String getCreateDate() {
    return createDate;
  }
  public void setCreateDate(String createDate) {
    this.createDate = createDate;
  }
  public String getModifyDate() {
    return modifyDate;
  }
  public void setModifyDate(String modifyDate) {
    this.modifyDate = modifyDate;
  }
  @Override
  public String toString() {
    return "ReservationInfo [cancelYn=" + cancelYn + ", productId=" + productId + ", displayInfoId="
        + displayInfoId + ", reservationInfoId=" + reservationInfoId + ", totalPrice=" + totalPrice
        + ", displayInfo=" + displayInfo + ", reservationName=" + reservationName
        + ", reservationTelephone=" + reservationTelephone + ", reservationEmail="
        + reservationEmail + ", reservationDate=" + reservationDate + ", createDate=" + createDate
        + ", modifyDate=" + modifyDate + "]";
  }
  
}
