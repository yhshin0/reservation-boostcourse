package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.ReservationsInfo;
import static kr.or.connect.booking.dao.ReservationsInfoDaoSqls.*;

@Repository
public class ReservationsInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<ReservationsInfo> rowMapper = BeanPropertyRowMapper.newInstance(ReservationsInfo.class);
  public ReservationsInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public int selectCount(String reservationEmail) {
    Map<String, String> params = new HashMap<String, String>();
    params.put("reservationEmail", reservationEmail);
    return jdbc.queryForObject(SELECT_COUNT, params, Integer.class);
  }
  
  public List<ReservationsInfo> selectByEmail(String reservationEmail) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("reservationEmail", reservationEmail);
    return jdbc.query(SELECT_BY_EMAIL, params, rowMapper);
  }
  
  public int selectTotalPrice(int reservationInfoId) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("reservationInfoId", reservationInfoId);
    return jdbc.queryForObject(SELECT_TOTAL_PRICE, params, Integer.class);
  }
  
  public int updateCancelFlag(int reservationInfoId) {
    Map<String, Object> params = new HashMap<String, Object>();
    params.put("reservationInfoId", reservationInfoId);
    return jdbc.update(UPDATE_CANCEL_FLAG, params);
  }
  
  
}
