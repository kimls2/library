package app.library.data.repositories.search;

import android.util.LruCache;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

import app.library.network.dto.BookDto;
import io.reactivex.Observable;
import io.reactivex.subjects.BehaviorSubject;

public class SearchStore {

    private static final int MAX_CACHE_SIZE = 32;

    @Inject
    public SearchStore() {
    }

    private final LruCache<String, List<BookDto>> cache = new LruCache(MAX_CACHE_SIZE);
    private Set<String> searchTerms = new HashSet<>();
    private BehaviorSubject<List<String>> searchTermSubject = BehaviorSubject.create();

    public List<BookDto> getResults(String query) {
        return cache.get(query);
    }

    public void setResults(String query, List<BookDto> results) {
        cache.put(query, results);
        if (!results.isEmpty()) {
            searchTerms.add(query);
            searchTermSubject.onNext(new ArrayList<>(searchTerms));
        }
    }

    public Observable<List<String>> observerSearchTerms() {
        return searchTermSubject;
    }
}
