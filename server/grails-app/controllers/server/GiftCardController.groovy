package server

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class GiftCardController {

    GiftCardService giftCardService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond giftCardService.list(params), model:[giftCardCount: giftCardService.count()]
    }

    def show(Long id) {
        respond giftCardService.get(id)
    }

    def create() {
        respond new GiftCard(params)
    }

    def save(GiftCard giftCard) {
        if (giftCard == null) {
            notFound()
            return
        }

        try {
            giftCardService.save(giftCard)
        } catch (ValidationException e) {
            respond giftCard.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'giftCard.label', default: 'GiftCard'), giftCard.id])
                redirect giftCard
            }
            '*' { respond giftCard, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond giftCardService.get(id)
    }

    def update(GiftCard giftCard) {
        if (giftCard == null) {
            notFound()
            return
        }

        try {
            giftCardService.save(giftCard)
        } catch (ValidationException e) {
            respond giftCard.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'giftCard.label', default: 'GiftCard'), giftCard.id])
                redirect giftCard
            }
            '*'{ respond giftCard, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        giftCardService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'giftCard.label', default: 'GiftCard'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'giftCard.label', default: 'GiftCard'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
