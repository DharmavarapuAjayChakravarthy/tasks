/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package newhashtodo;

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

/**
 *
 * @author ajayc
 */
public class NewHashTodo {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int choice;
        HashMap < Integer, Todo > todoMap = new HashMap < > (); //taking Integer & Todo class 		
        int id;

        Scanner input = new Scanner(System.in);
        while (true) {
            System.out.println("Please Choose an option (1) Add a task (2)Remove a task" +
                " (3) Update a task (4) List all tasks (0) Exit ");
            choice = input.nextInt();
            switch (choice) {

                case 1:
                    System.out.println("Add a task (Id, name,date(dd/mm/yyyy)," +
                        " status)");
                    id = input.nextInt(); //taking id as input
                    Todo todo = new Todo(); //creating todo object
                    todo.name = input.next(); //name input
                    todo.date = input.next(); //date input           
                    todo.status = Status.valueOf(input.next()); //status input
                    todoMap.put(id, todo); //storing in hashmap(in memory)
                    break;
                case 2:
                    System.out.println("Enter id to remove task");
                    int removeid = input.nextInt(); //taking id of the record to remove 
                    todoMap.remove(removeid);
                    break;
                case 3:
                    System.out.println("Enter id to update a task");
                    Todo todoup = new Todo();
                    int updateid = input.nextInt(); //taking id to be updated
                    String statusupdate = input.nextLine().trim();
                    /*trimming the user 
                     *input
                     */
                    Todo existingTask = todoMap.get(updateid);
                    if (null == existingTask) {
                        System.out.println("invalid ID");
                        break;
                    }
                    existingTask.setStatus(Status.valueOf(statusupdate));
                    /*updating the 
                     *status
                     */
                    System.out.print("updated task" + " " + existingTask.toString());
                    break;
                case 4:
                    System.out.println("List all tasks");
                    //System.out.println(todoMap)
                    TreeMap < Integer, Todo > sorted = new TreeMap < > ();
                    sorted.putAll(todoMap);
                    Set < Integer > keys = sorted.keySet();
                    for (Integer key: keys) {
                        System.out.print(key + " " + sorted.get(key).toString());
                    }
                    break;
                case 0:
                    return;
            }
        }
    }
}
