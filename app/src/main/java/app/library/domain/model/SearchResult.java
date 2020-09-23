package app.library.domain.model;

import java.util.ArrayList;
import java.util.List;

import app.library.network.dto.BookDto;

public class SearchResult {
    private int currentPage = 0;
    private List<BookDto> books = new ArrayList<>();

    public SearchResult() {
    }

    public SearchResult(int currentPage, List<BookDto> books) {
        this.currentPage = currentPage;
        this.books = books;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public List<BookDto> getBooks() {
        return books;
    }

    public void setBooks(List<BookDto> books) {
        this.books = books;
    }
}
