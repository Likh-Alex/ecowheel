package server

class Accessory {
    String name
    Type type
    String description
    Float price

//    1 - name
//    2 - type
//    3 - description
//    4 - price
//    types:
//    1 - Helmets
//    2 - Backpacks
//    3 - Glasses
//    4 - Gloves
    enum Type {
        HELMET, BACKPACK, GLASSES, GLOVES
    }
    static constraints = {
    }
}
