package app.library.data.repositories.book;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.BookDto;
import io.reactivex.Single;

import static org.mockito.Mockito.when;

public class BookRepositoryTest {
    private BookRepository repository;

    @Mock
    private BookDataSource bookDataSource;

    @Mock
    private BookStore bookStore;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
        repository = new BookRepository(bookStore, bookDataSource);
    }

    @Test
    public void testGetNewBooks() {
        List<BookDto> books = new ArrayList<>();
        BookDto book1 = new BookDto();
        book1.setTitle("book1");
        BookDto book2 = new BookDto();
        book2.setTitle("book2");
        books.add(book1);
        books.add(book2);
        when(bookDataSource.getNewBooks()).thenReturn(Single.just(books));
        repository.getNewBooks().test()
                .assertNoErrors()
                .assertValue(books);
    }

    @Test
    public void testGetBookDetails() {
        String isbn13 = "some isbn13";
        BookDetailsDto bookDetailsDto = new BookDetailsDto();
        bookDetailsDto.setTitle("some book");
        bookDetailsDto.setIsbn13(isbn13);

        when(bookDataSource.getBookDetails(isbn13)).thenReturn(Single.just(bookDetailsDto));
        repository.getBookDetails(isbn13).test()
                .assertNoErrors()
                .assertValue(bookDetailsDto);
    }

}
