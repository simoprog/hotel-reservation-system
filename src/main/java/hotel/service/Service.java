package hotel.service;

import hotel.entities.Booking;
import hotel.entities.Room;
import hotel.entities.User;
import hotel.enums.RoomType;

import java.util.*;

public class Service {
    ArrayList<Room> rooms;
    ArrayList<User> users;
    ArrayList<Booking> bookings;
    private int nextBookingId;

    public Service() {
        rooms = new ArrayList<>();
        users = new ArrayList<>();
        bookings = new ArrayList<>();
        nextBookingId = 1;
    }

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
        try {
            if (roomPricePerNight <= 0) {
                throw new IllegalArgumentException("Room price must be positive");
            }

            Room existingRoom = findRoomByNumber(roomNumber);
            if (existingRoom != null) {
                existingRoom.setRoomType(roomType);
                existingRoom.setPricePerNight(roomPricePerNight);
                System.out.println("Room " + roomNumber + " updated successfully");
            } else {
                Room newRoom = new Room(roomNumber, roomType, roomPricePerNight);
                rooms.add(newRoom);
                System.out.println("Room " + roomNumber + " created successfully");
            }
        } catch (Exception e) {
            System.err.println("Error setting room: " + e.getMessage());
        }
    }

    public void setUser(int userId, int balance) {
        try {
            if (balance < 0) {
                throw new IllegalArgumentException("Balance cannot be negative");
            }

            User existingUser = findUserById(userId);
            if (existingUser != null) {
                existingUser.setBalance(balance);
                System.out.println("User " + userId + " updated successfully");
            } else {
                User newUser = new User(userId, balance);
                users.add(newUser);
                System.out.println("User " + userId + " created successfully");
            }
        } catch (Exception e) {
            System.err.println("Error setting user: " + e.getMessage());
        }
    }

    public void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
        try {
            if (checkIn.after(checkOut) || checkIn.equals(checkOut)) {
                System.err.println("Invalid dates: Check-in must be before check-out");
                return;
            }

            User user = findUserById(userId);
            Room room = findRoomByNumber(roomNumber);

            if (user == null) {
                System.err.println("User " + userId + " not found");
                return;
            }

            if (room == null) {
                System.err.println("Room " + roomNumber + " not found");
                return;
            }

            long diffInMillies = Math.abs(checkOut.getTime() - checkIn.getTime());
            int nights = (int) (diffInMillies / (1000 * 60 * 60 * 24));
            int totalCost = nights * room.getPricePerNight();

            if (!user.hasEnoughBalance(totalCost)) {
                System.err.println("Insufficient balance. Required: " + totalCost + ", Available: " + user.getBalance());
                return;
            }

            if (!isRoomAvailable(roomNumber, checkIn, checkOut)) {
                System.err.println("Room " + roomNumber + " is not available for the specified period");
                return;
            }

            Booking booking = new Booking(nextBookingId++, userId, roomNumber, checkIn, checkOut,
                    totalCost, nights, room.getRoomType(), room.getPricePerNight(), user.getBalance());
            bookings.add(booking);
            user.deductBalance(totalCost);

            System.out.println("Booking successful! Booking ID: " + booking.getBookingId());

        } catch (Exception e) {
            System.err.println("Error booking room: " + e.getMessage());
        }
    }

    private boolean isRoomAvailable(int roomNumber, Date checkIn, Date checkOut) {
        for (Booking booking : bookings) {
            if (booking.getRoomNumber() == roomNumber) {
                if (!(checkOut.before(booking.getCheckIn()) || checkIn.after(booking.getCheckOut()))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void printAll() {
        System.out.println("\n=== ALL ROOMS (Latest to Oldest) ===");
        List<Room> reversedRooms = new ArrayList<>(rooms);
        Collections.reverse(reversedRooms);
        for (Room room : reversedRooms) {
            System.out.println(room);
        }

        System.out.println("\n=== ALL BOOKINGS (Latest to Oldest) ===");
        List<Booking> reversedBookings = new ArrayList<>(bookings);
        Collections.reverse(reversedBookings);
        for (Booking booking : reversedBookings) {
            System.out.println(booking);
        }
    }

    public void printAllUsers() {
        System.out.println("\n=== ALL USERS (Latest to Oldest) ===");
        List<User> reversedUsers = new ArrayList<>(users);
        Collections.reverse(reversedUsers);
        for (User user : reversedUsers) {
            System.out.println(user);
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        return rooms.stream().filter(r -> r.getRoomNumber() == roomNumber).findFirst().orElse(null);
    }

    private User findUserById(int userId) {
        return users.stream().filter(u -> u.getUserId() == userId).findFirst().orElse(null);
    }
}