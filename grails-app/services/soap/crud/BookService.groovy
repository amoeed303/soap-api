package soap.crud

import com.ericsson.schemas.vas.BookServicePortType
import com.ericsson.schemas.vas.DeleteBookRequest
import com.ericsson.schemas.vas.DeleteBookResponse
import com.ericsson.schemas.vas.GetBookRequest
import com.ericsson.schemas.vas.GetBookResponse
import com.ericsson.schemas.vas.ListBookRequest
import com.ericsson.schemas.vas.ListBookResponse
import com.ericsson.schemas.vas.SaveBookRequest
import com.ericsson.schemas.vas.SaveBookResponse
import com.ericsson.schemas.vas.UpdateBookRequest
import com.ericsson.schemas.vas.UpdateBookResponse
import grails.gorm.transactions.Transactional
import org.apache.cxf.interceptor.InInterceptors
import org.apache.cxf.jaxws.JaxWsServerFactoryBean
import org.grails.cxf.utils.GrailsCxfEndpoint
import org.springframework.web.multipart.MultipartFile
import javax.jws.WebParam

@InInterceptors(classes = WSSecurityInterceptor.class)
@GrailsCxfEndpoint()
@Transactional
class BookService implements BookServicePortType {

    BookService() {
        JaxWsServerFactoryBean factory = new JaxWsServerFactoryBean()
        factory.serviceBean = this
        factory.address = "/book"
        factory.serviceClass = BookServicePortType
        factory.getInInterceptors().add(new WSSecurityInterceptor())
        factory.create()
    }

    def listBooks() {
        return Book.list()
    }

    def getBook(Long id) {
        def book = Book.get(id)
        if (!book) {
            throw new RuntimeException("Book not found")
        }
        return book
    }

    def saveBook(String title, String author, String isbn)//, MultiPartFile image
    {
//        String imagePath = null
//        if (image && !image.empty) {
//            imagePath = saveImageToFileSystem(image)
//            println("imagePath: $imagePath")
//        }
//        if (imagePath == null) {
//            return [success: false, errors: "Image not uploaded"]
//        }
        def book = new Book(title: title, author: author, isbn: isbn)//, imagePath: imagePath
        def result = book.save(flush: true)
        if (result) {
            return [success: true, book: book]
        } else {
            return [success: false, errors: book.errors]
        }
    }
    //utility method to save image to file system
    //save absolute path of image to database
    private String saveImageToFileSystem(MultipartFile image) {
        String uploadDir = 'C:/Users/amoee/Desktop/soap_crud/uploads'
        String fileName = "${UUID.randomUUID().toString()}_${image.originalFilename}"
        println("fileName: $fileName")
        File uploadFolder = new File(uploadDir)
        println("upload Folder: $uploadFolder")

        if (!uploadFolder.exists()) {
            uploadFolder.mkdirs()
        }

        File uploadedFile = new File(uploadFolder, fileName)
        println("uploaded File: $uploadedFile")
        try {
            image.transferTo(uploadedFile)
            return uploadedFile.path

        } catch (Exception e) {
            print("hi i am stuck in this exception")
            e.printStackTrace()
            return null

        }

    }

    def updateBook(Long id, String title, String author, String isbn)//, MultipartFile image
    {
        def book = getBook(id)
        book.title = title
        book.author = author
        book.isbn = isbn
//        if (image && !image.empty) {
//            book.imagePath = saveImageToFileSystem(image)
//        }
        def result = book.save(flush: true)
        if (result) {
            return [success: true, book: book]
        } else {
            return [success: false, errors: book.errors]
        }

    }

    def deleteBook(Long id) {
        def book = Book.get(id)
        book.delete(flush: true)
    }

    @Override
    ListBookResponse listBooks(@WebParam(name = "ListBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "ListBookRequest") ListBookRequest listBookRequest) {
        ListBookResponse response = new ListBookResponse()
        def books = Book.list()
        if (books.size() == 0) {
            response.responseCode = 404
            return response
        } else {
            response.bookList = new com.ericsson.schemas.vas.BookList()
            books.each { bookElement ->
                def book = new com.ericsson.schemas.vas.Book()
                book.title = bookElement.title
                book.author = bookElement.author
                book.isbn = bookElement.isbn

                response.bookList.book.add(book)
            }
            response.responseCode = 200
            return response
        }
    }

    @Override
    SaveBookResponse saveBook(@WebParam(name = "SaveBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "SaveBookRequest") SaveBookRequest saveBookRequest) {
        SaveBookResponse response = new SaveBookResponse()
        if (!saveBookRequest.title || !saveBookRequest.author || !saveBookRequest.isbn) {
            response.result = "Insufficient parameters provided"
            response.responseCode = 400
            return response
        } else if (Book.findByTitle(saveBookRequest.title)) {
            response.result = "Book with title already exists"
            response.responseCode = 409
            return response
        } else if (Book.findByIsbn(saveBookRequest.isbn)) {
            response.result = "Book with ISBN already exists"
            response.responseCode = 409
            return response
        } else {
            Book book = new Book(title: saveBookRequest.title, author: saveBookRequest.author, isbn: saveBookRequest.isbn)
            if (book.save(flush: true)) {
                response.result = "Book saved successfully"
                response.responseCode = 200
                return response
            } else {
                response.result = "Book save failed"
                response.responseCode = 500
                return response
            }
        }
    }

    @Override
    GetBookResponse getBook(@WebParam(name = "GetBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "GetBookRequest") GetBookRequest getBookRequest) {
        GetBookResponse response = new GetBookResponse()
        if (!getBookRequest.id) {
            response.responseCode = 400
            response.result = "No ID provided"
            return response
        }
        Book book = Book.get(getBookRequest.id)
        if (!book) {
            response.responseCode = "404"
            response.result = "Book not found"
            return response
        }
        def responseBook = new com.ericsson.schemas.vas.Book()
        responseBook.title = book.title
        responseBook.author = book.author
        responseBook.isbn = book.isbn
        response.book = responseBook
        response.responseCode = 200
        return response
    }

    @Override
    UpdateBookResponse updateBook(@WebParam(name = "UpdateBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "UpdateBookRequest") UpdateBookRequest updateBookRequest) {
        UpdateBookResponse response = new UpdateBookResponse()
        if (!updateBookRequest.id) {
            response.result = "No ID provided"
            response.responseCode = 400
            return response
        }
        Book book = Book.get(updateBookRequest.id)

        if (!book) {
            response.result = "Book not found"
            response.responseCode = 404
            return response
        } else if (updateBookRequest.title && updateBookRequest.author && updateBookRequest.isbn) {
            book.title = updateBookRequest.title
            book.author = updateBookRequest.author
            book.isbn = updateBookRequest.isbn
            if (Book.findByTitle(updateBookRequest.title)) {
                response.result = "Book with title already exists Cannot Update"
                response.responseCode = 409
                return response
            } else if (Book.findByIsbn(updateBookRequest.isbn)) {
                response.result = "Book with ISBN already exists Cannot Update"
                response.responseCode = 409
                return response
            } else {
                if (book.save(flush: true)) {
                    response.result = "Book updated successfully"
                    response.responseCode = 200
                    return response
                } else {
                    response.result = "Book update failed"
                    response.responseCode = 500
                    return response
                }
            }
        } else {
            response.result = "Insufficient parameters provided"
            response.responseCode = 400
            return response
        }
    }

    @Override
    DeleteBookResponse deleteBook(@WebParam(name = "DeleteBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "DeleteBookRequest") DeleteBookRequest deleteBookRequest) {
        DeleteBookResponse response = new DeleteBookResponse()
        if (!deleteBookRequest.id) {
            response.result = "No ID provided"
            response.responseCode = "400"
            return response
        }
        Book book = Book.get(deleteBookRequest.id)
        if (book) {
            book.delete(flush: true)
            response.result = "Book deleted Successfully"
            response.responseCode = 200
            return response
        } else {
            response.result = "Book not found operation failed"
            response.responseCode = 404
            return response
        }
    }
}
