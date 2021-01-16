package rest;

import DTO.BooksDTO;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import facades.BookFacade;
import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
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
    
    @GET
    @Path("/populate")
    @Produces({MediaType.APPLICATION_JSON})
    public String populate() {
        bf.populateDB();
        return "{\"msg\":\"3 rows added\"}";
    }

}
