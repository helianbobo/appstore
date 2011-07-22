import appstore.Webapp

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        def packageFileUrl = grailsApplication.config.grails.serverURL.toString() + "/packages/angrybirds.zip"
        new Webapp(name:"Angry Birds", description: "Some Hot Game.", imageUrl: "/images/apps/angry-bird.jpg", packageUrl: packageFileUrl).save()

    }
    def destroy = {
    }
}
