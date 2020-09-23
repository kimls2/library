package app.library.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.library.data.repositories.book.BookRepository;
import app.library.network.dto.BookDto;
import io.reactivex.Observable;

public class ObserveHistory {
    private final BookRepository repository;

    @Inject
    public ObserveHistory(BookRepository repository) {
        this.repository = repository;
    }

    public Observable<List<BookDto>> observe() {
        return repository.observerHistory();
    }
}
