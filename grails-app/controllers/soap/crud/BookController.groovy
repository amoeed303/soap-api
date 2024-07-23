package soap.crud

import org.springframework.web.multipart.MultipartFile
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.MultipartRequest

import java.text.SimpleDateFormat

class BookController {
    BookService bookService

    def index() {
        def books = bookService.listBooks()
        println("books: $books")
        render(view: "index", model: [books: books])
    }

    def saveBook() {

        def title = params.title
        def author = params.author
        def isbn = params.isbn
        MultipartFile image = null
        if (request instanceof MultipartHttpServletRequest) {
            image = request.getFile('image')
        }
        println("details: $title, $author, $isbn")
        if (title && author && isbn) {
            def result = bookService.saveBook(title, author, isbn, image)
            if (result.success) {
                flash.message = "Book saved successfully"
                redirect(action: "index")
            } else {
                flash.message = "Error saving book"
                redirect(action: 'saveBook')
            }
        } else {
            render(view: "addBook")
        }
    }


    def updateBook(Long id) {
def book = bookService.getBook(id)
        def title = params.title
        def author = params.author
        def isbn = params.isbn
        MultipartFile image = null
        if (request instanceof MultipartHttpServletRequest) {
            image = request.getFile('image')
        }
        if (id&& title && author && isbn) {
            def result = bookService.updateBook(id, title, author, isbn, image)
            if (result.success) {
                flash.message = "Book updated successfully"
                redirect(action: "index")
            } else {
                flash.message = "Error updating book"
                redirect(action: 'updateBook', id: id)
            }
        } else {
            println("Unable to update book with id: $id")
            render(view: "updateBook", model: [book: book])
        }

    }

    def deleteBook(Long id) {
        bookService.deleteBook(id)
        redirect(action: "index")
    }

    def showImage(Long id) {
        def book = bookService.getBook(id)
        if (book && book.imagePath) {
            File imageFile = new File(book.imagePath)
            if (imageFile.exists()) {
                response.contentType = 'image/png'
                response.outputStream << imageFile.bytes
                response.outputStream.flush()
            } else {
                println("File does not exist at path: ${normalizedPath}")  // Debugging line
                response.status = 404
            }
        } else {
            println("Book or image path not found for book id: ${id}")  // Debugging line
            response.status = 404
        }
    }
}


