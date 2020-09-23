package app.library.shared;

import app.library.network.dto.BookDto;

public interface ItemClickCallbacks {
    void onBookItemClicked(BookDto bookDto);
}
