package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ProductDisplayInfo;
import static kr.or.connect.booking.dao.ProductDisplayInfoDaoSqls.*;

@Repository
public class ProductDisplayInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<ProductDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductDisplayInfo.class);
  public ProductDisplayInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public ProductDisplayInfo selectOne (Integer displayInfoId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.queryForObject(SELECT_ONE, params, rowMapper);
  }
  
}
