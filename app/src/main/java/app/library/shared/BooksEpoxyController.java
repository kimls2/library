package app.library.shared;

import com.airbnb.epoxy.TypedEpoxyController;

import java.util.List;

import javax.inject.Inject;

import app.library.network.dto.BookDto;

public class BooksEpoxyController extends TypedEpoxyController<List<BookDto>> {

    private ItemClickCallbacks callbacks;

    @Inject
    public BooksEpoxyController() {
    }

    public void setCallbacks(ItemClickCallbacks callbacks) {
        this.callbacks = callbacks;
    }

    @Override
    protected void buildModels(List<BookDto> data) {
        if (data.isEmpty()) {
            new EmptyViewModel_()
                    .id("empty_view")
                    .addTo(this);
        } else {
            for (BookDto item : data) {
                new BookItemEpoxyModel_()
                        .id(item.getIsbn13())
                        .bookDto(item)
                        .clickListener(view -> callbacks.onBookItemClicked(item))
                        .addTo(this);
            }
        }
    }
}
