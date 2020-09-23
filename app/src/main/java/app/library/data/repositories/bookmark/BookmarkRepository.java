package app.library.data.repositories.bookmark;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.library.network.dto.BookDto;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

@Singleton
public class BookmarkRepository {
    private Set<BookDto> books = new HashSet<>();
    private BehaviorSubject<Set<BookDto>> booksSubject = BehaviorSubject.createDefault(books);

    @Inject
    public BookmarkRepository() {
    }

    public Observable<Boolean> observeBookmarkStatus(BookDto bookDto) {
        return booksSubject.map(books -> books.contains(bookDto));
    }

    public Observable<List<BookDto>> observeBookmarks() {
        return booksSubject.map(ArrayList::new);
    }

    public void changeBookmarkStatus(BookDto bookDto) {
        if (books.contains(bookDto)) {
            removeBookmark(bookDto);
        } else {
            addToBookmark(bookDto);
        }
    }

    private void addToBookmark(BookDto bookDto) {
        books.add(bookDto);
        booksSubject.onNext(books);
    }

    public void removeBookmark(BookDto bookDto) {
        books.remove(bookDto);
        booksSubject.onNext(books);
    }
}
