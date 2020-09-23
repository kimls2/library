package app.library.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.library.data.repositories.book.BookRepository;
import app.library.network.dto.BookDto;
import io.reactivex.Single;

public class GetNewBooksUseCase {

    private final BookRepository repository;

    @Inject
    public GetNewBooksUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Single<List<BookDto>> getNewBooks() {
        return repository.getNewBooks();
    }
}
