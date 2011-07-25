package appstore

import grails.converters.JSON
import javax.servlet.http.HttpServletRequest

class WebappController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index = {
        redirect(action: "list", params: params)
    }

    def list = {
        params.max = Math.min(params.max ? params.int('max') : 10, 100)
        def list = Webapp.list(params)

        withFormat {
            html {
                [webappInstanceList: list, webappInstanceTotal: Webapp.count()]
            }
            json {

                render(contentType: "text/json") {
                    apps = array {
                        list.each {Webapp webapp ->
                            app {
                                name = webapp.name
                                description = webapp.description
                                imageUrl = grailsApplication.config.android.serverURL.toString() + webapp.imageUrl
                                packageUrl = grailsApplication.config.android.serverURL.toString() + webapp.packageUrl
                            }
                        }
                    }

                }
            }
        }

    }

    def create = {
        def webappInstance = new Webapp()
        webappInstance.properties = params
        return [webappInstance: webappInstance]
    }

    def save = {
        def webappInstance = new Webapp(params)

        processFiles(request, webappInstance)

        if (webappInstance.save(flush: true)) {
            flash.message = message(code: 'default.created.message', args: [message(code: 'webapp.label', default: 'Webapp'), webappInstance.id])
            redirect(action: "show", id: webappInstance.id)
        }
        else {
            render(view: "create", model: [webappInstance: webappInstance])
        }
    }

    def show = {
        def webappInstance = Webapp.get(params.id)
        if (!webappInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
            redirect(action: "list")
        }
        else {
            [webappInstance: webappInstance]
        }
    }

    def edit = {
        def webappInstance = Webapp.get(params.id)
        if (!webappInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
            redirect(action: "list")
        }
        else {
            return [webappInstance: webappInstance]
        }
    }

    def update = {
        def webappInstance = Webapp.get(params.id)
        if (webappInstance) {
            if (params.version) {
                def version = params.version.toLong()
                if (webappInstance.version > version) {

                    webappInstance.errors.rejectValue("version", "default.optimistic.locking.failure", [message(code: 'webapp.label', default: 'Webapp')] as Object[], "Another user has updated this Webapp while you were editing")
                    render(view: "edit", model: [webappInstance: webappInstance])
                    return
                }
            }

            webappInstance.properties = params

            processFiles(request, webappInstance)

            if (!webappInstance.hasErrors() && webappInstance.save(flush: true)) {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'webapp.label', default: 'Webapp'), webappInstance.id])
                redirect(action: "show", id: webappInstance.id)
            }
            else {
                render(view: "edit", model: [webappInstance: webappInstance])
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
            redirect(action: "list")
        }
    }

    private def processFiles(HttpServletRequest request, Webapp webappInstance) {
        def imageFile = request.getFile('image')
        def packageFile = request.getFile('package')

        if (!imageFile.empty) {
            String location = "E:\\Projects\\appstore\\web-app\\images\\apps\\"
            def imageFileName = webappInstance.name.replaceAll(" ", "")

            def newFile = new File(location + imageFileName + '.jpg')
            imageFile.transferTo(newFile)

            webappInstance.imageUrl = "/images/apps/${imageFileName}.jpg"
        }

        if (!packageFile.empty) {
            String location = "E:\\Projects\\appstore\\web-app\\packages\\"
            def packageFileName = webappInstance.name.replaceAll(" ", "")
            def newFile = new File(location + packageFileName + '.zip')
            packageFile.transferTo(newFile)

            webappInstance.packageUrl = "/packages/${packageFileName}.zip"
        }
    }

    def delete = {
        def webappInstance = Webapp.get(params.id)
        if (webappInstance) {
            try {
                webappInstance.delete(flush: true)
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
                redirect(action: "list")
            }
            catch (org.springframework.dao.DataIntegrityViolationException e) {
                flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
                redirect(action: "show", id: params.id)
            }
        }
        else {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'webapp.label', default: 'Webapp'), params.id])
            redirect(action: "list")
        }
    }
}
