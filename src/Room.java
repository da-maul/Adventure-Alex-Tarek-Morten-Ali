public class Room {
    private String name;
    private String description;
    private Room north;
    private Room east;
    private Room south;
    private Room west;

    public Room(String name){
        this.name = name;
    }

    public void setDescription(String description) {this.description = description;}

    public void setNorth(Room north) {this.north = north;}
    public void setEast(Room east) {this.east = east;}
    public void setSouth(Room south) {this.south = south;}
    public void setWest(Room west) {this.west = west;}

    //getters
    public String getName() {return name;}
    public String getDescription() {return description;}

    public Room getNorth() {
        if (north != null){return north;}
        else {return this;}
        }
    public Room getEast() {
        if (east != null){return east;}
        else {return this;}
    }
    public Room getSouth() {
        if (south != null){return south;}
        else {return this;}
    }
    public Room getWest() {
        if (west != null){return west;}
        else {return this;}
    }
}
