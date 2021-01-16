package facades;

import DTO.BookDTO;
import DTO.BooksDTO;
import entities.Book;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

public class BookFacade {

    private static BookFacade instance;
    private static EntityManagerFactory emf;

    public BookFacade() {
    }

    public static BookFacade getFacadeExample(EntityManagerFactory _emf) {
        if (instance == null) {
            emf = _emf;
            instance = new BookFacade();
        }
        return instance;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    
    public BooksDTO getAllBooks() {
        EntityManager em = getEntityManager();
        try {
            return new BooksDTO(em.createNamedQuery("Book.getAllRows").getResultList());
        } finally {
            em.close();
        }
    }
    

    
       public BookDTO addBook(int isbn, String title, String author, String publisher, int publishYear) {
        EntityManager em = emf.createEntityManager();

        Book book = new Book(isbn, title, author, publisher, publishYear);

        try {
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
            return new BookDTO(book);
        } finally {
            em.close();
        }

    }

    public void populateDB(){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(new Book(978148412, "Harry Potter and the Half Blood Price", "J.K Rowling", "Gyldendal", 2005));
            em.persist(new Book(978148410, "Harry Potter and the Deathly Hallows P1", "J.K Rowling", "Gyldendal", 2006));
            em.persist(new Book(978148411, "Harry Potter and the Deathly Hallows P2", "J.K Rowling", "Gyldendal", 2007));
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
