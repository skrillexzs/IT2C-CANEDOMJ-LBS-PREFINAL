
package it2c.canedomj.lbs;

import java.util.Scanner;


public class Reports {
    
     public void Reports(){
        
        Scanner sc = new Scanner(System.in);
    String response;
        
    do{
        System.out.println("---------------------------");
        System.out.println("|      REPORTS LISTS      |");
        System.out.println("---------------------------");
        System.out.println("| 1. ADD TRANSACTIONS     |");
        System.out.println("| 2. VIEW TRANSACTIONS    |");
        System.out.println("| 3. UPDATE TRANSACTION   |");
        System.out.println("| 4. DELETE TRANSACTIONS  |");
        System.out.println("| 5. EXIT                 |");
        System.out.println("---------------------------");

        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Reports report = new Reports();

        switch(action){
          case 1:
              
              report.addReports();
              report.viewReports();

            break;

          case 2:
              
              report.viewReports();

            break;

          case 3:
              
              report.viewReports();
              report.updateReports();
              report.viewReports();

            break;

          case 4:
              
              report.viewReports();
              report.deleteReports();
              report.viewReports();
   
            break;

          case 5:    
         
            break;
                }
                System.out.print("Do you want to continue? (yes/no): ");
                response = sc.next();
            
        }while(response.equalsIgnoreCase("yes"));
    }
    
    public void addReports(){
        
      Scanner sc = new Scanner(System.in);
      
      System.out.print("Borrowers Name: ");
      String bwname = sc.nextLine();
      System.out.print("Borrowed Books: ");
      String bdbooks = sc.nextLine();
      System.out.print("Borrowed Date: ");
      String bddate = sc.nextLine(); 
      System.out.print("Due Date: ");
      String duedate = sc.nextLine();
      
      String qry = "INSERT INTO tbl_transactions(bw_name, bd_books, bd_date, due_date) VALUES (?, ?, ?, ?)";
      config conf = new config();
      
      conf.addRecord(qry, bwname, bdbooks, bddate, duedate);
    }
    
    public void viewReports(){
        
      String qry = "SELECT * FROM tbl_transactions";
      String[] hdrs = {"ID", "Borrowers_Name", "Borrowed_Books", "Borrowed_Date", "Due_Date"};
      String[] clms = {"bw_id", "bw_name", "bd_books", "bd_date", "due_date"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateReports(){
        
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Transactions ID you want to Update: ");
      int tid = sc.nextInt();
      sc.nextLine();
      
      while(conf.getSingleValue("SELECT t_id FROM tbl_transactions WHERE t_id = ?", tid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          tid = sc.nextInt();
          sc.nextLine();    
      }
      
      System.out.print("New Borrowers Name: ");
      String bwname = sc.nextLine();
      System.out.print("New Borrowed Books: ");
      String bdbooks = sc.nextLine();
      System.out.print("New Borrowed Date: ");
      String bddate = sc.nextLine(); 
      System.out.print("New Due Date: ");
      String duedate = sc.nextLine();
      
      String qry = "UPDATE tbl_transactions SET bw_name = ?, bd_books = ?, bd_date = ?, due_date = ? WHERE t_id = ?";
      conf.updateRecord(qry, bwname, bdbooks, bddate, duedate, tid);
    }
    
    public void deleteReports(){
        
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Transactions ID you want to delete: ");
      int tid = sc.nextInt();
      
      while(conf.getSingleValue("SELECT t_id FROM tbl_transactions WHERE t_id = ?", tid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          tid = sc.nextInt();
      }
      
      String qry = "DELETE FROM tbl_transactions WHERE t_id = ?";
      conf.deleteRecord(qry, tid);
    }
    
    
}
