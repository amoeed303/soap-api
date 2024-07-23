package soap.crud

class UrlMappings {

    static mappings = {

        "/"(controller: 'book', action: 'index')
        "/showImage/$id"(controller: 'book', action: 'showImage')
        "/saveBook"(controller: 'book', action: 'saveBook')
        "/updateBook"(controller: 'book', action: 'updateBook')
        "/deleteBook"(controller: 'book', action: 'deleteBook')
        "500"(view:'/error')
        "404"(view:'/notFound')


    }
}
