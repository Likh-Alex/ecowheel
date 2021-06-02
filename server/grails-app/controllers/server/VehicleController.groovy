package server

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class VehicleController {

    VehicleService vehicleService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond vehicleService.list(params), model:[vehicleCount: vehicleService.count()]
    }

    def show(Long id) {
        respond vehicleService.get(id)
    }

    def create() {
        respond new Vehicle(params)
    }

    def save(Vehicle vehicle) {
        if (vehicle == null) {
            notFound()
            return
        }

        try {
            vehicleService.save(vehicle)
        } catch (ValidationException e) {
            respond vehicle.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect vehicle
            }
            '*' { respond vehicle, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond vehicleService.get(id)
    }

    def update(Vehicle vehicle) {
        if (vehicle == null) {
            notFound()
            return
        }

        try {
            vehicleService.save(vehicle)
        } catch (ValidationException e) {
            respond vehicle.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), vehicle.id])
                redirect vehicle
            }
            '*'{ respond vehicle, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        vehicleService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'vehicle.label', default: 'Vehicle'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
