package todo;


import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 *
 * @author ajayc
 */
public class Todo {
    
   private static final String newLine = System.getProperty("line.separator");
                                         //return OS dependent line seprator
    public static void main(String[] args) throws IOException {
        // TODO code application logic 
        
        String fileName = "myObjectives.txt"; // name of the file
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true)
        {
            System.out.println("Please Choose an option (1) Add a task "
                    + "(2) List all the tasks "
                    + "(3) Update a task (4) Remove a task (0) Exit ");
            choice =sc.nextInt();
            switch(choice) //user input 
            {
                case 1:
                    System.out.println("Add a task (Id, name,date(dd/mm/yyyy),"
                            + " status)");
                    Scanner ip = new Scanner(System.in);
                     TodoList todo = new TodoList();
                    int id = ip.nextInt();
                    todo.name = ip.next();
                    todo.date = ip.next();
                    todo.status=Status.valueOf(ip.next());
                    String note = String.valueOf(id)+"\t"+todo.name+"\t"
                            +todo.date+"\t"+todo.status;
                    PrintWriter myObj = null;
                    myObj = new PrintWriter(new FileOutputStream(fileName, 
                            true));
                    myObj.write(note + newLine);//writing into text file
                    myObj.close();// closing
                    break;
                case 2:
                    System.out.print("List of all the tasks\n");
                    BufferedReader br_list = new BufferedReader
                                      (new FileReader(fileName));
                    String st; 
                    while ((st = br_list.readLine()) != null) /*reading from 
                                                                 *text file
                                                                  */       
                        System.out.println(st); /* printing all the records
                                                            *in the text file
                                                */
                    break;
                case 3:
                    System.out.println("Enter the ID of the task you"
                            + " want to Update: ");
                    String find_id = sc.next();
                    String s;
                    String[] words =null;
                    String oldText = "";
                    BufferedReader br_update = new BufferedReader
                                                (new FileReader(fileName));
                    while ((s = br_update.readLine()) != null)
                    {
                        words = s.split("\t");
                        System.out.println(words[3]);//splitting on word length
                        if(words[0].equals(find_id))
                        {
                            System.out.println("Update the status");
                            sc.nextLine();
                            String new_status = sc.nextLine();
                            String replaced = s.replaceAll(words[3],
                                    new_status);//replacing that word with new 
                            oldText += replaced + "\n";
                            System.out.println(oldText);
                            break;
                        }
                        else
                        {
                            oldText += s +"\n";
                        }
                    }
                    FileWriter writer = new FileWriter(fileName);
                    writer.write(oldText);
                    writer.close();
                    break;
                case 4:
                    System.out.println("Enter the id to remove the task: ");
                    String remove_id = sc.next();//taking ID to remove 
                    BufferedReader reader = new BufferedReader
                                             (new FileReader(fileName));
                    String line = null;
                    String text = "";
                    while((line=reader.readLine())!=null)//reading text in file
                    {
                        if(!line.contains(remove_id))// if line has that ID 
                        {
                            text += line + "\n";
                        }
                    }
                    FileWriter remove = new FileWriter(fileName);
                    remove.write(text);
                    remove.close();
                    break;
                case 0:
                    System.exit(0);//exit 
            }
        }
    }
}
