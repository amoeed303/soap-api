package soap.crud

import grails.gorm.transactions.Transactional
import org.springframework.web.multipart.MultipartFile
import grails.core.GrailsApplication
import java.util.UUID

@Transactional
class BookService {
    def getBook(Long id) {
        return Book.get(id)
    }

    def listBooks() {
        return Book.list()
    }

    def saveBook(String title, String author, String isbn, MultipartFile image) {
        String imagePath = null
        if (image && !image.empty) {
            imagePath = saveImageToFileSystem(image)
            println("imagePath: $imagePath")
        }
        if (imagePath == null) {
            return [success: false, errors: "Image not uploaded"]
        }
        def book = new Book(title: title, author: author, isbn: isbn, imagePath: imagePath)
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

    def updateBook(Long id, String title, String author, String isbn,MultipartFile image) {
        def book = getBook(id)
        book.title = title
        book.author = author
        book.isbn = isbn
        if (image && !image.empty) {
            book.imagePath = saveImageToFileSystem(image)
        }
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


}
