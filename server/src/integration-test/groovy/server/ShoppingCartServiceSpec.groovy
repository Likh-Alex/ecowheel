package server

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class ShoppingCartServiceSpec extends Specification {

    ShoppingCartService shoppingCartService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new ShoppingCart(...).save(flush: true, failOnError: true)
        //new ShoppingCart(...).save(flush: true, failOnError: true)
        //ShoppingCart shoppingCart = new ShoppingCart(...).save(flush: true, failOnError: true)
        //new ShoppingCart(...).save(flush: true, failOnError: true)
        //new ShoppingCart(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //shoppingCart.id
    }

    void "test get"() {
        setupData()

        expect:
        shoppingCartService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<ShoppingCart> shoppingCartList = shoppingCartService.list(max: 2, offset: 2)

        then:
        shoppingCartList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        shoppingCartService.count() == 5
    }

    void "test delete"() {
        Long shoppingCartId = setupData()

        expect:
        shoppingCartService.count() == 5

        when:
        shoppingCartService.delete(shoppingCartId)
        sessionFactory.currentSession.flush()

        then:
        shoppingCartService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        ShoppingCart shoppingCart = new ShoppingCart()
        shoppingCartService.save(shoppingCart)

        then:
        shoppingCart.id != null
    }
}
