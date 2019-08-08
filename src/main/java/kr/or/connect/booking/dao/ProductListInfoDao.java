package kr.or.connect.booking.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ProductListInfo;
import static kr.or.connect.booking.dao.ProductListInfoDaoSqls.*;

@Repository
public class ProductListInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<ProductListInfo> rowMapper = BeanPropertyRowMapper.newInstance(ProductListInfo.class);
  
  public ProductListInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<ProductListInfo> selectAll(Integer start, Integer limit){
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("start", start);
    params.put("limit", limit);
    return jdbc.query(SELECT_ALL, params, rowMapper);
  }
  
  public List<ProductListInfo> selectByCategoryId(Integer categoryId, Integer start, Integer limit){
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("categoryId", categoryId);
    params.put("start", start);
    params.put("limit", limit);
    return jdbc.query(SELECT_BY_CATEGORYID, params, rowMapper);
  }
  
  public int countAll() {
    return jdbc.queryForObject(COUNT_ALL, Collections.emptyMap(), Integer.class);
  }
  
  public int countByCategoryId(Integer categoryId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("categoryId", categoryId);
    return jdbc.queryForObject(COUNT_BY_CATEGORYID, params, Integer.class);
  }
  
}
