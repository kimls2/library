package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.bookmark.BookmarkRepository;
import app.library.network.dto.BookDto;

public class RemoveBookmark {
    private final BookmarkRepository repository;

    @Inject
    public RemoveBookmark(BookmarkRepository repository) {
        this.repository = repository;
    }

    public void remove(BookDto bookDto) {
        repository.removeBookmark(bookDto);
    }
}
