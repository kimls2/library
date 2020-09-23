package app.library.data.repositories.book;

import java.util.List;

import javax.inject.Inject;

import app.library.network.api.BookStoreApi;
import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.BookDto;
import app.library.network.dto.Response;
import io.reactivex.Single;

public class BookDataSource {

    private final BookStoreApi bookStoreApi;

    @Inject
    public BookDataSource(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    public Single<List<BookDto>> getNewBooks() {
        return bookStoreApi.getNewBooks()
                .map(Response::getBooks);
    }

    public Single<BookDetailsDto> getBookDetails(String isbn13) {
        return bookStoreApi.getBookDetails(isbn13);
    }
}
