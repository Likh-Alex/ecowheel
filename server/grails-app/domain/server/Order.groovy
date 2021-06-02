package server

class Order {
    Date date

    static belongsTo = [user: User]
    static hasMany = [vehicles: Vehicle, accessories: Accessory, giftCards: GiftCard]
    static constraints = {
    }
}
