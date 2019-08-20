package kr.or.connect.booking.dao;

import javax.sql.DataSource;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import kr.or.connect.booking.dto.Reservation;

@Repository
public class ReservationDao {
  private SimpleJdbcInsert insertAction;
  
  public ReservationDao(DataSource dataSource) {
    this.insertAction = new SimpleJdbcInsert(dataSource).withTableName("reservation_info").usingGeneratedKeyColumns("id");
  }
  
  public int insert(Reservation reservation) {
    SqlParameterSource params = new BeanPropertySqlParameterSource(reservation);
    return insertAction.executeAndReturnKey(params).intValue();
  }
  
}
