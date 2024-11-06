
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

        System.out.print("Enter Action: ");
        int action = sc.nextInt();
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
      System.out.print("Book Genre: ");
      String bgen = sc.nextLine();
      System.out.print("Book Author: ");
      String bauth = sc.nextLine();
      System.out.print("Book Status: ");
      String bstat = sc.nextLine();
      
      String qry = "INSERT INTO tbl_books(b_name, b_genre, b_author, b_status) VALUES (?, ?, ?, ?)";
      config conf = new config();
      
      conf.addRecord(qry, bname, bgen, bauth, bstat);
      
      System.out.print("Do you want to add another book? (yes/no): ");
      continueAdding = sc.nextLine();
      
      }while (continueAdding.equalsIgnoreCase("yes"));
  }
  
  private void viewBooks(){
      
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
      System.out.print("New Book Genre: ");
      String bgen = sc.nextLine();
      System.out.print("New Book Author: ");
      String bauth = sc.nextLine();
      System.out.print("New Book Status: ");
      String bstat = sc.nextLine();
      
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
