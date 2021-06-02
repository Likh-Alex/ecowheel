package server

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class AccessoryServiceSpec extends Specification {

    AccessoryService accessoryService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Accessory(...).save(flush: true, failOnError: true)
        //new Accessory(...).save(flush: true, failOnError: true)
        //Accessory accessory = new Accessory(...).save(flush: true, failOnError: true)
        //new Accessory(...).save(flush: true, failOnError: true)
        //new Accessory(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //accessory.id
    }

    void "test get"() {
        setupData()

        expect:
        accessoryService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Accessory> accessoryList = accessoryService.list(max: 2, offset: 2)

        then:
        accessoryList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        accessoryService.count() == 5
    }

    void "test delete"() {
        Long accessoryId = setupData()

        expect:
        accessoryService.count() == 5

        when:
        accessoryService.delete(accessoryId)
        sessionFactory.currentSession.flush()

        then:
        accessoryService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Accessory accessory = new Accessory()
        accessoryService.save(accessory)

        then:
        accessory.id != null
    }
}
