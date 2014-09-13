package com.grails


class UserIntegrationTest extends GroovyTestCase {

    void testUserPersistence() {
        def user = new User(userId: 'joe', password: 'password', homepage: "http://home.page")
        assertNotNull(user.save())
        assertNotNull(user.id)

        def foundUser = User.get(user.id)
        assertEquals('joe', foundUser.userId)
    }
}
