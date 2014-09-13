package com.grails


class UserIntegrationTest extends GroovyTestCase {

    void testUserPersistence() {
        def user = new User(userId: 'joe', password: 'password')
        assertNotNull(user.save())
        assertNotNull(user.id)

        def foundUser = User.get(user.id)
        assertEquals('joe', foundUser.userId)
    }

    void testSaveAndUpdate() {
        def user = new User(userId: 'joe', password: 'password')
        assertNotNull(user.save())

        def foundUser = User.get(user.id)
        foundUser.password = 'sesame'
        foundUser.save()

        def editedUser =  User.get(user.id)
        assertEquals('sesame', editedUser.password)
    }

    void testDeleteUser() {
        def user = new User(userId: 'joe', password: 'password')
        assertNotNull(user.save())

        def foundUser = User.get(user.id)
        foundUser.delete()

        assertFalse(User.exists(foundUser.id))
    }

    void testEvilSave() {
        def user = new User(userId: 'chuck_norris', password: 'tiny')
        assertFalse(user.validate())
        assertTrue(user.hasErrors())

        def errors = user.errors

        assertEquals("size.toosmall", errors.getFieldError("password").code)
        assertEquals("tiny", errors.getFieldError("password").rejectedValue)

        assertNull(errors.getFieldError("userId"))
    }

    void testEvilSaveCorrected() {
        def user = new User(userId: 'chuck_norris', password: 'tiny')
        assertFalse(user.validate())
        assertTrue(user.hasErrors())

        assertNull(user.save())

        user.password = "fistfist"
        assertTrue(user.validate())
        assertFalse(user.hasErrors())
        assertNotNull(user.save())
    }

    void testPasswordEqualsUserId() {
        def user = new User(userId: 'chuck_no', password: 'chuck_no')
        assertFalse(user.validate())
        assertTrue(user.hasErrors())

        assertNull(user.save())

        def errors = user.errors

        assertEquals('validator.invalid', errors.getFieldError("password").code)
    }
}
