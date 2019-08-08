package kr.or.connect.booking.dao;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.CategoryDetail;
import static kr.or.connect.booking.dao.CategoryDetailDaoSqls.*;

@Repository
public class CategoryDetailDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<CategoryDetail> rowMapper = BeanPropertyRowMapper.newInstance(CategoryDetail.class);
  
  public CategoryDetailDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<CategoryDetail> selectAll(){
    return jdbc.query(SELECT_ALL, rowMapper);
  }
  
  
}
