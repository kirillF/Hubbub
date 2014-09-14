import com.grails.Profile
import com.grails.User
import grails.test.GrailsUnitTestCase

class QueryIntegration extends GrailsUnitTestCase {

    void testBasicDynamicFinders() {
        new User(userId: 'glen', password: 'secret', profile:
            new Profile(email: 'email@home.page')).save()
        new User(userId: 'peter', password: 'sesame', profile:
            new Profile(homepage: 'http://home.page')).save()

        def user = User.findByPassword('sesame')
        assertEquals('peter', user.userId)

        user = User.findByUserIdAndPassword('glen', 'password')
        assertEquals('glen', user.userId)

        def now = new Date()

        def users = User.findAllByDateCreatedBetween(now - 1, now)
        assertEquals(2, users.size())

        def profiles = Profile.findAllByEmailIsNotNull()
        assertEquals(1, profiles.size())
    }
}
