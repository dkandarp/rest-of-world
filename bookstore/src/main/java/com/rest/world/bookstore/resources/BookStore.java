package com.rest.world.bookstore.resources;


import com.rest.world.bookstore.models.Book;
import com.rest.world.bookstore.services.BookService;
import com.rest.world.commons.annotations.RestService;
import io.micrometer.core.annotation.Timed;

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
@Timed
public class BookStore {

    private final BookService bookService;

    public BookStore(BookService bookService) {
        this.bookService = bookService;
    }

    @GET
    @Timed
    public Collection<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @Path("/{oid}")
    @GET
    @Produces("application/json")
    @Timed
    public Book getBook(@PathParam("oid") String oid) {
        return bookService.getBook(oid);
    }

    @POST
    @Timed
    public Response addBook(Book book) {
        bookService.addBook(book);
        return created(URI.create("/" + book.getOid())).build();
    }

    @Path("/{oid}")
    @PUT
    @Timed
    public Response updateBook(@PathParam("oid") String oid, Book book) {
        bookService.updateBook(oid, book);
        return Response.noContent().build();
    }

    @Path("/{oid}")
    @DELETE
    @Timed
    public Response deleteBook(@PathParam("oid") String oid) {
        bookService.deleteBook(oid);
        return ok().build();
    }

}
