package com.grails

class UserController {

    def scaffold = true

    def index() { }

    def search() { }

    def results() {
        def users = User.findAllByUserIdLike("%${params.userId}%")
        return [ users: users, term: params.userId ]
    }

    def register() {
        def user = new User(params)

        if(user.validate()) {
            user.save()
            flash.message = "User successfully created"
            redirect(uri: "/")
        } else {
            flash.message = "Error registering user"
            return [ user: user ]
        }
    }
}
