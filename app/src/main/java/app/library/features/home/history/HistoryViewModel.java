package app.library.features.home.history;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

import javax.inject.Inject;

import app.library.domain.usecase.ObserveHistory;
import app.library.network.dto.BookDto;
import app.library.shared.BaseViewModel;
import app.library.util.AppSchedulers;
import timber.log.Timber;

public class HistoryViewModel extends BaseViewModel {

    private final MutableLiveData<List<BookDto>> history = new MutableLiveData<>();

    @Inject
    public HistoryViewModel(ObserveHistory observeHistory, AppSchedulers schedulers) {
        disposables.add(
                observeHistory.observe()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .subscribe(history::setValue, Timber::e)
        );
    }

    LiveData<List<BookDto>> getHistory() {
        return history;
    }
}
