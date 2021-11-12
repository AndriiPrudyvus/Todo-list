package startingdata;

import model.Task;

import java.util.HashMap;
import java.util.Map;

public  class StartingTasks {


    //  protected static Map<String, Task> tasks = new HashMap<>();
    public static Map<String, Task> tasks = new HashMap<String, Task>() {{
        put("task1", new Task("task1", "description1"));
        put("task2", new Task("task2", "description2"));
    }};

}
