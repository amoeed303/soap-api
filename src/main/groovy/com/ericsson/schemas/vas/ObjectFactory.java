
package com.ericsson.schemas.vas;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.ericsson.schemas.vas package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _BookList_QNAME = new QName("http://schemas.ericsson.com/vas/", "bookList");
    private final static QName _Book_QNAME = new QName("http://schemas.ericsson.com/vas/", "Book");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.ericsson.schemas.vas
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetBookRequest }
     * 
     */
    public GetBookRequest createGetBookRequest() {
        return new GetBookRequest();
    }

    /**
     * Create an instance of {@link SaveBookResponse }
     * 
     */
    public SaveBookResponse createSaveBookResponse() {
        return new SaveBookResponse();
    }

    /**
     * Create an instance of {@link GetBookResponse }
     * 
     */
    public GetBookResponse createGetBookResponse() {
        return new GetBookResponse();
    }

    /**
     * Create an instance of {@link Book }
     * 
     */
    public Book createBook() {
        return new Book();
    }

    /**
     * Create an instance of {@link UpdateBookRequest }
     * 
     */
    public UpdateBookRequest createUpdateBookRequest() {
        return new UpdateBookRequest();
    }

    /**
     * Create an instance of {@link UpdateBookResponse }
     * 
     */
    public UpdateBookResponse createUpdateBookResponse() {
        return new UpdateBookResponse();
    }

    /**
     * Create an instance of {@link DeleteBookRequest }
     * 
     */
    public DeleteBookRequest createDeleteBookRequest() {
        return new DeleteBookRequest();
    }

    /**
     * Create an instance of {@link ListBookRequest }
     * 
     */
    public ListBookRequest createListBookRequest() {
        return new ListBookRequest();
    }

    /**
     * Create an instance of {@link SaveBookRequest }
     * 
     */
    public SaveBookRequest createSaveBookRequest() {
        return new SaveBookRequest();
    }

    /**
     * Create an instance of {@link ListBookResponse }
     * 
     */
    public ListBookResponse createListBookResponse() {
        return new ListBookResponse();
    }

    /**
     * Create an instance of {@link BookList }
     * 
     */
    public BookList createBookList() {
        return new BookList();
    }

    /**
     * Create an instance of {@link DeleteBookResponse }
     * 
     */
    public DeleteBookResponse createDeleteBookResponse() {
        return new DeleteBookResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link BookList }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.ericsson.com/vas/", name = "bookList")
    public JAXBElement<BookList> createBookList(BookList value) {
        return new JAXBElement<BookList>(_BookList_QNAME, BookList.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Book }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://schemas.ericsson.com/vas/", name = "Book")
    public JAXBElement<Book> createBook(Book value) {
        return new JAXBElement<Book>(_Book_QNAME, Book.class, null, value);
    }

}
