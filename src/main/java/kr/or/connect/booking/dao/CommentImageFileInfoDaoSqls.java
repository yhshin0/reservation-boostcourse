package kr.or.connect.booking.dao;

public class CommentImageFileInfoDaoSqls {
  public static String SELECT_IMAGES = "SELECT r.id as image_id, r.reservation_info_id, r.reservation_user_comment_id, r.file_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date FROM reservation_user_comment_image r " 
      + "INNER JOIN file_info f ON r.file_id = f.id " 
      + "WHERE r.reservation_user_comment_id = :reservationUserCommentId "
      + "ORDER BY image_id";
}
