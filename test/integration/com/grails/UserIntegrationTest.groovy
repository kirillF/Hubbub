package com.grails


class UserIntegrationTest extends GroovyTestCase {

    void testUserPersistence() {
        def user = new User(userId: 'joe', password: 'password', homepage: "http://home.page")
        assertNotNull(user.save())
        assertNotNull(user.id)

        def foundUser = User.get(user.id)
        assertEquals('joe', foundUser.userId)
    }

    void testSaveAndUpdate() {
        def user = new User(userId: 'joe', password: 'password', homepage: 'http://home.page')
        assertNotNull(user.save())

        def foundUser = User.get(user.id)
        foundUser.password = 'sesame'
        foundUser.save()

        def editedUser =  User.get(user.id)
        assertEquals('sesame', editedUser.password)
    }

    void testDeleteUser() {
        def user = new User(userId: 'joe', password: 'password', homepage: 'http://home.page')
        assertNotNull(user.save())

        def foundUser = User.get(user.id)
        foundUser.delete()

        assertFalse(User.exists(foundUser.id))
    }

    void testEvilSave() {
        def user = new User(userId: 'chuck_norris', password: 'tiny', homepage: 'not-a-url')
        assertFalse(user.validate())
        assertTrue(user.hasErrors())

        def errors = user.errors

        assertEquals("size.toosmall", errors.getFieldError("password").code)
        assertEquals("tiny", errors.getFieldError("password").rejectedValue)

        assertEquals("url.invalid", errors.getFieldError("url").code)
        assertEquals("not-a-url", errors.getFieldError("url").rejectedValue)

        assertNull(errors.getFieldError("userId"))
    }

    void testEvilSaveCorrected() {
        def user = new User(userId: 'chuck_norris', password: 'tiny', homepage: 'not-a-url')
        assertFalse(user.validate())
        assertTrue(user.hasErrors())

        assertNull(user.save())

        user.password = "fistfist"
        user.homepage = "http://www.chucknorrisfacts.com"
        assertTrue(user.validate())
        assertFalse(user.hasErrors())
        assertNotNull(user.save())
    }
}
