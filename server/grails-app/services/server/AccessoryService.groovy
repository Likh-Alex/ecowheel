package server

import grails.gorm.services.Service

@Service(Accessory)
interface AccessoryService {

    Accessory get(Serializable id)

    List<Accessory> list(Map args)

    Long count()

    void delete(Serializable id)

    Accessory save(Accessory accessory)

}