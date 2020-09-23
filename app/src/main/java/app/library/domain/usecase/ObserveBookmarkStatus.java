package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.bookmark.BookmarkRepository;
import app.library.network.dto.BookDto;
import io.reactivex.Observable;

public class ObserveBookmarkStatus {
    private final BookmarkRepository repository;

    @Inject
    public ObserveBookmarkStatus(BookmarkRepository repository) {
        this.repository = repository;
    }

    public Observable<Boolean> observe(BookDto bookDto) {
        return repository.observeBookmarkStatus(bookDto);
    }
}
