import appstore.Webapp

class BootStrap {

    def grailsApplication

    def init = { servletContext ->

        def packageFileUrl = "/packages/angrybirds.zip"
        def imageUrl = "/images/apps/angry-bird.jpg"
        new Webapp(name:"Angry Birds", description: "Some Hot Game.", imageUrl: imageUrl, packageUrl: packageFileUrl).save()

    }
    def destroy = {
    }
}
