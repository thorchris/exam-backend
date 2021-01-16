package rest;

import DTO.BookDTO;
import DTO.BooksDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.Book;
import entities.User;
import errorhandling.BookNotFoundException;
import facades.BookFacade;
import facades.UserFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import utils.EMF_Creator;

@Path("books")
public class BookResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final BookFacade bf = BookFacade.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String allBooks() {
        BooksDTO booksDTO = bf.getAllBooks();
        return GSON.toJson(booksDTO);
    }
    
    @POST
    @Produces({MediaType.APPLICATION_JSON})
    @Consumes({MediaType.APPLICATION_JSON})
    @Path("add")
    public String addBook(String book) {
        Book b = GSON.fromJson(book, Book.class);
        bf.addBook(b.getIsbn(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getPublishYear());
        return "Book added";
    }
    
    @DELETE
    @Path("/{isbn}")
    @Produces({MediaType.APPLICATION_JSON})
    public String deleteBook(@PathParam("isbn") int isbn) throws BookNotFoundException{
        BookDTO pd = bf.deleteBook(isbn);
        return "Deleted: " + GSON.toJson(pd);
    }
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        bf.populateDB();
        return "{\"msg\":\"3 rows added\"}";
    }
}
