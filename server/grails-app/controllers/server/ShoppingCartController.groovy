package server

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class ShoppingCartController {

    ShoppingCartService shoppingCartService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond shoppingCartService.list(params), model:[shoppingCartCount: shoppingCartService.count()]
    }

    def show(Long id) {
        respond shoppingCartService.get(id)
    }

    def create() {
        respond new ShoppingCart(params)
    }

    def save(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            notFound()
            return
        }

        try {
            shoppingCartService.save(shoppingCart)
        } catch (ValidationException e) {
            respond shoppingCart.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'shoppingCart.label', default: 'ShoppingCart'), shoppingCart.id])
                redirect shoppingCart
            }
            '*' { respond shoppingCart, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond shoppingCartService.get(id)
    }

    def update(ShoppingCart shoppingCart) {
        if (shoppingCart == null) {
            notFound()
            return
        }

        try {
            shoppingCartService.save(shoppingCart)
        } catch (ValidationException e) {
            respond shoppingCart.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'shoppingCart.label', default: 'ShoppingCart'), shoppingCart.id])
                redirect shoppingCart
            }
            '*'{ respond shoppingCart, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        shoppingCartService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'shoppingCart.label', default: 'ShoppingCart'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'shoppingCart.label', default: 'ShoppingCart'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
