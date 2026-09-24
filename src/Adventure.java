import java.lang.reflect.Array;

public class Adventure {


    //Gee willickers this really needed to be its own class guys...
    public void run(){
        final Map map = new Map(); map.initMap1();
        final ConsoleUI console = new ConsoleUI();

        console.run(map);
    }
    //I suppose this is more expandable?

}
