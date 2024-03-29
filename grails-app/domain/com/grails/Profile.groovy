package com.grails

class Profile {

    static belongsTo = User

    byte[] photo

    String fullName
    String bio
    String email
    String homepage
    String timezone
    String country
    String jabberAddress

    static constraints = {
        fullName(nullable: true)
        bio(nullable: true, maxSize: 1000)
        homepage(url: true, nullable: true)
        email(email: true, nullable: true)
        photo(nullable: true)
        country(nullable: true)
        timezone(nullable: true)
        jabberAddress(email: true, nullable: true)
    }
}
