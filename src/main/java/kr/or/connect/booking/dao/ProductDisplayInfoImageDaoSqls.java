package kr.or.connect.booking.dao;

public class ProductDisplayInfoImageDaoSqls {
  public static String SELECT_ONE = "SELECT img.id as display_info_image_id, img.display_info_id, f.id as file_id, f.file_name, f.save_file_name, f.content_type, f.delete_flag, f.create_date, f.modify_date FROM display_info_image img " 
      + "INNER JOIN file_info f ON img.file_id = f.id " 
      + "WHERE img.display_info_id = :displayInfoId";
}
