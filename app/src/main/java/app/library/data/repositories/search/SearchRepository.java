package app.library.data.repositories.search;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

import app.library.domain.model.SearchResult;
import app.library.network.dto.BookDto;
import io.reactivex.Observable;
import io.reactivex.Single;

@Singleton
public class SearchRepository {

    private final SearchStore searchStore;
    private final SearchDataSource searchDataSource;

    @Inject
    public SearchRepository(SearchStore searchStore, SearchDataSource searchDataSource) {
        this.searchStore = searchStore;
        this.searchDataSource = searchDataSource;
    }

    public Single<SearchResult> search(String query, int page) {
        List<BookDto> cachedResult = searchStore.getResults(query);
        if (page == 0 && cachedResult != null && !cachedResult.isEmpty()) {
            return Single.just(new SearchResult(0, cachedResult));
        }

        return searchDataSource.search(query, page)
                .doOnSuccess(result -> searchStore.setResults(query, result.getBooks()));
    }

    public Observable<List<String>> observeSearchTerms() {
        return searchStore.observerSearchTerms();
    }
}
