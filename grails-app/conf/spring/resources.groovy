package spring

import soap.crud.BookService
//import UsernameTokenCallBackHandler
import soap.crud.WSSecurityInterceptor

// Place your Spring DSL code here

beans = {

    bookService(BookService)
    //usernameTokenCallBackHandler(UsernameTokenCallBackHandler)
    wsSecurityInterceptor(WSSecurityInterceptor)
}