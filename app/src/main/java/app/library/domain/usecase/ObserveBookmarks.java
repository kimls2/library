package app.library.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.library.data.repositories.bookmark.BookmarkRepository;
import app.library.network.dto.BookDto;
import io.reactivex.Observable;

public class ObserveBookmarks {
    private final BookmarkRepository repository;

    @Inject
    public ObserveBookmarks(BookmarkRepository repository) {
        this.repository = repository;
    }

    public Observable<List<BookDto>> observe() {
        return repository.observeBookmarks();
    }
}
