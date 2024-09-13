import java.util.Scanner;

public class SimpleCalculator {

    // For Displaying a header
    public static void displayheader() {

        System.out.println("------------------------------");
        System.out.println("      Simple Calculator       ");
        System.out.println("------------------------------");

    }

    // For Displaying the main menu for operation selection
    public static void displaymenu() {

        System.out.println("\n Choose an operation : ");
        System.out.println("1. Addition");
        System.out.println("2. Subtraction");
        System.out.println("3. Multiplication");
        System.out.println("4. Division");
        System.out.println("5. Exit");
        System.out.println();
        System.out.print("Enter Your Choice : ");

    }

    // For performing the selected arithmetic operation
    public static double calculate(double num1, double num2, int operation) throws ArithmeticException {

        switch (operation) {
            case 1:
                return num1 + num2;

            case 2:
                return num1 - num2;    
            
            case 3:
                return num1 * num2;

            case 4:
                if (num2 == 0) {
                    throw new ArithmeticException("Cannot divided by 0");
                }
                return num1 / num2;
        
            default:
                throw new IllegalArgumentException("Invalid operation Selected !!!!!");
        }

    }

    // For validating the user's input to ensure that it's a valid number
    public static double getValidNumber(Scanner scan, String prompt) {

        while (true) {
            try {
                System.out.print(prompt);
                return Double.parseDouble(scan.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("!!!!! Error : Invalid Input. !!!!!");
                System.out.println("Please Enter a valid number.");
            }
        }

    }

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        boolean continueCalculating = true;

        displayheader();    // It will display the program header.

        while (continueCalculating) {
            displaymenu();      // It will display the main menu option.

            try {
                
                int choice = Integer.parseInt(scan.nextLine());     // It will take user's choice

                // If the user choose to exit means 5 option in main menu
                if (choice == 5) {
                    System.out.println("Thank you for using the Calculator. Goodbye !!!!!!");
                    break;
                }

                // Get valid inputs from the user
                double num1 = getValidNumber(scan, "Enter the first number : ");
                double num2 = getValidNumber(scan, "Enter the second number : ");

                // Perform the selected operation and display the result
                double result = calculate(num1, num2, choice);
                System.out.printf("\n Result : %.2f%n", result);

            } catch (NumberFormatException e) {
                System.out.println("!!!!! Error : Invalid Input. !!!!!");
                System.out.println("Please Enter a valid number.");
            } catch (ArithmeticException e) {
                System.out.println("!!!!! Error : !!!!!" + e.getMessage());
            } catch (IllegalArgumentException e) {
                System.out.println("!!!!! Error : !!!!!" + e.getMessage());
            }
        }

        scan.close();

    }

}