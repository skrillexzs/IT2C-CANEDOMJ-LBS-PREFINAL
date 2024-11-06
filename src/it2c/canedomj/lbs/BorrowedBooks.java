
package it2c.canedomj.lbs;

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
            
            System.out.print("Enter Action: ");
            int action = sc.nextInt();
            
            BorrowedBooks bwdb = new BorrowedBooks();
            
            switch(action){
                
               
                case 1:
                    
                    bwdb.addBorrowedBooks();
                    bwdb.vieBorrowedBooks();
                    
                    break;
                    
                case 2:
                   
                    bwdb.viewBorrowedBooks();
                    
                    break;
                    
                case 3:
                    
                    bwdb.viewBorrowedBooks();
                    bwdb.updateBorrowedBooks();
                               
                    break;
            
                case 4: 
                    
                    bwdb.viewBorrowedBooks();
                    bwdb.deleteBorrowedBooks();
                    bwdb.viewBorrowedBooks();
                                     
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
            Scanner sc = new Scanner(System.in);
            config conf = new config();
            Customer cust = new Customer();
            
            System.out.println("-------------------");
            System.out.println("CUSTOMER TABLE");
            cust.viewCustomers();
            
            System.out.print("Enter Selected Customer ID: ");
            int cid = sc.nextInt();
            
            String csql = "SELECT id FROM customer WHERE id = ?";
            while(conf.getSingleValue(csql, cid)== 0){
                System.out.print("Customer does not exist, Please Select Again: " );
                cid = sc.nextInt();
            }            
            
            Product prod = new Product();
            System.out.println("-------------------");
            System.out.println("PRODUCT TABLE");
            prod.viewProducts();
            
             System.out.print("Enter Selected Product ID: ");
            int pid = sc.nextInt();
            
            String psql = "SELECT p_id FROM product WHERE p_id = ?";
            while(conf.getSingleValue(psql, pid)== 0){
                System.out.print("Product does not exist, Please Select Again: ");
                pid = sc.nextInt();
        }
            System.out.print("Enter quantity: ");
            double quantity = sc.nextDouble();
            
            String priceqry = "SELECT p_price FROM product WHERE p_id = ?";
            double price = conf.getSingleValue(priceqry, pid);
            double due = price * quantity;
                    
            System.out.println("-----------------------");
            System.out.println("Total Due: "+due);
            System.out.println("-----------------------");
                                                                      
            
            LocalDate currdate = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = currdate.format(format);
            
            String status = "Pending";
            
                String orderqry = "INSERT INTO tbl_order (id, p_id, o_quantity, o_due, o_date, o_status) "
                        + "VALUES (?, ?, ?, ?, ?, ?)";
                
                conf.addRecords(orderqry, cid, pid, quantity, due, date, status);

}
        
        
        public void viewBorrowedBooks() {
            String Cmquery = "SELECT o_id, lname, p_name, o_due, o_date, o_status FROM tbl_order "
                    + "LEFT JOIN customer ON customer.id = tbl_order.id "
                    + "LEFT JOIN product ON product.p_id = tbl_order.p_id";
                  
        
        String[] hdrs = {"Order id","Customer LastName", "Product Name",  "Due", "Date", "Status"};
        String[] colmns = {"o_id","lname", "p_name",  "o_due", "o_date", "o_status"};
        config conf = new config();
        conf.viewRecords(Cmquery, hdrs, colmns);
        
        }
        
         private void updateBorrowedBooks(){
        Scanner sc = new Scanner(System.in);
        Order or = new Order();
        Customer cs = new Customer();
        Product pr = new Product();
        config conf = new config();
        
        
        System.out.print("Enter Order ID: ");
        int oid = sc.nextInt();
        
        String osql = "SELECT o_id FROM tbl_order WHERE o_id = ?";
            while(conf.getSingleValue(osql, oid)== 0){
                System.out.print("Order ID does not exist, Please Select Again: " );
                oid = sc.nextInt();
            }
        
        cs.viewCustomers();
            
            System.out.print("Enter New Selected Customer ID: ");
            int ncid = sc.nextInt();
            
            String csql = "SELECT id FROM customer WHERE id = ?";
            while(conf.getSingleValue(csql, ncid)== 0){
                System.out.print("Customer does not exist, Please Select Again: " );
                ncid = sc.nextInt();
            }
                pr.viewProducts();
        
        System.out.print("Enter New Selected Product ID: ");
            int npid = sc.nextInt();
            
            String psql = "SELECT p_id FROM product WHERE p_id = ?";
            while(conf.getSingleValue(psql, npid)== 0){
                System.out.print("Product does not exist, Please Select Again: ");
                npid = sc.nextInt();
        }

        
        System.out.print("Enter new Quantity:");
        double nquant = sc.nextDouble();
        
         String priceqry = "SELECT p_price FROM product WHERE p_id = ?";
            double price = conf.getSingleValue(priceqry, npid);
            double due = price * nquant;
                    
            System.out.println("-----------------------");
            System.out.println("Total Due: "+due);
            System.out.println("-----------------------");
                                                                      
            
            LocalDate currdate = LocalDate.now();
            DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            String date = currdate.format(format);
            
            String status = "Pending";

        
        String sql = "UPDATE tbl_order SET id = ?, p_id = ?, o_quantity = ?, o_due = ?, o_date = ?, o_status = ? WHERE o_id = ?";
        
        conf.updateRecords(sql, ncid, npid, nquant, due, date, status, oid);
        
        
    }
         
          private void deleteBorrowedBooks() {      
            Scanner sc = new Scanner(System.in);        
            System.out.print("Enter Order ID to Delete: ");
            int oid = sc.nextInt();

            String sqlDelete = "DELETE FROM tbl_order WHERE o_id = ?";
        
            config conf = new config();uj
            conf.deleteRecords(sqlDelete, oid);

    }
}
    
