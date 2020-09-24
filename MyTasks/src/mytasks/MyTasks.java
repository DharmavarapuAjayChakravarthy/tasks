package mytasks;
import java.util.TreeMap;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Set;
public class MyTasks {
    public static void main(String[] args) {
    int choice;
     HashMap<Integer,Todo> todoMap=new HashMap<>();		
    int id;
   
    Scanner input = new Scanner(System.in);
    while(true)
    {
    System.out.println("Please Choose an option (1) Add a task (2) Remove a task (3) Update a task (4) List all tasks (0) Exit ");
    choice = input.nextInt();
     switch (choice) {

        case 1:
            System.out.println("Add a task (Id, name,date(dd/mm/yyyy), status)");
            
            id=input.nextInt();
            Todo todo=new Todo();
            todo.name=input.next();
            todo.date=input.next();            
            todo.status=Status.valueOf(input.next());
            System.out.println(todo.status);
            todoMap.put(id,todo);
            //System.out.println(todoMap);
            break;
        case 2:
            System.out.println("Enter id to remove task");
            int removeid=input.nextInt();
           // todoMap.remove(removeid);
            System.out.println(todoMap.remove(removeid).getName());
            break;
        case 3:
            System.out.println("Enter id to update a task");
            Todo todoup=new Todo();
            int updateid=input.nextInt();
            String statusupdate=input.nextLine().trim();
            Todo existingTask =todoMap.get(updateid);
            if(null==existingTask)
            {
                System.out.println("invalid ID");
                break;
            }
            existingTask.setStatus(Status.valueOf(statusupdate));
            System.out.print("updated task"+ " " +existingTask.toString());
           
            break;
        case 4:
           System.out.println("List all tasks");
           //System.out.println(todoMap)
            TreeMap<Integer,Todo> sorted = new TreeMap<>();
            sorted.putAll(todoMap);
            Set<Integer> keys=sorted.keySet();
            for(Integer key: keys){
               System.out.print(key + " " +sorted.get(key).toString());
            }
            break;
        case 0:
            return;
        }
    }
}
}
    

