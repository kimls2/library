package app.library.features.home.newbooks;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import app.library.domain.usecase.GetNewBooksUseCase;
import app.library.network.dto.BookDto;
import app.library.shared.BaseViewModel;
import app.library.util.AppSchedulers;
import timber.log.Timber;

public class NewBooksViewModel extends BaseViewModel {

    private final AppSchedulers schedulers;
    private final GetNewBooksUseCase getNewBooksUseCase;

    private final MutableLiveData<List<BookDto>> books = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public NewBooksViewModel(AppSchedulers schedulers, GetNewBooksUseCase getNewBooksUseCase) {
        this.schedulers = schedulers;
        this.getNewBooksUseCase = getNewBooksUseCase;
    }

    LiveData<List<BookDto>> getBooks() {
        return this.books;
    }

    LiveData<Boolean> getLoadingStatus() {
        return this.loading;
    }

    void load() {
        loading.setValue(true);
        disposables.add(
                getNewBooksUseCase.getNewBooks()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .subscribe(
                                books -> {
                                    this.books.setValue(books);
                                    loading.setValue(false);
                                },
                                Timber::e
                        )
        );
    }
}
