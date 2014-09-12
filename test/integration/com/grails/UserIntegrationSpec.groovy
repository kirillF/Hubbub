package com.grails

import com.grails.User
import grails.test.GrailsUnitTestCase

import static org.junit.Assert.*;

class UserIntegrationSpec extends GrailsUnitTestCase {


    void testSave() {
        def user = new User(userId: 'joe', password: 'password', homepage: "http://home.page")
        assertNotNull(user.save())
        assertNotNull(user.id)

        def foundUser = User.get(user.id)
        assertEquals('joe', foundUser.userId)
    }
}
