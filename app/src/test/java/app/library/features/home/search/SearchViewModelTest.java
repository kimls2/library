package app.library.features.home.search;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import app.library.domain.model.SearchResult;
import app.library.domain.usecase.ObserveSearchTerms;
import app.library.domain.usecase.SearchUseCase;
import app.library.network.dto.BookDto;
import app.library.util.AppSchedulers;
import io.reactivex.Observable;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class SearchViewModelTest {

    private SearchViewModel viewModel;

    @Mock
    private SearchUseCase searchUseCase;

    @Mock
    private ObserveSearchTerms observeSearchTerms;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        when(observeSearchTerms.observe()).thenReturn(Observable.just(Collections.emptyList()));
        viewModel = new SearchViewModel(
                searchUseCase,
                new AppSchedulers(
                        Schedulers.trampoline(),
                        Schedulers.trampoline()
                ),
                observeSearchTerms);
    }

    @Test
    public void loadMoreTest() {
        String query = "android";
        List<BookDto> books = new ArrayList<>();
        BookDto book1 = new BookDto();
        book1.setTitle("book1");
        BookDto book2 = new BookDto();
        book2.setTitle("book2");
        books.add(book1);
        books.add(book2);
        SearchResult result = new SearchResult(0, books);
        when(searchUseCase.search(query, 0)).thenReturn(Single.just(result));

        viewModel.setSearchQuery(query);
        viewModel.loadMore();

        viewModel.getViewState().observeForever(searchViewState -> assertEquals(result.getBooks(), searchViewState.getBooks()));
    }
}
