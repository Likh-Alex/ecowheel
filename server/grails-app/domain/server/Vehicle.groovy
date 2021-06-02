package server

class Vehicle {
    String name
    String brand
    Float weight
    Integer maximumRange
    Integer power
    Integer wheelSize
    Integer passengerCapacity
    Boolean builtInLight
    Float price
    String color
    Date manufactured

    static constraints = {
        name nullable: false
        brand nullable: false
    }
}
