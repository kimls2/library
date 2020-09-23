package app.library.domain.usecase;

import javax.inject.Inject;

import app.library.data.repositories.search.SearchRepository;
import app.library.domain.model.SearchResult;
import io.reactivex.Single;

public class SearchUseCase {
    private final SearchRepository repository;

    @Inject
    public SearchUseCase(SearchRepository repository) {
        this.repository = repository;
    }

    public Single<SearchResult> search(String query, int page) {
        if (query == null || query.isEmpty()) {
            return Single.just(new SearchResult());
        }
        return repository.search(query, page);
    }
}
