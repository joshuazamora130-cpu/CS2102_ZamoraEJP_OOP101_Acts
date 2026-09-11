import java.util.Scanner;

public class Canteen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String[] itemNames = {"Burger", "Pizza", "Pasta", "Sandwich", "Milk Tea"};
        double[] itemPrices = {80.00, 120.00, 100.00, 70.00, 90.00};

        int totalItemsPurchased = 0;
        double totalBeforeDiscount = 0.0;
        double totalDiscount = 0.0;

        System.out.println("=====  M E N U  =====");
        for (int i = 0; i < itemNames.length; i++) {
            System.out.printf("%d. %-10s - $%.2f%n", i + 1, itemNames[i], itemPrices[i]);
        }

        boolean orderAgain = true;

        while (orderAgain) {
            System.out.println();
            System.out.print("Enter item number: ");
            int itemNumber = scanner.nextInt();

            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();

            boolean validItem = itemNumber >= 1 && itemNumber <= itemNames.length;
            boolean validQuantity = quantity >= 1 && quantity <= 10;

            if (!validItem || !validQuantity) {
                System.out.println();
                System.out.println("Invalid order! Please enter a valid item and quantity.");

                System.out.println();
                System.out.print("Do you want to order again? (Y/N): ");
                String tryAgain = scanner.next();

                if (!tryAgain.equalsIgnoreCase("Y")) {
                    orderAgain = false;
                }
                continue;
            }

            System.out.print("Are you a student? (Y/N): ");
            String studentInput = scanner.next();
            boolean isStudent = studentInput.equalsIgnoreCase("Y");

            double subtotal = itemPrices[itemNumber - 1] * quantity;
            double discountRate = 0.0;

            if (isStudent && subtotal >= 500) {
                discountRate = 0.15;
            } else if (isStudent) {
                discountRate = 0.10;
            } else if (subtotal >= 500) {
                discountRate = 0.05;
            }

            double discount = subtotal * discountRate;
            double orderTotal = subtotal - discount;

            // Update running totals
            totalItemsPurchased += quantity;
            totalBeforeDiscount += subtotal;
            totalDiscount += discount;

            System.out.println();
            System.out.printf("Subtotal: $%.2f%n", subtotal);
            System.out.printf("Discount: $%.2f%n", discount);
            System.out.printf("Order total: $%.2f%n", orderTotal);

            System.out.println();
            System.out.print("Do you want to order again? (Y/N): ");
            String again = scanner.next();

            if (!again.equalsIgnoreCase("Y")) {
                orderAgain = false;
            }
        }

        double finalAmount = totalBeforeDiscount - totalDiscount;

        System.out.println();
        System.out.println("=====  ORDER SUMMARY  =====");
        System.out.println("Total items: " + totalItemsPurchased);
        System.out.printf("Total before discount: $%.2f%n", totalBeforeDiscount);
        System.out.printf("Total discount: $%.2f%n", totalDiscount);
        System.out.printf("Final amount: $%.2f%n", finalAmount);
        System.out.println("Thank you for ordering!");

        scanner.close();
    }
}