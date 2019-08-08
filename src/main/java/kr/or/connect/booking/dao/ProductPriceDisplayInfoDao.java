package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ProductPriceDisplayInfo;
import static kr.or.connect.booking.dao.ProductPriceDisplayInfoDaoSqls.*;

@Repository
public class ProductPriceDisplayInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<ProductPriceDisplayInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductPriceDisplayInfo.class);
  public ProductPriceDisplayInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<ProductPriceDisplayInfo> selectPrices(Integer displayInfoId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.query(SELECT_PRICES, params, rowMapper);
  }
  
}
