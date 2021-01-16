package DTO;

import entities.Book;
import java.util.ArrayList;
import java.util.List;


public class BooksDTO {

    private List<BookDTO> all = new ArrayList();

    public BooksDTO(List<Book> booksEntities) {
        booksEntities.forEach((p) -> {
            all.add(new BookDTO(p));
        });
    }
    public List<BookDTO> getAll() {
        return all;
    }
}
