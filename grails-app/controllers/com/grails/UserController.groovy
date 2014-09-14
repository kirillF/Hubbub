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

    def register2 = { UserRegistrationCommand urc ->
        if(urc.hasErrors()) {
            return [ user: urc ]
        } else {
            def user =  new User(urc.properties)
            user.profile = new Profile(urc.properties)

            if (user.save()) {
                flash.message = "User successfully created"
                redirect(uri: "/")
            } else {
                return [ user: urc ]
            }
        }
    }
}

class UserRegistrationCommand {
    String userId
    String password
    String passwordRepeat

    byte[] photo
    String fullName
    String bio
    String homepage
    String email
    String timezone
    String country
    String jabberAddress

    static constraints = {
        userId(size: 3..20)

        password(size: 6..8, blank: false,
            validator: { passwrd, urc ->
                return passwd != urc.userId
            })
        passwordRepeat(nullable: false,
            validator: { passwd, urc ->
                return passwd == urc.password
            })
        fullName(nullable: true)
        bio(nullable: true, maxSize: 1000)
        homepage(url: true, nullable: true)
        email(email: true, nullable: true)
        photo(nullable: true)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(nullable: true)
   }
}
