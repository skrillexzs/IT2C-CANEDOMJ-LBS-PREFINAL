
package it2c.canedomj.lbs;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class BorrowedBooks {
    
    public void BorrowedBooks(){
        Scanner sc = new Scanner(System.in);
         String response;

        do{
            System.out.println("--------------------------------");
            System.out.println("|     BORROWED BOOKS LISTS     |");
            System.out.println("--------------------------------");
            System.out.println("| 1. ADD BORROWED BOOKS        |");
            System.out.println("| 2. VIEW BORROWED BOOKS       |");
            System.out.println("| 3. UPDATE BORROWED BOOKS     |");
            System.out.println("| 4. DELETE BORROWED BOOKS     |");
            System.out.println("| 5. EXIT                      |");
            System.out.println("--------------------------------");
            
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
            
            BorrowedBooks bwdbooks = new BorrowedBooks();
            
            switch(action){
                
               
                case 1:
                    
                    bwdbooks.addBorrowedBooks();
                    
                    break;
                    
                case 2:
                   
                    bwdbooks.viewBorrowedBooks();
                    
                    break;
                    
                case 3:
                    
                    bwdbooks.viewBorrowedBooks();
                    bwdbooks.updateBorrowedBooks();
                               
                    break;
            
                case 4: 
                    
                    bwdbooks.viewBorrowedBooks();
                    bwdbooks.deleteBorrowedBooks();
                    bwdbooks.viewBorrowedBooks();
                                     
                   break;
                   
                case 5:
                    
                    System.out.println("\nReturning to Main System...\n");
                    return;
                    
                    default:
                    System.out.print("Invalid option. Please try again.");
                    break;   
            }
            
          
            System.out.print("Do you want to continue? (yes/no): ");
            response = sc.next();
            
        }while(response.equals("yes"));
        
        
    }
        private void addBorrowedBooks(){
            Scanner sc = new Scanner (System.in);
            config conf = new config();
            Books bks = new Books();
            bks.viewBooks();
            
            System.out.print("Enter Selected Books ID: ");
            int bid = sc.nextInt();
            
            String bsql = "SELECT b_id FROM tbl_books WHERE b_id = ?";
            while(conf.getSingleValue(bsql, bid)== 0){
                System.out.print("Book does not exist, Please Select ID Again: " );
                bid = sc.nextInt();
            }
            
            Borrowers bws = new Borrowers();
            bws.viewBorrowers();
              
            System.out.print("Enter Selected Borrowers ID: ");
            int bwid = sc.nextInt();
            
            String bsql1 = "SELECT bw_id FROM tbl_borrowers WHERE bw_id = ?";
            while(conf.getSingleValue(bsql1, bwid)== 0){
                System.out.print("Borrower does not exist, Please Select ID Again: " );
                bwid = sc.nextInt();
        }
            System.out.print("Enter borrowed date: ");
            String bwddate = sc.next();
            
            System.out.print("Enter due date: ");
            String dd = sc.next();
            
            System.out.print("Enter quantity: ");
            int quant = sc.nextInt();
            
            System.out.print("Enter return status: ");
            String rstatus = sc.next();
            
            System.out.print("Enter penalties: ");
            String penalties = sc.next();
            
            String bdbqry = "INSERT INTO tbl_bdbooks (b_id, bw_id, bd_date, due_date, b_quantity, r_status, penalties)"
            + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                
            conf.addRecord(bdbqry, bid, bwid, bwddate, dd, quant, rstatus, penalties);

}
        
        
        public void viewBorrowedBooks() {
            String query = "SELECT bb_id, b_name, bw_lname, bd_date, due_date, r_status, penalties FROM tbl_bdbooks "
                    + "LEFT JOIN tbl_books ON tbl_books.b_id = tbl_bdbooks.b_id "
                    + "LEFT JOIN tbl_borrowers ON tbl_borrowers.bw_id = tbl_bdbooks.bw_id";
                  
        
        String[] hdrs = {"BBID","Book Name", "Borrower LastName",  "Borrowed Date", "Due", "Status", "Penalties"};
        String[] clmns = {"bb_id","b_name", "bw_lname",  "bd_date", "due_date", "r_status", "penalties"};
        config conf = new config();
        conf.viewRecords(query, hdrs, clmns);
        
        }
        
        private void updateBorrowedBooks(){
        Scanner sc = new Scanner(System.in);
        BorrowedBooks bwdbooks = new BorrowedBooks();
        Books bks = new Books();
        Borrowers bws = new Borrowers();
        config conf = new config();
        
        
        System.out.print("Enter Borrowed Books ID: ");
        int bbid = sc.nextInt();
        
        String bbsql = "SELECT bb_id FROM tbl_bdbooks WHERE bb_id = ?";
            while(conf.getSingleValue(bbsql, bbid)== 0){
                System.out.print("Borrowed Book ID does not exist, Please Select ID Again: " );
                bbid = sc.nextInt();
            }

              String statusQuery = "SELECT r_status FROM tbl_bdbooks WHERE bb_id = ?";
              String currentStatus = conf.getSingleStringValue(statusQuery, bbid);

    
            bwdbooks.viewBorrowedBooks();

            System.out.print("Enter new Status: ");
            String newStatus = sc.next();

    
            if (currentStatus.equalsIgnoreCase("Cancel") || currentStatus.equalsIgnoreCase("Done")) {
                System.out.println("Status cannot be updated as the order is already marked as '" + currentStatus + "'.");
             } else if (newStatus.equalsIgnoreCase("Cancel") || newStatus.equalsIgnoreCase("Done")) {
        
            String sql = "UPDATE tbl_bdbooks SET r_status = ? WHERE bb_id = ?";
            conf.updateRecord(sql, newStatus, bbid);
            System.out.println("Status Successfully Updated to '" + newStatus + "'.");
            } else {
       
             }
    }
         
          private void deleteBorrowedBooks() {      
            Scanner sc = new Scanner(System.in);        
            System.out.print("Enter Borrowed Book ID to Delete: ");
            int bbid = sc.nextInt();

            String sqlDelete = "DELETE FROM tbl_bdbooks WHERE bb_id = ?";
        
            config conf = new config();
            conf.deleteRecord(sqlDelete, bbid);

    }
}
    
