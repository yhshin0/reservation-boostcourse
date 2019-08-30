package kr.or.connect.booking.service;

import kr.or.connect.booking.dto.ReservationResponse;
import kr.or.connect.booking.dto.Reservations;

public interface ReservationsService {
  public ReservationResponse getReservations(String reservationEmail);

  public int cancelReservation(int reservationInfoId);

  public int makeReservation(Reservations reservations);

  public int writeReview(int reservationInfoId, String filePath, String comment, String productId,
      int score);
}
