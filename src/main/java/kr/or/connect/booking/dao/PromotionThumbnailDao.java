package kr.or.connect.booking.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.PromotionThumbnail;
import static kr.or.connect.booking.dao.PromotionThumbnailDaoSqls.*;

@Repository
public class PromotionThumbnailDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<PromotionThumbnail> rowMapper = BeanPropertyRowMapper.newInstance(PromotionThumbnail.class);
  
  public PromotionThumbnailDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }

  public List<PromotionThumbnail> selectAll(){
    return jdbc.query(SELECT_ALL, rowMapper);
  }
  
}
