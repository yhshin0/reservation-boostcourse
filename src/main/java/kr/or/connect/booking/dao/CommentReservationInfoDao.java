package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.CommentReservationInfo;
import static kr.or.connect.booking.dao.CommentReservationInfoDaoSqls.*;

@Repository
public class CommentReservationInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<CommentReservationInfo> rowMapper = BeanPropertyRowMapper.newInstance(CommentReservationInfo.class);
  public CommentReservationInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<CommentReservationInfo> selectComments(Integer displayInfoId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.query(SELECT_COMMENTS, params, rowMapper);
  }
  
  public double averageScore(Integer displayInfoId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("displayInfoId", displayInfoId);
    return jdbc.queryForObject(AVERAGE_SCORE, params, Double.class);
  }
  
}
