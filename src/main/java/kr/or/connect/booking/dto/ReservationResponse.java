package kr.or.connect.booking.dto;

import java.util.List;

public class ReservationResponse {
  private List<ReservationsInfo> reservations;
  private int size;
  public List<ReservationsInfo> getReservations() {
    return reservations;
  }
  public void setReservations(List<ReservationsInfo> reservations) {
    this.reservations = reservations;
  }
  public int getSize() {
    return size;
  }
  public void setSize(int size) {
    this.size = size;
  }
  @Override
  public String toString() {
    return "ReservationResponse [reservations=" + reservations + ", size=" + size + "]";
  }
  
}
