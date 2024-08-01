
package com.ericsson.schemas.vas;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="bookList" type="{http://schemas.ericsson.com/vas/}bookList"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseCode",
    "bookList"
})
@XmlRootElement(name = "ListBookResponse")
public class ListBookResponse {

    @XmlElement(required = true)
    protected String responseCode;
    @XmlElement(required = true)
    protected BookList bookList;

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponseCode(String value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the bookList property.
     * 
     * @return
     *     possible object is
     *     {@link BookList }
     *     
     */
    public BookList getBookList() {
        return bookList;
    }

    /**
     * Sets the value of the bookList property.
     * 
     * @param value
     *     allowed object is
     *     {@link BookList }
     *     
     */
    public void setBookList(BookList value) {
        this.bookList = value;
    }

}
