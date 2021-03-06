package facades;

import DTO.BooksDTO;
import entities.Book;
import entities.Role;
import entities.User;
import errorhandling.BookNotFoundException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import utils.EMF_Creator;

public class BookFacadeTest {
    
    private static EntityManagerFactory emf;
    private static BookFacade facade;
    private Book book1, book2, book3;

    public BookFacadeTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
        emf = EMF_Creator.createEntityManagerFactoryForTest();
        facade = BookFacade.getFacadeExample(emf);
    }
    
    @BeforeEach
    public void setUp() {
        EntityManager em = emf.createEntityManager();

        book1 = new Book(978148412, "Harry Potter and the Half Blood Price", "J.K Rowling", "Gyldendal", 2005);
        book2 = new Book(978148410, "Harry Potter and the Deathly Hallows P1", "J.K Rowling", "Gyldendal", 2006);
        book3 = new Book(978148411, "Harry Potter and the Deathly Hallows P2", "J.K Rowling", "Gyldendal", 2007);

        try {
            em.getTransaction().begin();
            em.createQuery("Delete from Book").executeUpdate();
            em.persist(book1);
            em.persist(book2);
            em.persist(book3);
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }

    @Test
    public void testGetAllBooks() {
        BooksDTO result = facade.getAllBooks();
        assertEquals(3, result.getAll().size(), "Expects three rows in the database");
    }
    
    @Test
    public void testAddBook() {
        System.out.println("TESTING SIZE BEFORE ADD METHOD ....");

        int isbn = 978148414;
        String title = "Postmand Per";
        String author = "John A. Cunliffe";
        String publisher = "Gyldendal";
        int publishYear = 2017;

        facade.addBook(isbn, title, author, publisher, publishYear);
        System.out.println("TESTING SIZE AFTER ADD METHOD");
        assertEquals(4, facade.getAllBooks().getAll().size(), "Expects four rows in the database");
    }     
    
    //Ikke fremragende måde at teste på da, der er auto increment på ID og det derfor rykkes op hele tiden deraf disabled
    @Disabled
    @Test
    public void testDeleteBook() throws BookNotFoundException {
        System.out.println("TESTING SIZE BEFORE DELETE METHOD ....");
        facade.deleteBook(9);
        System.out.println("TESTING SIZE AFTER ADD METHOD");
        assertEquals(2, facade.getAllBooks().getAll().size(), "Expects two rows in the database");
    }    
}
