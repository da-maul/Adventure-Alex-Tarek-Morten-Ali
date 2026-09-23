import java.lang.reflect.Array;

public class Adventure {
    private Room currentRoom;


    Room room1 = new Room("Entryway (Room 1)");
    Room room2 = new Room("Dry Corridor (Room 2)");
    Room room3 = new Room("Warehouse (Room 3)");
    Room room4 = new Room("Armory (Room 4)");
    Room room5 = new Room("Treasury (Room 5)");
    Room room6 = new Room("Kitchen (Room 6)");
    Room room7 = new Room("Barracks (Room 7)");
    Room room8 = new Room("Wet Corridor (Room 8)");
    Room room9 = new Room("Mess Hall (Room 9)");

    String[] commands = {"NORTH","EAST","SOUTH","WEST","LIGHT","DARKNESS","XYZZY","EXIT"};



    public void roomsInit(){
        //room1
        room1.setEast(room2); room1.setSouth(room4);
        room1.setLit(true);
        room1.setDescription("""
                Light filters into this room from a hole in the ceiling, bathing it in a pale glow.
                There are two doors. One is to your EAST and another is to the SOUTH
                """);
        //room2
        room2.setWest(room1); room2.setEast(room3);
        room2.setLit(true);
        room2.setDescription("""
                This dismal corridor is lit by a doorway to the WEST and grows darker as it heads to the EAST.
                """);
        //room3
        room3.setWest(room2); room3.setSouth(room6);
        room3.setDescription("""
                This is a storage room, all manner of ruined barrels and crates are lined up in rows, making it difficult to navigate
                There is a doorway to the WEST which leaks a bit of light, and another to the SOUTH.
                """);
        //room4
        room4.setNorth(room1); room4.setSouth(room7);
        room4.setLit(true);
        room4.setDescription("""
                Dimly lit and a little humid, this room is lined with racks of rusty weapons and trunks filled with ratty sets of armor.
                There is a door to the NORTH through which light leaks, and one to the SOUTH which looks moldy and damp.
                """);
        //room5
        room5.setSouth(room8);
        room5.setDescription("""
                A Treasure room! or at least it used to be. There's empty chests and some scattered copper coins.
                Seems it was looted a long time ago...
                There's only the door to the SOUTH that you came in from.
                """);
        //room6
        room6.setNorth(room3); room6.setSouth(room9);
        room6.setDescription("""
                Bleugh... it smells awful in this disgusting kitchen, unclean tables and moldy pots of rotten foodstuffs are scattered around.
                Fresh air flows in from the NORTH, the rotten door to the SOUTH looks damaged by water.
                """);
        //room7
        room7.setNorth(room4); room7.setEast(room8);
        room7.setDescription("""
                Waterlogged beds and mattresses float in this flooded room, you surmise it used to be a barracks.
                The door to the NORTH seems dryer... water seems to flow in from the EAST.
                """);
        //room8
        room8.setNorth(room5); room8.setWest(room7); room8.setEast(room9);
        room8.setDescription("""
                There's a stream running through this corridor flowing from EAST to WEST
                to the NORTH there's a section of wall that juts out. You realize it's an open SECRET DOOR...
                """);
        //room9
        room9.setNorth(room6); room9.setWest(room8);
        room9.setDescription("""
                Long tables and benches are staggered about the room haphazardly, with scattered pewter plates and utensils all about.
                An overturned pitcher on one of the tables endlessly spews forth water, which trickles in a stream to the WEST
                A foul odor sneaks in from the door to the NORTH, and the stream of water disappears into a corridor leading WEST.
                """);
    }

    public void userInterface(){
        roomsInit();
        IO.println("""
                    
                You awaken with a start, a bump on the back of your head, your whole body feels bruised.
                You open your eyes and are dazzled by the sun, sitting up, you realize you're surrounded by walls
                on all sides. You're in a damp, cool room with walls of grey cobblestone, dark moss growing in
                the cracks. A dungeon... You rise to your feet and dust yourself off, determined to make your way
                out of here...
                """);
        currentRoom = room1;
        boolean playing = true;
        while (playing){
            IO.println("You are in the "+ currentRoom.getName());
            if (currentRoom.isLit()){IO.println(currentRoom.getDescription());}
            else {IO.println("You strain your eyes, it's too dark to see. You need some LIGHT");}


            boolean playerInputIsInvalid = true;
            while (playerInputIsInvalid) {
                String input = IO.readln("What do you do?");
                input = handleInput(input);
                //IO.println("player's input is:"+ input); //Debug Line
                Room roomMovedTo = currentRoom;
                boolean inputWasNotMove = false;
                boolean lightWasCast = false;
                switch (input) {
                    case "NORTH" -> roomMovedTo = currentRoom.getNorth();
                    case "EAST" -> roomMovedTo = currentRoom.getEast();
                    case "SOUTH" -> roomMovedTo = currentRoom.getSouth();
                    case "WEST" -> roomMovedTo = currentRoom.getWest();
                    case "XYZZY" -> {xyzzy(); inputWasNotMove = true;}
                    case "LIGHT" -> {
                        currentRoom.setLit(true); inputWasNotMove = true; lightWasCast = true;
                    }
                    case "DARKNESS" -> {
                        currentRoom.setLit(false); inputWasNotMove = true; lightWasCast = true;
                    }
                    case "HELP" -> {
                        IO.print("Commands are: ");
                        for (String command :commands){
                            IO.print(command+", ");
                        }
                        IO.println();
                        inputWasNotMove = true;
                    }
                    case "EXIT" -> {playing = false; return;}
                    default -> roomMovedTo = currentRoom;
                }
                if (inputWasNotMove && lightWasCast){
                    if (currentRoom.isLit()){
                        IO.println("You cast a spell of light on this room!\n");}
                    else {IO.println("You cast a spell and cloak this room in darkness...\n");}
                    break;
                }
                else if (inputWasNotMove) {break;}
                else if (roomMovedTo == currentRoom) {
                    IO.println("You get confused and bump into a wall...");
                }
                else {currentRoom = roomMovedTo; playerInputIsInvalid = false;}
            }
        }
    }
    private void xyzzy() {
        Room[] teleportRooms = {room1,room2,room3,room4,room5,room6,room7,room8,room9};
        int answer = Integer.parseInt(IO.readln("Where do you want to teleport?"));
        currentRoom = teleportRooms[answer - 1];
    }

    private String handleInput(String input){
        input = input.toUpperCase();
        switch (input){
            case "GO NORTH" -> input = "NORTH";
            case "N" -> input = "NORTH";
            case "GO EAST" -> input = "EAST";
            case "E" -> input = "EAST";
            case "GO SOUTH" -> input = "SOUTH";
            case "S" -> input = "SOUTH";
            case "GO WEST" -> input = "WEST";
            case "W" -> input = "WEST";
            case "CAST LIGHT" -> input = "LIGHT";
            case "TURN ON LIGHT"-> input = "LIGHT";
            case "CAST DARKNESS" -> input = "DARKNESS";
            case "TURN OFF LIGHT" -> input = "DARKNESS";
            default -> input = input;
        }
        return input;
    }
}
