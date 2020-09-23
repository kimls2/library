package app.library.data.repositories.book;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.library.data.mappers.BookDetailsToBookDto;
import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.BookDto;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.subjects.BehaviorSubject;

@Singleton
public class BookRepository {

    private final BookStore bookStore;
    private final BookDataSource bookDataSource;
    private BehaviorSubject<Set<BookDto>> historySubject = BehaviorSubject.createDefault(new HashSet<>());

    @Inject
    public BookRepository(BookStore bookStore, BookDataSource bookDataSource) {
        this.bookStore = bookStore;
        this.bookDataSource = bookDataSource;
    }

    public Single<List<BookDto>> getNewBooks() {
        return bookDataSource.getNewBooks();
    }

    public Single<BookDetailsDto> getBookDetails(String isbn13) {
        return bookDataSource.getBookDetails(isbn13)
                .doOnSuccess(bookDetailsDto -> {
                    Set<BookDto> current = historySubject.getValue();
                    current.add(BookDetailsToBookDto.map(bookDetailsDto));
                    historySubject.onNext(current);
                });
    }

    public Observable<List<BookDto>> observerHistory() {
        return historySubject.map(ArrayList::new);
    }

    public String getMemoByIsb13(String isbn13) {
        return bookStore.getMemoByIsbn13(isbn13);
    }

    public void saveMemo(String isbn13, String memo) {
        bookStore.setMemo(isbn13, memo);
    }
}
