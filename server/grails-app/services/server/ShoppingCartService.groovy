package server

import grails.gorm.services.Service

@Service(ShoppingCart)
interface ShoppingCartService {

    ShoppingCart get(Serializable id)

    List<ShoppingCart> list(Map args)

    Long count()

    void delete(Serializable id)

    ShoppingCart save(ShoppingCart shoppingCart)

}