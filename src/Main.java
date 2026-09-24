void main() {
    //creates a new "Adventure" object and executes main program
    Map map1 = new Map();
    Adventure adventure = new Adventure(map1);
    adventure.userInterface();

//    Map testMap = new Map();
//    testMap.newRoom("Joe Mama's room");
//    testMap.roomAddDesc(1, "it's fat wide and stinky");
//    testMap.roomSetDirs(1, testMap.getRoomByNumber(1),null,null,null);
//    IO.println(testMap.roomDesc(1));
}

