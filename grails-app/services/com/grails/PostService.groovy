package com.grails

import grails.transaction.Transactional

class PostException extends RuntimeException {
    String message
    Post post
}

@Transactional
class PostService {

    boolean transactional = true

    def createPost(String userId, String content) {
        def user = User.findByUserId(userId)
        if (user) {
            def post = new Post(content: content)
            user.addToPosts(post)
            if (user.save()) {
                return post
            } else {
                throw new PostException(message: "Invalid or empty post",
                post: post)
            }
        }
        throw new PostException(message: "Invalid user id")
    }
}