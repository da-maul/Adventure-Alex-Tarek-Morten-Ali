public class Player {
    private Room currentRoom;

    //Set the player in the start room.
    public void setStartingRoom(Room startingRoom) {
        currentRoom = startingRoom;
    }

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public boolean moveNorth() {
        if (currentRoom == null) {
            return false;
        }

        return moveTo(currentRoom.getNorth());
    }

    public boolean moveEast() {
        if (currentRoom == null) {
            return false;
        }

        return moveTo(currentRoom.getEast());
    }

    public boolean moveSouth() {
        if (currentRoom == null) {
            return false;
        }

        return moveTo(currentRoom.getSouth());
    }

    public boolean moveWest() {
        if (currentRoom == null) {
            return false;
        }

        return moveTo(currentRoom.getWest());
    }

    // Moving the player.
    private boolean moveTo(Room nextRoom) {
        if (nextRoom == null || nextRoom == currentRoom) {
            return false;
        }

        currentRoom = nextRoom;
        return true;
    }

    // The order of the rooms in the array determines their numbers.
    public boolean xyzzy(int roomNumber, Room[] rooms) {
        if (rooms == null ||
                roomNumber < 1 ||
                roomNumber > rooms.length) {
            return false;
        }

        Room destination = rooms[roomNumber - 1];

        if (destination == null) {
            return false;
        }

        currentRoom = destination;
        return true;
    }
}