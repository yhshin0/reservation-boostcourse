package kr.or.connect.booking.dto;

public class Reservation {
  private int id;
  private int productId;
  private int displayInfoId;
  private String reservationName;
  private String reservationTel;
  private String reservationEmail;
  private String reservationDate;
  private int cancelFlag;
  private String createDate;
  private String modifyDate;
  public int getId() {
    return id;
  }
  public void setId(int id) {
    this.id = id;
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
  public String getReservationName() {
    return reservationName;
  }
  public void setReservationName(String reservationName) {
    this.reservationName = reservationName;
  }
  public String getReservationTel() {
    return reservationTel;
  }
  public void setReservationTel(String reservationTel) {
    this.reservationTel = reservationTel;
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
  public int getCancelFlag() {
    return cancelFlag;
  }
  public void setCancelFlag(int cancelFlag) {
    this.cancelFlag = cancelFlag;
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
    return "Reservation [id=" + id + ", productId=" + productId + ", displayInfoId=" + displayInfoId
        + ", reservationName=" + reservationName + ", reservationTel=" + reservationTel
        + ", reservationEmail=" + reservationEmail + ", reservationDate=" + reservationDate
        + ", cancelFlag=" + cancelFlag + ", createDate=" + createDate + ", modifyDate=" + modifyDate
        + "]";
  }
  
}
