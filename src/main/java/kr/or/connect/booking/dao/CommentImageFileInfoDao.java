package kr.or.connect.booking.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.CommentImageFileInfo;
import static kr.or.connect.booking.dao.CommentImageFileInfoDaoSqls.*;

@Repository
public class CommentImageFileInfoDao {
  private NamedParameterJdbcTemplate jdbc;
  private RowMapper<CommentImageFileInfo> rowMapper = BeanPropertyRowMapper.newInstance(CommentImageFileInfo.class);
  public CommentImageFileInfoDao(DataSource dataSource) {
    this.jdbc = new NamedParameterJdbcTemplate(dataSource);
  }
  
  public List<CommentImageFileInfo> selectImages(Integer reservationUserCommentId) {
    Map<String, Integer> params = new HashMap<String, Integer>();
    params.put("reservationUserCommentId", reservationUserCommentId);
    return jdbc.query(SELECT_IMAGES, params, rowMapper);
  }
  
}
