package kr.or.connect.booking.dao;

public class ReservationsInfoDaoSqls {
  public static String SELECT_COUNT = "SELECT COUNT(*) FROM RESERVATION_INFO WHERE reservation_email = :reservationEmail";
  public static String SELECT_BY_EMAIL = "SELECT cancel_flag AS cancelYn, product_id, display_info_id, "
      + "id AS reservation_info_id, reservation_name, reservation_tel AS reservation_telephone, reservation_email, "
      + "reservation_date, create_date, modify_date FROM RESERVATION_INFO WHERE reservation_email = :reservationEmail";
  public static String SELECT_TOTAL_PRICE = "SELECT SUM(price * count) AS totalprice FROM product_price p "
      + "INNER JOIN reservation_info_price r ON p.id = r.product_price_id "
      + "WHERE r.reservation_info_id = :reservationInfoId";
  public static String UPDATE_CANCEL_FLAG = "UPDATE reservation_info SET cancel_flag = 1 WHERE id=:reservationInfoId;";

}
