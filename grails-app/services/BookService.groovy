import com.ericsson.schemas.vas.BookServicePortType
import com.ericsson.schemas.vas.DeleteBookRequest
import com.ericsson.schemas.vas.DeleteBookResponse
import com.ericsson.schemas.vas.GetBookRequest
import com.ericsson.schemas.vas.GetBookResponse
import com.ericsson.schemas.vas.SaveBookRequest
import com.ericsson.schemas.vas.SaveBookResponse
import com.ericsson.schemas.vas.UpdateBookRequest
import com.ericsson.schemas.vas.UpdateBookResponse
import grails.gorm.transactions.Transactional
import org.grails.cxf.utils.GrailsCxfEndpoint
import org.springframework.web.multipart.MultipartFile
import soap.crud.Book

import javax.jws.WebParam


@Transactional
@GrailsCxfEndpoint()
class BookService implements BookServicePortType {
    def listBooks() {
        return Book.list()
    }

    /*
    def getBook(Long id) {
        def book = Book.get(id)
        if (!book) {
            throw new RuntimeException("Book not found")
        }
        return book
    }
//
//    def listBooks() {
//        return Book.list()
//    }
@Override
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
*/

    @Override
    SaveBookResponse saveBook(@WebParam(name = "SaveBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "SaveBookRequest") SaveBookRequest saveBookRequest) {
        SaveBookResponse response = new SaveBookResponse()
        Book book = new Book(title: saveBookRequest.title, author: saveBookRequest.author, isbn: saveBookRequest.isbn)
        if (book.save(flush: true)) {
            response.result = true
            return response
        } else {
            response.result = false
            return response
        }
    }

    @Override
    GetBookResponse getBook(@WebParam(name = "GetBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "GetBookRequest") GetBookRequest getBookRequest) {
        GetBookResponse response = new GetBookResponse()
        Book book = Book.get(getBookRequest.id)
        response.title = book.title
        response.author = book.author
        response.isbn = book.isbn
        return response
    }

    @Override
    UpdateBookResponse updateBook(@WebParam(name = "UpdateBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "UpdateBookRequest") UpdateBookRequest updateBookRequest) {
        UpdateBookResponse response = new UpdateBookResponse()
        Book book = Book.get(updateBookRequest.id)
        if (!book) {
            return response.result = false
        }
        if (updateBookRequest.title && updateBookRequest.author && updateBookRequest.isbn) {
            book.title = updateBookRequest.title
            book.author = updateBookRequest.author
            book.isbn = updateBookRequest.isbn
            if (book.save(flush: true)) {
                response.result = true
                return response
            } else {
                response.result = false
                return response
            }
        }
    }

    @Override
    DeleteBookResponse deleteBook(@WebParam(name = "DeleteBookRequest", targetNamespace = "http://schemas.ericsson.com/vas/", partName = "DeleteBookRequest") DeleteBookRequest deleteBookRequest) {
        DeleteBookResponse response = new DeleteBookResponse()
        Book book = Book.get(deleteBookRequest.id)
        if (book) {
            book.delete(flush: true)
            response.result = true
            return response
        } else {
            response.result = false
            return response
        }
    }
}
