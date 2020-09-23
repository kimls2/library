package app.library.features.home.bookmark;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import app.library.domain.usecase.ObserveBookmarks;
import app.library.domain.usecase.RemoveBookmark;
import app.library.network.dto.BookDto;
import app.library.shared.BaseViewModel;
import app.library.util.AppSchedulers;
import timber.log.Timber;

public class BookmarkViewModel extends BaseViewModel {

    private final RemoveBookmark removeBookmark;

    private final MutableLiveData<List<BookDto>> bookmarks = new MutableLiveData<>();

    @Inject
    public BookmarkViewModel(ObserveBookmarks getBookmarks, AppSchedulers schedulers, RemoveBookmark removeBookmark) {
        this.removeBookmark = removeBookmark;
        disposables.add(
                getBookmarks.observe()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .subscribe(bookmarks::setValue, Timber::e)
        );
    }

    LiveData<List<BookDto>> getBookmarks() {
        return bookmarks;
    }

    void removeBookmark(int position) {
        List<BookDto> currentBookmarks = bookmarks.getValue();
        if (currentBookmarks != null) {
            BookDto toBeRemovedItem = currentBookmarks.get(position);
            removeBookmark.remove(toBeRemovedItem);
        }
    }
}
