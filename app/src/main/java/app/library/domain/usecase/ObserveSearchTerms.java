package app.library.domain.usecase;

import java.util.List;

import javax.inject.Inject;

import app.library.data.repositories.search.SearchRepository;
import io.reactivex.Observable;

public class ObserveSearchTerms {
    private final SearchRepository repository;

    @Inject
    public ObserveSearchTerms(SearchRepository repository) {
        this.repository = repository;
    }

    public Observable<List<String>> observe() {
        return repository.observeSearchTerms();
    }
}
