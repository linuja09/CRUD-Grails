package leadsquared

class UrlMappings {

    static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/"(uri:"/lead/home")
        "500"(view:'/error')
        "404"(view:'/notFound')
    }
}
