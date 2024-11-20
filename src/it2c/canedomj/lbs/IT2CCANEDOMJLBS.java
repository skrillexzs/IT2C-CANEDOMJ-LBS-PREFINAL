
package it2c.canedomj.lbs;

import java.util.InputMismatchException;
import java.util.Scanner;


public class IT2CCANEDOMJLBS {


    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        String response;
        boolean exit=true;

        do {

            System.out.println("-----------------------------------------------");
            System.out.println("|     WELCOME TO LIBRARY BORROWING SYSTEM     |");
            System.out.println("-----------------------------------------------");
            System.out.println("| 1. BOOKS                                    |");
            System.out.println("| 2. BORROWERS                                |");
            System.out.println("| 3. BORROWED BOOKS                           |");
            System.out.println("| 4. REPORTS                                  |");
            System.out.println("| 5. EXIT                                     |");
            System.out.println("-----------------------------------------------");

            int action = 0;

            while (action < 1 || action > 5) {
                System.out.print("Enter action: ");
                
                if (sc.hasNextInt()) {
                    action = sc.nextInt();

                    if (action < 1 || action > 5) {
                        System.out.println("Invalid option! Please enter a number between 1 and 5 only.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next();
                }
            }

            switch (action) {

                case 1:
                    
                    Books bks = new Books();
                    bks.Books();

                    break;

                case 2:
                    
                    Borrowers bws = new Borrowers();
                    bws.Borrowers();

                    break;

                case 3:
                    
                    BorrowedBooks bwdbooks = new BorrowedBooks();
                    bwdbooks.BorrowedBooks();

                    break;
                    
                case 4:
                    
                    Reports reports = new Reports();
                    reports.Reports();

                    break;

                case 5:
                    
                    System.out.print("Exiting Program... type 'yes' to continue: ");
                    String resp = sc.next();
                    if(resp.equalsIgnoreCase("yes")){
                        exit = false;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            

        } while(exit);
        System.out.println("Program Exited... Thank you and Goodbye!");
    }

}


