
package it2c.canedomj.lbs;

import java.util.Scanner;


public class Borrowers {
    
    public void Borrowers(){
        
    Scanner sc = new Scanner(System.in);
    String response;
        
    do{
        System.out.println("-------------------------");
        System.out.println("|    BORROWERS LISTS    |");
        System.out.println("-------------------------");
        System.out.println("| 1. ADD BORROWERS      |");
        System.out.println("| 2. VIEW BORROWERS     |");
        System.out.println("| 3. UPDATE BORROWERS   |");
        System.out.println("| 4. DELETE BORROWERS   |");
        System.out.println("| 5. EXIT               |");
        System.out.println("-------------------------");

        System.out.print("Enter Action: ");
        int action = sc.nextInt();
        Borrowers bws = new Borrowers();

        switch(action){
          case 1:
              
              bws.addBorrowers();
              bws.viewBorrowers();

            break;

          case 2:
              
              bws.viewBorrowers();

            break;

          case 3:
              
              bws.viewBorrowers();
              bws.updateBorrowers();
              bws.viewBorrowers();

            break;

          case 4:
              
              bws.viewBorrowers();
              bws.deleteBorrowers();
              bws.viewBorrowers();
   
            break;

          case 5:    
         
            break;
                }
                System.out.print("Do you want to continue? (yes/no): ");
                response = sc.next();
            
        }while(response.equalsIgnoreCase("yes"));

    }
    
    public void addBorrowers(){
      
      Scanner sc = new Scanner(System.in);
      String continueAdding;
      
      do{
      System.out.print("Borrowers First Name: ");
      String bwfname = sc.nextLine();
      System.out.print("Borrowers Last Name: ");
      String bwlname = sc.nextLine();
      System.out.print("Borrowers Contact: ");
      String bwcontact = sc.nextLine(); 
      System.out.print("Borrowed Books: ");
      String bdbooks = sc.nextLine();
      
      String qry = "INSERT INTO tbl_borrowers(bw_fname, bw_lname, bw_contact, bd_books) VALUES (?, ?, ?, ?)";
      config conf = new config();
      
      conf.addRecord(qry, bwfname, bwlname, bwcontact, bdbooks);
      
      System.out.print("Do you want to add another book? (yes/no): ");
      continueAdding = sc.nextLine();
      
      }while (continueAdding.equalsIgnoreCase("yes"));
  }
    
    public void viewBorrowers(){
        
      String qry = "SELECT * FROM tbl_Borrowers";
      String[] hdrs = {"ID", "Borrowers_Firstname", "Borrowers_Lastname", "Borrowers_Contacts", "Borrowed_Books"};
      String[] clms = {"bw_id", "bw_fname", "bw_lname", "bw_contact", "bd_books"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
    }
    
    public void updateBorrowers(){
        
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Borrowers ID you want to Update: ");
      int bwid = sc.nextInt();
      sc.nextLine();
      
      while(conf.getSingleValue("SELECT bw_id FROM tbl_borrowers WHERE bw_id = ?", bwid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          bwid = sc.nextInt();
          sc.nextLine();    
      }
      
      System.out.print("New Borrowers First Name: ");
      String bwfname = sc.nextLine();
      System.out.print("New Borrowers Last Name: ");
      String bwlname = sc.nextLine();
      System.out.print("New Borrowers Contact: ");
      String bwcontact = sc.nextLine();
      System.out.print("New Borrowed Books: ");
      String bdbooks = sc.nextLine();
      
      String qry = "UPDATE tbl_borrowers SET bw_fname = ?, bw_lname = ?, bw_contact = ?, bd_books = ? WHERE bw_id = ?";
      conf.updateRecord(qry, bwfname, bwlname, bwcontact, bdbooks, bwid);
    }
    
    public void deleteBorrowers(){
        
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Borrowers ID you want to delete: ");
      int bwid = sc.nextInt();
      
      while(conf.getSingleValue("SELECT bw_id FROM tbl_borrowers WHERE bw_id = ?", bwid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          bwid = sc.nextInt();
      }
      
      String qry = "DELETE FROM tbl_borrowers WHERE bw_id = ?";
      conf.deleteRecord(qry, bwid);
    }
    
}
