package kr.or.connect.booking.service;

import java.util.Map;
import kr.or.connect.booking.dto.Reservations;

public interface ReservationsService {
  public Map<String, Object> getReservations(String reservationEmail);

  public int cancelReservation(int reservationInfoId);

  public int makeReservation(Reservations reservations);

  public int writeReview(int reservationInfoId, String filePath, String comment, String productId,
      int score);
}
