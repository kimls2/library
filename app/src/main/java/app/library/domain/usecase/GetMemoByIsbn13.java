package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.book.BookRepository;

public class GetMemoByIsbn13 {
    private final BookRepository repository;

    @Inject
    public GetMemoByIsbn13(BookRepository repository) {
        this.repository = repository;
    }

    public String get(String isbn13) {
        return repository.getMemoByIsb13(isbn13);
    }
}
