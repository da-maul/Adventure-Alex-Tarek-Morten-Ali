import java.util.ArrayList;

public class Map {

    ArrayList<Room> rooms = new ArrayList<Room>();

    public void newRoom(String name){rooms.add(new Room(name));}
    public void roomAddDesc(int roomNumber,String description){rooms.get(roomNumber-1).setDescription(description);}
    public void roomSetDirs(int roomNumber, int northNum, int eastNum, int southNum, int westNum){
        //sets adjacent rooms by room number, first is the room in question
        //the following 4 are rooms by their number in NESW order, empty directions are replaced by NULL

        //initialize working variables
        Room workingRoom = rooms.get(roomNumber-1);
        Room north = rooms.get(northNum-1);
        Room east = rooms.get(eastNum-1);
        Room south = rooms.get(southNum-1);
        Room west = rooms.get(westNum-1);
        //assign rooms, Room setter functions can handle NULL by doing nothing.
        workingRoom.setNorth(north);
        workingRoom.setEast(east);
        workingRoom.setSouth(south);
        workingRoom.setWest(west);
    }
    public String roomDesc(int roomNumber){return rooms.get(roomNumber-1).getDescription();}

    public Room getRoom(int roomNumber){return rooms.get(roomNumber-1);}

    public void makeLit(int roomNumber){
        //lights a room, rooms are dark by default
        rooms.get(roomNumber-1).setLit(true);
    }
}
