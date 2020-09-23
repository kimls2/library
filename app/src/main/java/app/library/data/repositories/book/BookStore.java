package app.library.data.repositories.book;

import android.util.LruCache;

import javax.inject.Inject;

public class BookStore {
    @Inject
    public BookStore() {
    }

    private final LruCache<String, String> cache = new LruCache(32);

    public String getMemoByIsbn13(String isbn13) {
        return cache.get(isbn13);
    }

    public void setMemo(String isbn13, String memo) {
        cache.put(isbn13, memo);
    }
}
