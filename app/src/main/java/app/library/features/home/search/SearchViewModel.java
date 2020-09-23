package app.library.features.home.search;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import app.library.domain.model.SearchResult;
import app.library.domain.usecase.ObserveSearchTerms;
import app.library.domain.usecase.SearchUseCase;
import app.library.network.dto.BookDto;
import app.library.shared.BaseViewModel;
import app.library.util.AppSchedulers;
import io.reactivex.subjects.BehaviorSubject;
import timber.log.Timber;

public class SearchViewModel extends BaseViewModel {

    private final SearchUseCase searchUseCase;
    private final AppSchedulers schedulers;

    private final BehaviorSubject<String> querySubject = BehaviorSubject.create();
    private final MutableLiveData<SearchViewState> viewState = new MutableLiveData<>(new SearchViewState());
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();
    private int currentPage = 0;

    @Inject
    public SearchViewModel(SearchUseCase searchUseCase, AppSchedulers schedulers, ObserveSearchTerms observeSearchTerms) {
        this.searchUseCase = searchUseCase;
        this.schedulers = schedulers;
        disposables.add(
                querySubject.debounce(300, TimeUnit.MILLISECONDS)
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .subscribe(this::performSearch, Timber::e)
        );
        disposables.add(
                observeSearchTerms.observe()
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .subscribe(this::onNext, Timber::e)
        );
    }

    LiveData<SearchViewState> getViewState() {
        return this.viewState;
    }

    LiveData<Boolean> getLoading() {
        return this.loading;
    }

    void setSearchQuery(String query) {
        SearchViewState currentState = viewState.getValue();
        if (currentState != null) {
            currentState.setQuery(query);
            viewState.setValue(currentState);
        }
        querySubject.onNext(query);
    }

    void loadMore() {
        performSearch(querySubject.getValue());
    }

    private void performSearch(String query) {
        loading.setValue(true);
        disposables.add(
                searchUseCase.search(query, currentPage)
                        .subscribeOn(schedulers.io())
                        .observeOn(schedulers.main())
                        .doOnError(throwable -> loading.setValue(false))
                        .subscribe(this::onSearchSuccess, Timber::e)
        );
    }

    private void onSearchSuccess(SearchResult searchResult) {
        currentPage = searchResult.getCurrentPage() + 1;
        loading.setValue(false);
        SearchViewState currentState = viewState.getValue();
        if (currentState != null) {
            if (searchResult.getBooks().isEmpty()) {
                currentState.setBooks(Collections.emptyList());
            } else {
                List<BookDto> newList = new ArrayList<>(currentState.getBooks());
                newList.addAll(searchResult.getBooks());
                currentState.setBooks(newList);
            }
            currentState.setMoreToLoad(!searchResult.getBooks().isEmpty());
            viewState.setValue(currentState);
        }
    }

    private void onNext(List<String> searchTerms) {
        SearchViewState currentState = viewState.getValue();
        if (currentState != null) {
            currentState.setSearchTermHistory(searchTerms);
            viewState.setValue(currentState);
        }
    }
}
