package server

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class AccessoryController {

    AccessoryService accessoryService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond accessoryService.list(params), model:[accessoryCount: accessoryService.count()]
    }

    def show(Long id) {
        respond accessoryService.get(id)
    }

    def create() {
        respond new Accessory(params)
    }

    def save(Accessory accessory) {
        if (accessory == null) {
            notFound()
            return
        }

        try {
            accessoryService.save(accessory)
        } catch (ValidationException e) {
            respond accessory.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'accessory.label', default: 'Accessory'), accessory.id])
                redirect accessory
            }
            '*' { respond accessory, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond accessoryService.get(id)
    }

    def update(Accessory accessory) {
        if (accessory == null) {
            notFound()
            return
        }

        try {
            accessoryService.save(accessory)
        } catch (ValidationException e) {
            respond accessory.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'accessory.label', default: 'Accessory'), accessory.id])
                redirect accessory
            }
            '*'{ respond accessory, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        accessoryService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'accessory.label', default: 'Accessory'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'accessory.label', default: 'Accessory'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
