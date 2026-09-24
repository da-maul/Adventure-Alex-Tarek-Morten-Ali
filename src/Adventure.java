import java.lang.reflect.Array;

public class Adventure {
    private Map map = new Map();

    //displayed when someone types out the HELP command (candidate for rewrite)
    String[] commands = {"NORTH","EAST","SOUTH","WEST","LIGHT","DARKNESS","XYZZY","EXIT"};

    public void userInterface(){
        //start message
        IO.println("""
                    
                You awaken with a start, a bump on the back of your head, your whole body feels bruised.
                You open your eyes and are dazzled by the sun, sitting up, you realize you're surrounded by walls
                on all sides. You're in a damp, cool room with walls of grey cobblestone, dark moss growing in
                the cracks. A dungeon... You rise to your feet and dust yourself off, determined to make your way
                out of here...
                """);
        //main loop
        while (true){
            IO.println("You are in the "+ currentRoom.getName());
            if (currentRoom.isLit()){IO.println(currentRoom.getDescription());}
            else {IO.println("You strain your eyes, it's too dark to see. You need some LIGHT");}

            //input loop plays until player makes a valid move
            boolean playerInputIsInvalid = true;
            while (playerInputIsInvalid) {
                String input = IO.readln("What do you do?");
                //similar inputs are concatenated and made UPPERCASE in this function
                input = handleInput(input);
                //roomMovedTo defaults to the current room because not all commands move the player
                Room roomMovedTo = currentRoom;
                //bools for post-switch-statement sorting
                boolean inputWasNotMove = false;
                boolean lightWasCast = false;
                switch (input) {
                    case "NORTH" -> roomMovedTo = currentRoom.getNorth();
                    case "EAST" -> roomMovedTo = currentRoom.getEast();
                    case "SOUTH" -> roomMovedTo = currentRoom.getSouth();
                    case "WEST" -> roomMovedTo = currentRoom.getWest();
                    case "XYZZY" -> {xyzzy(); inputWasNotMove = true;}
                    case "LIGHT" -> {
                        currentRoom.setLit(true); inputWasNotMove = true; lightWasCast = true;}
                    case "DARKNESS" -> {
                        currentRoom.setLit(false); inputWasNotMove = true; lightWasCast = true;}
                    case "HELP" -> {
                        IO.print("Commands are: ");
                        for (String command :commands){
                            IO.print(command+", ");
                        }
                        IO.println();
                        inputWasNotMove = true;}
                    case "EXIT" -> {return;}
                }
                //if the player cast light or darkness
                if (inputWasNotMove && lightWasCast){
                    if (currentRoom.isLit()){
                        IO.println("You cast a spell of light on this room!\n");}
                    else {IO.println("You cast a spell and cloak this room in darkness...\n");}
                    break;
                }
                //if they did not move but had a valid input
                else if (inputWasNotMove) {break;}
                //if they tried to move to an invalid location.
                else if (roomMovedTo == currentRoom) {
                    IO.println("You get confused and bump into a wall...");
                }
                //I don't think this can ever be hit? IDK, worth keeping here.
                else {currentRoom = roomMovedTo; playerInputIsInvalid = false;}
            }
        }
    }
    private void xyzzy() {
        //this method needs a little work so the output is clean if the player inputs an out-of-bounds room
        //should be made to return a boolean and then have an if/else in main switch statement to change the cleaning behavior
        Room[] teleportRooms = {room1,room2,room3,room4,room5,room6,room7,room8,room9};
        int answer = Integer.parseInt(IO.readln("Where do you want to teleport?"));
        //hacky line to fix OOB inputs
        if (1 > answer || 9 < answer){IO.println("There is no room by that number..."); return;}
        currentRoom = teleportRooms[answer - 1];
    }

    private String handleInput(String input){
        //method boils down similar inputs, correct inputs do not need to be changed and are handled by "default"
        input = input.toUpperCase();
        switch (input){
            case "GO NORTH", "N" -> input = "NORTH";
            case "GO EAST", "E" -> input = "EAST";
            case "GO SOUTH", "S" -> input = "SOUTH";
            case "GO WEST", "W" -> input = "WEST";
            case "CAST LIGHT", "TURN ON LIGHT", "L" -> input = "LIGHT";
            case "CAST DARKNESS", "TURN OFF LIGHT", "D" -> input = "DARKNESS";
            default -> {return input;}
        }
        return input;
    }
}
