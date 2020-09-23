package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.book.BookRepository;
import app.library.network.dto.BookDetailsDto;
import io.reactivex.Single;

public class GetBookDetailsUseCase {
    private final BookRepository repository;

    @Inject
    public GetBookDetailsUseCase(BookRepository repository) {
        this.repository = repository;
    }

    public Single<BookDetailsDto> getBookDetails(String isbn13) {
        return repository.getBookDetails(isbn13);
    }
}
