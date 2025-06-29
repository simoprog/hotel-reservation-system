import hotel.enums.RoomType;
import hotel.service.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

public class HotelReservationTest {
    public static void main(String[] args) {
        Service service = new Service();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        try {
            System.out.println("=== HOTEL RESERVATION SYSTEM TEST ===\n");

            System.out.println("Creating rooms...");
            service.setRoom(1, RoomType.STANDARD, 1000);
            service.setRoom(2, RoomType.JUNIOR, 2000);
            service.setRoom(3, RoomType.SUITE, 3000);

            System.out.println("\nCreating users...");
            service.setUser(1, 5000);
            service.setUser(2, 10000);

            System.out.println("\n=== BOOKING ATTEMPTS ===");

            Date checkIn1 = sdf.parse("30/06/2026");
            Date checkOut1 = sdf.parse("07/07/2026");
            System.out.println("\n1. User 1 booking Room 2 (7 nights, cost: 14000):");
            service.bookRoom(1, 2, checkIn1, checkOut1);

            // User 1 tries booking Room 2 from 07/07/2026 to 30/06/2026 (invalid dates)
            Date checkIn2 = sdf.parse("07/07/2026");
            Date checkOut2 = sdf.parse("30/06/2026");
            System.out.println("\n2. User 1 booking Room 2 (invalid dates):");
            service.bookRoom(1, 2, checkIn2, checkOut2);

            // User 1 tries booking Room 1 from 07/07/2026 to 08/07/2026 (1 night)
            Date checkIn3 = sdf.parse("07/07/2026");
            Date checkOut3 = sdf.parse("08/07/2026");
            System.out.println("\n3. User 1 booking Room 1 (1 night, cost: 1000):");
            service.bookRoom(1, 1, checkIn3, checkOut3);

            // User 2 tries booking Room 1 from 07/07/2026 to 09/07/2026 (2 nights)
            Date checkIn4 = sdf.parse("07/07/2026");
            Date checkOut4 = sdf.parse("09/07/2026");
            System.out.println("\n4. User 2 booking Room 1 (2 nights, overlaps with User 1):");
            service.bookRoom(2, 1, checkIn4, checkOut4);

            // User 2 tries booking Room 3 from 07/07/2026 to 08/07/2026 (1 night)
            Date checkIn5 = sdf.parse("07/07/2026");
            Date checkOut5 = sdf.parse("08/07/2026");
            System.out.println("\n5. User 2 booking Room 3 (1 night, cost: 3000):");
            service.bookRoom(2, 3, checkIn5, checkOut5);

            // Update Room 1 to suite type with price 10000
            System.out.println("\n6. Updating Room 1 to suite type with price 10000:");
            service.setRoom(1, RoomType.SUITE, 10000);

            // Print final results
            System.out.println("\n" + "=".repeat(50));
            System.out.println("FINAL RESULTS");
            System.out.println("=".repeat(50));

            service.printAll();
            service.printAllUsers();

        } catch (Exception e) {
            System.err.println("Error in test: " + e.getMessage());
            e.printStackTrace();
        }
    }
}