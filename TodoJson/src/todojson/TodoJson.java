/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package todojson;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.BufferedReader;
//import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
//import org.json.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;
import org.json.simple.JSONValue;



/**
 *
 * @author ajayc
 */
public class TodoJson {

   private static final String newLine = System.getProperty("line.separator");
    public static void main(String[] args) throws IOException  {
        // TODO code application logic 
        
        String fileName = "json.txt";
        Scanner sc = new Scanner(System.in);
        int choice;
        while(true)
        {
            System.out.println("Please Choose an option (1) Add a task (2) List all the tasks (3) Update a task (4) Remove a task (0) Exit ");
            choice =sc.nextInt();
            JSONObject jsonObject = new JSONObject();
            switch(choice)
            {
                case 1:
                    System.out.println("Add a task (Id, name,date(dd/mm/yyyy), status)");
                    Scanner ip = new Scanner(System.in);
                    Todo todo = new Todo();
                    int id = ip.nextInt();
                     todo.name = ip.next();
                    todo.date = ip.next();
                    //todo.getStatus() = ip.nextLine();
                     todo.status=Status.valueOf(ip.next());
                    String note = String.valueOf(id)+"\t"+todo.name+"\t"+todo.date+"\t"+todo.status;
                    //PrintWriter myObj = null;
                    jsonObject.put("id",id);
                    jsonObject.put("name",todo.name);
                    jsonObject.put("date",todo.date);
                    jsonObject.put("status",todo.status);
                    //Todo todonew = (Todo)JSONValue.parse(jsonObject.toJSONString());
                    //System.out.println(todo);
                    try {
                        FileWriter file = new FileWriter(fileName,true);
                        
                        file.write(jsonObject.toJSONString()+ newLine);
                         //jsonObject.write(note + newLine);
                        file.close();
                     } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                     }
                     //System.out.println("JSON file created: "+jsonObject);
                    break;
                case 2:
                    System.out.print("List of all the tasks\n");
                    BufferedReader br_list = new BufferedReader(new FileReader(fileName));
                    String st; 
                    while ((st = br_list.readLine()) != null) 
                        System.out.println(st); 
                    break;
                case 3:
                    System.out.println("Enter the ID of the task you want to Update: ");
                    String find_id = sc.next();
                    System.out.println("Enter New Status");
                     String newStatus = sc.next();
                    BufferedReader br_update = new BufferedReader(new FileReader(fileName));
                       // todoObject.getJSON("status").put("status",newStatus);
                        Todo todoObject = (Todo)JSONValue.parse(jsonObject.toJSONString());
                        System.out.println(todoObject.status);
                        FileWriter writer = new FileWriter(fileName);
                    writer.close();
                    break;
                case 4:
                    System.out.println("Enter the id to remove the task: ");
                    String remove_id = sc.next();
                    BufferedReader reader = new BufferedReader(new FileReader(fileName));
                    String line = null;
                    String text = "";
                    while((line=reader.readLine())!=null)
                    {
                        if(!line.contains(remove_id))
                        {
                            text += line + "\n";
                        }
                    }
                    FileWriter remove = new FileWriter(fileName);
                    remove.write(text);
                    remove.close();
                    break;
                case 0:
                    System.exit(0);
            }
        }
    }
}

