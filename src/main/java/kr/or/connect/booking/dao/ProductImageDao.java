package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ProductImage;
import static kr.or.connect.booking.dao.ProductImageDaoSqls.*;

@Repository
public class ProductImageDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<ProductImage> rowMapper = BeanPropertyRowMapper.newInstance(ProductImage.class);
  public ProductImageDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<ProductImage> selectImages(Integer displayInfoId){
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.query(SELECT_IMAGES, params, rowMapper);
  }
  
}
