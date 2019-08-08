package kr.or.connect.booking.dao;

public class CommentReservationInfoDaoSqls {
  public static String SELECT_COMMENTS = "SELECT c.id as comment_id, c.product_id, c.reservation_info_id, c.score, c.comment, r.reservation_name, r.reservation_tel as reservation_telephone, r.reservation_email, r.reservation_date, c.create_date, c.modify_date FROM reservation_user_comment c " 
      + "INNER JOIN reservation_info r ON c.reservation_info_id = r.id "
      + "WHERE r.display_info_id = :displayInfoId "
      + "ORDER BY comment_id DESC";
  
  public static String AVERAGE_SCORE = "SELECT IFNULL(avg(score),0) avg FROM reservation_user_comment c " 
      + "INNER JOIn reservation_info r ON c.reservation_info_id = r.id " 
      + "WHERE r.display_info_id = :displayInfoId";
  
}
