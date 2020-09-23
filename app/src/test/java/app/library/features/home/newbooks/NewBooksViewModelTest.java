package app.library.features.home.newbooks;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import app.library.domain.usecase.GetNewBooksUseCase;
import app.library.network.dto.BookDto;
import app.library.util.AppSchedulers;
import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

import static org.mockito.Mockito.when;

public class NewBooksViewModelTest {

    private NewBooksViewModel viewModel;

    @Mock
    private GetNewBooksUseCase newBooksUseCase;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Before
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        viewModel = new NewBooksViewModel(
                new AppSchedulers(
                        Schedulers.trampoline(),
                        Schedulers.trampoline()
                ),
                newBooksUseCase
        );
    }

    @Test
    public void loadTest() {
        List<BookDto> books = new ArrayList<>();
        BookDto book1 = new BookDto();
        book1.setTitle("book1");
        BookDto book2 = new BookDto();
        book2.setTitle("book2");
        books.add(book1);
        books.add(book2);

        when(newBooksUseCase.getNewBooks()).thenReturn(Single.just(books));

        viewModel.load();

        viewModel.getBooks().observeForever(bookDtos -> Assert.assertEquals(books, bookDtos));
    }
}
