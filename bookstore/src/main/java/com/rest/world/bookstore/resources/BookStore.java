package com.rest.world.bookstore.resources;


import com.rest.world.bookstore.annotations.RestService;
import com.rest.world.bookstore.models.Book;
import com.rest.world.bookstore.services.BookService;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.URI;
import java.util.Collection;

import static javax.ws.rs.core.Response.created;
import static javax.ws.rs.core.Response.ok;

@RestService
@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookStore {

    private final BookService bookService;
    public BookStore(BookService bookService){
        this.bookService = bookService;
    }

    @GET
    @Produces("application/json")
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GET
    @Produces("application/json")
    @Path("/{oid}")
    public Book getBook(@PathParam("oid") String oid) {
        return bookService.getBook(oid);
    }

    @POST
    public Response addBook(Book book) {
        bookService.addBook(book);
        return created(URI.create("/" + book.getOid())).build();
    }

    @PUT
    @Path("/{oid}")
    public Response updateBook(@PathParam("oid") String oid, Book book) {
        bookService.updateBook(oid, book);
        return Response.noContent().build();
    }

    @DELETE
    @Path("/{oid}")
    public Response deleteBook(@PathParam("oid") String oid) {
        bookService.deleteBook(oid);
        return ok().build();
    }

}
