import java.util.Scanner;

class Room {
    int roomNumber;
    boolean isBooked;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.isBooked = false;
    }
}

public class HotelReservationSystem {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        Room[] rooms = new Room[5];
        for (int i = 0; i < 5; i++) {
            rooms[i] = new Room(i + 1);
        }

        int choice;

        do {
            System.out.println("\n===== Hotel Reservation System =====");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Book Room");
            System.out.println("3. Cancel Booking");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\nAvailable Rooms:");
                    for (Room r : rooms) {
                        if (!r.isBooked) {
                            System.out.println("Room " + r.roomNumber);
                        }
                    }
                    break;

                case 2:
                    System.out.print("Enter room number to book: ");
                    int bookRoom = sc.nextInt();

                    if (bookRoom >= 1 && bookRoom <= 5) {
                        if (!rooms[bookRoom - 1].isBooked) {
                            rooms[bookRoom - 1].isBooked = true;
                            System.out.println("Room booked successfully!");
                        } else {
                            System.out.println("Room already booked!");
                        }
                    } else {
                        System.out.println("Invalid room number!");
                    }
                    break;

                case 3:
                    System.out.print("Enter room number to cancel booking: ");
                    int cancelRoom = sc.nextInt();

                    if (cancelRoom >= 1 && cancelRoom <= 5) {
                        if (rooms[cancelRoom - 1].isBooked) {
                            rooms[cancelRoom - 1].isBooked = false;
                            System.out.println("Booking cancelled successfully!");
                        } else {
                            System.out.println("Room was not booked!");
                        }
                    } else {
                        System.out.println("Invalid room number!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Hotel Reservation System.");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 4);

        sc.close();
    }
}