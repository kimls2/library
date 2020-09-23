package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.book.BookRepository;

public class SaveMemo {
    private final BookRepository repository;

    @Inject
    public SaveMemo(BookRepository repository) {
        this.repository = repository;
    }

    public void save(String isbn13, String memo) {
        repository.saveMemo(isbn13, memo);
    }
}
