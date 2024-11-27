
package it2c.canedomj.lbs;

import java.util.Scanner;

public class Books {

  public void Books(){
        
    Scanner sc = new Scanner(System.in);
    String response;
        
    do{
        System.out.println("---------------------");
        System.out.println("|     BOOK LISTS    |");
        System.out.println("---------------------");
        System.out.println("| 1. ADD BOOKS      |");
        System.out.println("| 2. VIEW BOOKS     |");
        System.out.println("| 3. UPDATE BOOKS   |");
        System.out.println("| 4. DELETE BOOKS   |");
        System.out.println("| 5. EXIT           |");
        System.out.println("---------------------");

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
            
        Books bks = new Books();

        switch(action){
          case 1:
              
                bks.addBooks();
                bks.viewBooks();

            break;

          case 2:
              
              bks.viewBooks();

            break;

          case 3:
              
              bks.viewBooks();
              bks.updateBooks();
              bks.viewBooks();

            break;

          case 4:
              
              bks.viewBooks();
              bks.deleteBooks();
              bks.viewBooks();
   
            break;

          case 5:
              
            break;
                
        }
        System.out.print("Do you want to continue? (yes/no): ");
        response = sc.next();
            
    }while(response.equalsIgnoreCase("yes"));

  }
  
  public void addBooks(){
      
      Scanner sc = new Scanner(System.in);
      String continueAdding;
      
      do{
      System.out.print("Book Name: ");
      String bname = sc.nextLine();
      
      String bgen;
      while (true) {
            System.out.print("Book Genre: ");
            bgen = sc.nextLine();
            if (bgen.matches("[a-zA-Z , . - ]+")) {  // regex allows only letters and spaces
                break;
            } else {
                System.out.println("Invalid genre. Please enter only words (letters and spaces).");
            }
        }
      
      String bauth;
      while (true) {
            System.out.print("Book Author: ");
            bauth = sc.nextLine();
            if (bauth.matches("[a-zA-Z , . - ]+")) {  // regex allows only letters and spaces
                break;
            } else {
                System.out.println("Invalid author name. Please enter only words (letters and spaces).");
            }
        }
      
        String bstat;
        System.out.print("Book Status (New, Old, For Replacement): ");
        bstat = sc.nextLine();

        if (bstat.equalsIgnoreCase("New") || bstat.equalsIgnoreCase("Old") || bstat.equalsIgnoreCase("For Replacement")) {
            String qry = "INSERT INTO tbl_books(b_name, b_genre, b_author, b_status) VALUES (?, ?, ?, ?)";
            config conf = new config();
            conf.addRecord(qry, bname, bgen, bauth, bstat);
        } else {
            System.out.println("Invalid status. Please enter ('New', 'Old', or 'For Replacement' only):");
            bstat = sc.next();         
        }
      
      String qry = "INSERT INTO tbl_books(b_name, b_genre, b_author, b_status) VALUES (?, ?, ?, ?)";
      config conf = new config();
      
      conf.addRecord(qry, bname, bgen, bauth, bstat);
      
      System.out.print("Do you want to add another book? (yes/no): ");
      continueAdding = sc.nextLine();
      
      }while (continueAdding.equalsIgnoreCase("yes"));
  }
  
  public void viewBooks(){
      
      String qry = "SELECT * FROM tbl_Books";
      String[] hdrs = {"ID", "Book_Name", "Genre", "Author", "Status"};
      String[] clms = {"b_id", "b_name", "b_genre", "b_author", "b_status"};
      config conf = new config();
      conf.viewRecords(qry, hdrs, clms);
      
  }
  
  private void updateBooks(){
      
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Book ID to Update: ");
      int bid = sc.nextInt();
      sc.nextLine();
      
      while(conf.getSingleValue("SELECT b_id FROM tbl_Books WHERE b_id = ?", bid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          bid = sc.nextInt();
          sc.nextLine();    
      }
      
      System.out.print("New Book Name: ");
      String bname = sc.nextLine();
      
      String bgen = "";
      while (true) {
            System.out.print("Book Genre: ");
            bgen = sc.nextLine();
            if (bgen.matches("[a-zA-Z , . - ]+")) {
                break;
            } else {
                System.out.println("Invalid genre. Please enter only words (letters and spaces).");
            }
        }
      
      String bauth = "";
      while (true) {
            System.out.print("Book Author: ");
            bauth = sc.nextLine();
            if (bauth.matches("[a-zA-Z , . - ]+")) {
                break;
            } else {
                System.out.println("Invalid author name. Please enter only words (letters and spaces).");
            }
        }
      
      String bstat;
        System.out.print("Book Status (New, Old, For Replacement): ");
        bstat = sc.nextLine();

        if (bstat.equalsIgnoreCase("New") || bstat.equalsIgnoreCase("Old") || bstat.equalsIgnoreCase("For Replacement")) {
            String qry = "INSERT INTO tbl_books(b_name, b_genre, b_author, b_status) VALUES (?, ?, ?, ?)";
            conf.addRecord(qry, bname, bgen, bauth, bstat);
        } else {
            System.out.println("Invalid status. Please enter ('New', 'Old', or 'For Replacement' only):");
            bstat = sc.next();         
        }
      
      String qry = "UPDATE tbl_Books SET b_name = ?, b_genre = ?, b_author = ?, b_status = ? WHERE b_id = ?";
      conf.updateRecord(qry, bname, bgen, bauth, bstat, bid);
  }
  
  private void deleteBooks(){
      
      Scanner sc = new Scanner(System.in);
      config conf = new config();
      
      System.out.println("Enter Book ID you want to delete: ");
      int bid = sc.nextInt();
      
      while(conf.getSingleValue("SELECT b_id FROM tbl_Books WHERE b_id = ?", bid) == 0){
          System.out.println("ID doesn't exist!");
          System.out.println("Please try again!: ");
          bid = sc.nextInt();
      }
      
      String qry = "DELETE FROM tbl_Books WHERE b_id = ?";
      conf.deleteRecord(qry, bid);
  }
  
}
