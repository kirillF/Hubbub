package com.grails

class PostIntegrationTest extends GroovyTestCase {

    void testFirstPost() {
        def user = new User(userId: 'joe', password: 'secret').save()
        def post1 = new Post(content: 'First post')
        user.addToPosts(post1)
        def post2 = new Post(content: 'Second post')
        user.addToPosts(post2)
        def post3 = new Post(content: 'Third post')
        user.addToPosts(post3)
        assertEquals(3, User.get(user.id).getPosts().size())
    }

    void testAccessingPosts() {
        def user = new User(userId: 'joe', password: 'secret').save()
        user.addToPosts(new Post(content: "First"))
        user.addToPosts(new Post(content: "Second"))
        user.addToPosts(new Post(content: "Third"))

        def foundUser = User.get(user.id)
        def postNames = foundUser.getPosts().collect { it.content }

        assertEquals(['First', 'Second', 'Third'], postNames.sort())
    }
}
