
package it2c.canedomj.lbs;

import java.util.Scanner;


public class Reports {
        
       public void generalReports() {
        String query = "SELECT bb_id, b_name, bw_lname, bd_date, due_date, r_status, penalties FROM tbl_bdbooks "
                        + "LEFT JOIN tbl_books ON tbl_books.b_id = tbl_bdbooks.b_id "
                        + "LEFT JOIN tbl_borrowers ON tbl_borrowers.bw_id = tbl_bdbooks.bw_id";
        
        String[] hdrs = {"BBID", "Book Name", "Borrower LastName", "Borrowed Date", "Due", "Status", "Penalties"};
        String[] clmns = {"bb_id", "b_name", "bw_lname", "bd_date", "due_date", "r_status", "penalties"};
        
        System.out.println("----------------------------");
        System.out.println("    GENERAL ORDER REPORT    ");
        System.out.println("----------------------------");
        config conf = new config();
        conf.viewRecords(query, hdrs, clmns);
    }

    public void specificReports() {
        Scanner sc = new Scanner(System.in);
        
        Books bks = new Books();
        System.out.println("------------------");
        System.out.println("    BOOKS LIST    ");
        System.out.println("------------------");
        bks.viewBooks();
        
        System.out.print("Enter Book ID for a specific report: ");
        int bookId = sc.nextInt();

        config conf = new config();
        String checkBookQuery = "SELECT b_id FROM tbl_books WHERE b_id = ?";
        if (conf.getSingleValue(checkBookQuery, bookId) == 0) {
            System.out.println("Book does not exist.");
            return;
        }

        String query = "SELECT bb_id, b_name, bw_lname, bd_date, due_date, r_status, penalties FROM tbl_bdbooks "
                        + "LEFT JOIN tbl_books ON tbl_books.b_id = tbl_bdbooks.b_id "
                        + "LEFT JOIN tbl_borrowers ON tbl_borrowers.bw_id = tbl_bdbooks.bw_id "
                        + "WHERE tbl_bdbooks.bb_id = ?";
        
        String[] hdrs = {"BBID", "Book Name", "Borrower LastName", "Borrowed Date", "Due", "Status", "Penalties"};
        String[] clmns = {"bb_id", "b_name", "bw_lname", "bd_date", "due_date", "r_status", "penalties"};
        
        System.out.println("=== SPECIFIC REPORT FOR BOOK ID: " + bookId + " ===");
        conf.viewRecords0(query, hdrs, clmns, bookId);

        sc.close();  // Close the Scanner when done
    }

    public void Reports() {
        Scanner sc = new Scanner(System.in);
        String response;
        
        do {    
            System.out.println("------------------------------");
            System.out.println("    BORROWED BOOKS REPORTS    ");
            System.out.println("------------------------------");
            System.out.println("1. GENERAL REPORTS            ");
            System.out.println("2. SPECIFIC REPORTS           ");
            System.out.println("3. EXIT REPORTS PANEL         ");
            System.out.println("------------------------------");
            
            int act = 0;

            while (act < 1 || act > 3) {
                System.out.print("Enter action: ");
                
                if (sc.hasNextInt()) {
                    act = sc.nextInt();

                    if (act < 1 || act > 3) {
                        System.out.println("Invalid option! Please enter a number from 1-3 only.");
                    }
                } else {
                    System.out.println("Invalid input. Please enter a valid number.");
                    sc.next(); 
                }
            }
         
            Reports reps = new Reports();
         
            switch(act) {
                case 1:
                    reps.generalReports();
                    break;
                    
                case 2:
                    reps.specificReports();
                    break;
                    
                case 3:
                    System.out.println("\nReturning to Main System...\n");
                    sc.close();  // Close the Scanner when done
                    return;
                    
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
            
            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();

            while (!response.equalsIgnoreCase("yes") && !response.equalsIgnoreCase("no")) {
                System.out.println("Invalid input! Please answer only 'yes' or 'no'.");
                System.out.print("Do you want to continue? (yes/no): ");
                response = sc.next();
            }
            
        } while (response.equalsIgnoreCase("yes"));
        
        sc.close();  // Close the Scanner when done
    }
}