package hotel.entities;

import hotel.enums.RoomType;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Booking {
    private int bookingId;
    private int userId;
    private int roomNumber;
    private Date checkIn;
    private Date checkOut;
    private int totalCost;
    private int nights;

    private RoomType roomTypeAtBooking;
    private int pricePerNightAtBooking;
    private int userBalanceAtBooking;

    public Booking(int bookingId, int userId, int roomNumber, Date checkIn, Date checkOut,
                   int totalCost, int nights, RoomType roomType, int pricePerNight, int userBalance) {
        this.bookingId = bookingId;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.totalCost = totalCost;
        this.nights = nights;
        this.roomTypeAtBooking = roomType;
        this.pricePerNightAtBooking = pricePerNight;
        this.userBalanceAtBooking = userBalance;
    }

    // Getters
    public int getBookingId() { return bookingId; }
    public int getUserId() { return userId; }
    public int getRoomNumber() { return roomNumber; }
    public Date getCheckIn() { return checkIn; }
    public Date getCheckOut() { return checkOut; }
    public int getTotalCost() { return totalCost; }
    public int getNights() { return nights; }
    public RoomType getRoomTypeAtBooking() { return roomTypeAtBooking; }
    public int getPricePerNightAtBooking() { return pricePerNightAtBooking; }
    public int getUserBalanceAtBooking() { return userBalanceAtBooking; }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return "Booking{ID=" + bookingId +
                ", User=" + userId +
                ", Room=" + roomNumber +
                ", CheckIn=" + sdf.format(checkIn) +
                ", CheckOut=" + sdf.format(checkOut) +
                ", Nights=" + nights +
                ", TotalCost=" + totalCost +
                ", RoomType=" + roomTypeAtBooking +
                ", PricePerNight=" + pricePerNightAtBooking +
                ", UserBalance=" + userBalanceAtBooking + "}";
    }
}