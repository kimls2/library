package app.library.data.repositories.search;

import javax.inject.Inject;

import app.library.domain.model.SearchResult;
import app.library.network.api.BookStoreApi;
import io.reactivex.Single;

public class SearchDataSource {
    private final BookStoreApi bookStoreApi;

    @Inject
    public SearchDataSource(BookStoreApi bookStoreApi) {
        this.bookStoreApi = bookStoreApi;
    }

    public Single<SearchResult> search(String query, int page) {
        return bookStoreApi.search(query, page)
                .map(response -> new SearchResult(response.getPage(), response.getBooks()));
    }
}
