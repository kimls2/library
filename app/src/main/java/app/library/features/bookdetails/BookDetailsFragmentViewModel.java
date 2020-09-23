package app.library.features.bookdetails;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import javax.inject.Inject;

import app.library.data.mappers.BookDetailsToBookDto;
import app.library.domain.usecase.ChangeBookmarkStatus;
import app.library.domain.usecase.GetBookDetailsUseCase;
import app.library.domain.usecase.GetMemoByIsbn13;
import app.library.domain.usecase.ObserveBookmarkStatus;
import app.library.domain.usecase.SaveMemo;
import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.BookDto;
import app.library.shared.BaseViewModel;
import app.library.util.AppSchedulers;
import timber.log.Timber;

public class BookDetailsFragmentViewModel extends BaseViewModel {
    private final AppSchedulers schedulers;
    private final GetBookDetailsUseCase getBookDetailsUseCase;
    private final ObserveBookmarkStatus observeBookmarkStatus;
    private final ChangeBookmarkStatus changeBookmarkStatus;
    private final GetMemoByIsbn13 getMemoByIsbn13;
    private final SaveMemo saveMemo;

    @Inject
    public BookDetailsFragmentViewModel(AppSchedulers schedulers, GetBookDetailsUseCase getBookDetailsUseCase, ObserveBookmarkStatus observeBookmarkStatus, ChangeBookmarkStatus changeBookmarkStatus, GetMemoByIsbn13 getMemoByIsbn13, SaveMemo saveMemo) {
        this.schedulers = schedulers;
        this.getBookDetailsUseCase = getBookDetailsUseCase;
        this.observeBookmarkStatus = observeBookmarkStatus;
        this.changeBookmarkStatus = changeBookmarkStatus;
        this.getMemoByIsbn13 = getMemoByIsbn13;
        this.saveMemo = saveMemo;
    }

    private final MutableLiveData<BookDetailsDto> bookDetails = new MutableLiveData<>();
    private final MutableLiveData<Boolean> bookmarked = new MutableLiveData<>();
    private final MutableLiveData<String> memo = new MutableLiveData<>();
    private String isbn13;

    LiveData<BookDetailsDto> getBookDetails() {
        return this.bookDetails;
    }

    LiveData<Boolean> isBookmarked() {
        return this.bookmarked;
    }

    LiveData<String> getMemo() {
        return this.memo;
    }

    void load(String isbn13) {
        this.isbn13 = isbn13;
        memo.setValue(getMemoByIsbn13.get(isbn13));
        disposables.add(
                getBookDetailsUseCase.getBookDetails(isbn13)
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .doOnSuccess(dto -> disposables.add(
                                observeBookmarkStatus.observe(new BookDto(
                                        dto.getImage(), dto.getPrice(), dto.getSubtitle(), dto.getIsbn13(), dto.getTitle(), dto.getUrl()
                                ))
                                        .subscribeOn(schedulers.io())
                                        .observeOn(schedulers.main())
                                        .subscribe(bookmarked::setValue, Timber::e)
                        ))
                        .subscribe(bookDetails::setValue, Timber::e)
        );
    }

    void onFabClicked() {
        BookDetailsDto bookDetailsDto = bookDetails.getValue();
        if (bookDetailsDto != null) {
            changeBookmarkStatus.change(BookDetailsToBookDto.map(bookDetailsDto));
        }
    }

    void setMemo(String memo) {
        saveMemo.save(isbn13, memo);
    }
}
