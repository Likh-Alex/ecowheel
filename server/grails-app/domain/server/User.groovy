package server

class User {

    String firstName
    String lastName
    Date birthDate
    Date registrationDate
    String phoneNumber
    String gender
    String shippingAddress

    enum Discount {
        NORMAL(0), BRONZE(5), SILVER(10), GOLD(15)
    }

    static hasMany = [orders: Order]

//    1 - Firstname
//    2 - Lastname
//    3 - Phonenumber
//    4 - Birthdate
//    5 - Gender
//    6 - Registration date
//    7 - Discount type
//    8 - Address
    static constraints = {
    }
}
