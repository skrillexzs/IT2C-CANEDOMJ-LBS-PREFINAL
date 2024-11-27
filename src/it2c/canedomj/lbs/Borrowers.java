
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
      String bwfname;
      while (true) {
            System.out.print("Borrowers First Name: ");
            bwfname = sc.nextLine();
            if (bwfname.matches("[a-zA-Z , . - ]+")) { 
                break;
            } else {
                System.out.println("Invalid name. Please enter only words (letters and spaces).");
            }
        }
      
      String bwlname;
      while (true) {
            System.out.print("Borrowers Last Name: ");
            bwlname = sc.nextLine();
            if (bwlname.matches("[a-zA-Z , . - ]+")) {
                break;
            } else {
                System.out.println("Invalid name. Please enter only words (letters and spaces).");
            }
        }
      
      String bwcontact;
    while (true) {
        System.out.print("Borrowers Contact Number (numbers only): ");
        bwcontact = sc.next();
        if (bwcontact.matches("\\d+")) { 
            break;
        } else {
            System.out.print("Invalid! Please enter valid digits only.");
        }
    }
    
      String yrlvl;
        while (true) {
            System.out.print("Borrower's Year Level: ");
            yrlvl = sc.nextLine().trim();

            if (yrlvl.equals("1st year") || yrlvl.equals("2nd year") ||
                yrlvl.equals("3rd year") || yrlvl.equals("4th year")) {
                break;
            } else {
                System.out.println("Invalid input. Please enter a valid year level (1st year, 2nd year, 3rd year, 4th year).");
            }
        }
      
      String qry = "INSERT INTO tbl_borrowers(bw_fname, bw_lname, bw_contact, bw_yr_lvl) VALUES (?, ?, ?, ?)";
      config conf = new config();
      
      conf.addRecord(qry, bwfname, bwlname, bwcontact, yrlvl);
      
      System.out.print("Do you want to add another book? (yes/no): ");
      continueAdding = sc.nextLine();
      
      }while (continueAdding.equalsIgnoreCase("yes"));
  }
    
    public void viewBorrowers(){
        
      String qry = "SELECT * FROM tbl_Borrowers";
      String[] hdrs = {"ID", "Borrowers_Firstname", "Borrowers_Lastname", "Borrowers_Contacts", "Borrowers_Year_Level"};
      String[] clms = {"bw_id", "bw_fname", "bw_lname", "bw_contact", "bw_yr_lvl"};
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
      String bwcontact;
      
    while (true) {
        System.out.print("New Borrowers Contact (numbers only): ");
        bwcontact = sc.next();
        if (bwcontact.matches("\\d+")) { 
            break;
        } else {
            System.out.print("Invalid! Please enter valid digits only.");
        }
    }
    
      System.out.print("New Borrowers Year Level: ");
      String yrlvl = sc.nextLine();
      
      String qry = "UPDATE tbl_borrowers SET bw_fname = ?, bw_lname = ?, bw_contact = ?, bw_yr_lvl = ? WHERE bw_id = ?";
      conf.updateRecord(qry, bwfname, bwlname, bwcontact, yrlvl, bwid);
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
