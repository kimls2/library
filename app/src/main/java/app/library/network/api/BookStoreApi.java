package app.library.network.api;

import app.library.network.dto.BookDetailsDto;
import app.library.network.dto.Response;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface BookStoreApi {
    @GET("new")
    Single<Response> getNewBooks();

    @GET("search/{query}/{page}")
    Single<Response> search(@Path("query") String query, @Path("page") int page);

    @GET("books/{isbn13}")
    Single<BookDetailsDto> getBookDetails(@Path("isbn13") String isbn13);
}
