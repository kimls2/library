package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.bookmark.BookmarkRepository;
import app.library.network.dto.BookDto;

public class ChangeBookmarkStatus {
    private final BookmarkRepository repository;

    @Inject
    public ChangeBookmarkStatus(BookmarkRepository repository) {
        this.repository = repository;
    }

    public void change(BookDto bookDto) {
        repository.changeBookmarkStatus(bookDto);
    }
}
