package com.grails

class LameSecurityFilters {

    def filters = {
        secureActions(controller:'post', action:'(addPost|deletePost)') {
            before = {
                if (params.logonId) {
                    session.user = Users.findByUserId(params.logonId)
                }

                if (!session.user) {
                    redirect(controller: 'login', action: 'form')
                    return false
                }
            }
            after = { Map model ->

            }
            afterView = {
               log.debug("Finished running ${controllerName} ${actionName}")
            }
        }
    }
}
