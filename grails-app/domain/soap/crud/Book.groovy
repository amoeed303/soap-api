package soap.crud

class Book {
    String title
    String author
    String isbn
    //String imagePath

    static constraints = {
        title blank: false,unique: true
        author blank: false
        isbn blank: false,unique: true
       // imagePath nullable: true
    }
    static mapping = {
        version false
    }
}
