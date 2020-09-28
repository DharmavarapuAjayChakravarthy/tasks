package todolistdb;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.Scanner;

/**
 *
 * @author ajayc
 */
public class Todolistdb {
  
    public static void main(String[] args) {      
    
      Connection c = null;
      Statement stmt = null;
      try {
         Class.forName("org.postgresql.Driver");
         c = DriverManager.getConnection("jdbc:postgresql://localhost:5432/mydb",
                 "postgres", "1263");/* postgres connection string 
                                      *with username and password*/
         System.out.println("Opened database successfully");
         stmt = c.createStatement();
         String sql = "CREATE TABLE IF NOT EXISTS Todo " +
            "(ID INT PRIMARY KEY     NOT NULL," +
            " NAME           TEXT    NOT NULL, " +
            " DATE           TEXT     NOT NULL, " +
            " STATUS         TEXT     NOT NULL  )";//Create table if not exists
         stmt.executeUpdate(sql);
         stmt.close();
         //c.close();
      } catch ( Exception e ) {
       System.out.println("Cannot create Table");
      }
      System.out.println("Table created successfully");
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true)
        {
            System.out.println("Please Choose an option (1) Add a task "
                    + "(2) List all the tasks (3)"
                    + " Update a task (4) Remove a task (0) Exit ");
            choice =sc.nextInt();//getting user input to perform which operation
            switch (choice)
            {
                case 1:
                    System.out.println("Add a task (Id, name,date(dd/mm/yyyy),"
                            + " status)");
                    Scanner ip = new Scanner(System.in);
                    Todo todo = new Todo();
                    System.out.println("Enter ID"); //user id
                    int id = ip.nextInt();
                     System.out.println("Enter Name");//user name
                     todo.name = ip.next();
                     System.out.println("Enter Date");//date 
                     todo.date = ip.next();
                     System.out.println("Enter Status");// status of the work
                     todo.status=ip.next();
                    try{
                        PreparedStatement st = c.prepareStatement("INSERT INTO"
                                + " todo (id, name, date, status)"
                                + " VALUES (?, ?, ?, ?)");// inserting records 
                            st.setInt(1, id);
                            st.setString(2,todo.name);
                            st.setString(3, todo.date);
                            st.setString(4, todo.status);
                            st.executeUpdate();
                            //st.close();
                        // c.close();
                        System.out.println("Inserted records successfully");
                       // c.close();
                     } catch (Exception ex) {
                        System.out.println( "Cannot insert" );
                        //System.exit(0);
                     }
                    break;
                case 2:
                    System.out.print("List of all the tasks\n");
                    try{
                        stmt = c.createStatement();
                        ResultSet rs = stmt.executeQuery( "SELECT * FROM"
                                + " todo ORDER BY id ASC;" );
                        /*getting all the details of the user in db */
                        while ( rs.next() ) {
                           int uid = rs.getInt("id");
                           String  uname = rs.getString("name");
                           String udate  = rs.getString("date");
                           String  ustatus = rs.getString("status");
                           System.out.println( "ID:" + uid + ", NAME = "
                                   + uname + ", Date = " 
                                   + udate +", STATUS = " + ustatus  );   
                           /* assigning keys with values */
                        }
                     } catch ( Exception exp ) {
                         System.out.println("cannot retrive details");
                     }
                     break;
   
               case 3:
                 System.out.println("Enter the ID of the task you "
                         + "want to Update: ");
                    Scanner iput = new Scanner(System.in);
                    System.out.println("Enter ID");
                    int update_id = iput.nextInt();
                    System.out.println("Entet Status to be Updated");
                    String update_status = iput.next();
                    try{
                         PreparedStatement st = c.prepareStatement("UPDATE todo"
                                 + " set status = ? where ID=?;");
                         /* updating records */
                            st.setString(1,update_status);
                            st.setInt(2,update_id);
                            st.executeUpdate();
                            System.out.println("Updated Successfully");
                     }
                catch ( Exception exp ) {
                         System.out.println("cannot update");
                     }
                     break;
                case 4:
                    System.out.println("Enter the id to remove the task: ");
                    Scanner input = new Scanner(System.in);
                    System.out.println("Enter ID");
                    int remove_id = input.nextInt();
                     try{
                         PreparedStatement st = c.prepareStatement("DELETE FROM"
                                 + " todo WHERE id = ?");
                         /* delete on id */
                            st.setInt(1, remove_id);
                            st.executeUpdate();
                            System.out.println("Deleted Successfully");
                     }
                catch ( Exception exp ) {
                         System.out.println("cannot delete");
                     }
                     break;
                case 0:
                    return;
                  
           } 

       }
   }
}
