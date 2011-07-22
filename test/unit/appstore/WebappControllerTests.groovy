package appstore



import org.junit.*
import grails.test.mixin.*


@TestFor(WebappController)
@Mock(Webapp)
class WebappControllerTests {


    @Test
    void testIndex() {
        controller.index()
        assert "/webapp/list" == response.redirectedUrl
    }

    @Test
    void testList() {

        def model = controller.list()

        assert model.webappInstanceList.size() == 0
        assert model.webappInstanceTotal == 0

    }

    @Test
    void testCreate() {
        def model = controller.create()

        assert model.webappInstance != null


    }

    @Test
    void testSave() {
        controller.save()

        assert model.webappInstance != null
        assert view == '/webapp/create'

        // TODO: Populate valid properties

        controller.save()

        assert response.redirectedUrl == '/webapp/show/1'
        assert controller.flash.message != null
        assert Webapp.count() == 1
    }


    @Test
    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/webapp/list'


        def webapp = new Webapp()

        // TODO: populate domain properties

        assert webapp.save() != null

        params.id = webapp.id

        def model = controller.show()

        assert model.webappInstance == webapp
    }

    @Test
    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/webapp/list'


        def webapp = new Webapp()

        // TODO: populate valid domain properties

        assert webapp.save() != null

        params.id = webapp.id

        def model = controller.edit()

        assert model.webappInstance == webapp
    }

    @Test
    void testUpdate() {

        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/webapp/list'

        response.reset()


        def webapp = new Webapp()

        // TODO: populate valid domain properties

        assert webapp.save() != null

        // test invalid parameters in update
        params.id = webapp.id

        controller.update()

        assert view == "/webapp/edit"
        assert model.webappInstance != null

        webapp.clearErrors()

        // TODO: populate valid domain form parameter
        controller.update()

        assert response.redirectedUrl == "/webapp/show/$webapp.id"
        assert flash.message != null
    }

    @Test
    void testDelete() {
        controller.delete()

        assert flash.message != null
        assert response.redirectedUrl == '/webapp/list'

        response.reset()

        def webapp = new Webapp()

        // TODO: populate valid domain properties
        assert webapp.save() != null
        assert Webapp.count() == 1

        params.id = webapp.id

        controller.delete()

        assert Webapp.count() == 0
        assert Webapp.get(webapp.id) == null
        assert response.redirectedUrl == '/webapp/list'


    }


}