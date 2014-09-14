package com.grails

class PostController {

    def scaffold = true

    def index() {}

    def timeline() {
        def user = User.findByUserId(params.id)

        [ user: user ]
    }

    def addPost() {
        def user = User.findByUserId(params.id)

        if (user) {
            def post = new Post(params)
            user.addToPosts(post)
            if (user.save()) {
                flash.message = "Succeffully created Post"
            } else {
                user.discard()
                flash.message = "Invalid or empty post"
            }
        } else {
            flash.message = "Invalid user id"
        }
        redirect(action: 'timeline', id: params.id)
    }
}
