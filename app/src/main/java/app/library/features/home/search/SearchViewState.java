package app.library.features.home.search;

import java.util.ArrayList;
import java.util.List;

import app.library.network.dto.BookDto;

public class SearchViewState {
    private List<BookDto> books = new ArrayList<>();
    private boolean moreToLoad = true;
    private String query = "";
    private List<String> searchTermHistory = new ArrayList<>();

    public SearchViewState() {
    }

    public SearchViewState(List<BookDto> oldList, List<BookDto> books, boolean moreToLoad) {
        List<BookDto> newList = new ArrayList<>(oldList);
        newList.addAll(books);
        this.books = newList;
        this.moreToLoad = moreToLoad;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }

    public boolean isMoreToLoad() {
        return moreToLoad;
    }

    public void setMoreToLoad(boolean moreToLoad) {
        this.moreToLoad = moreToLoad;
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<String> getSearchTermHistory() {
        return searchTermHistory;
    }

    public void setSearchTermHistory(List<String> searchTermHistory) {
        this.searchTermHistory = searchTermHistory;
    }
}
