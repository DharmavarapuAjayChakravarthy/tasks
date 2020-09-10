

import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;

public class MyTasks{


   
    

public static void main(String[] args) {
    int choice;
     HashMap<Integer,String> names=new HashMap<>();
     HashMap<Integer,String> dates=new HashMap<>();
     HashMap<Integer,String> statusList=new HashMap<>();
    String name,date,status;
    int id;
    Scanner input = new Scanner(System.in);
    while(true)
    {
    System.out.println("Please Choose an option (1) Add a task (2) Remove a task (3) Update a task (4) List all tasks (0) Exit ");
    choice = input.nextInt();
     switch (choice) {

        case 1:
            System.out.println("Add a task (Id, name,date, status)");
            id=input.nextInt();
            name=input.next();
            date=input.next();            
            status=input.next();
            names.put(id, name);
            dates.put(id, date);
            statusList.put(id, status);
            break;
        case 2:
            System.out.println("Enter id to remove task");
            int removeid=input.nextInt();
            names.remove(removeid);
            dates.remove(removeid);
            statusList.remove(removeid);
            break;
        case 3:
            System.out.println("Enter id to update a task");
            int updateid=input.nextInt();
            String statusupdate=input.nextLine();
            statusList.replace(updateid, statusupdate);
            break;
        case 4:
            System.out.println("List all tasks");
            Set<Integer> keys = names.keySet();
            for(Integer key: keys){
               System.out.print(key + " " +names.get(key)+" ");
               System.out.print(dates.get(key)+ " ");
               System.out.println(statusList.get(key));
            }
            break;
        case 0:
            return;
        }
    }
}
}