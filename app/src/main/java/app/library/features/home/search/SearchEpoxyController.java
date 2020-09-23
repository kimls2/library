package app.library.features.home.search;

import com.airbnb.epoxy.TypedEpoxyController;

import app.library.network.dto.BookDto;
import app.library.shared.BookItemEpoxyModel_;
import app.library.shared.ItemClickCallbacks;
import app.library.shared.LoadMoreViewModel_;

public class SearchEpoxyController extends TypedEpoxyController<SearchViewState> {

    interface Callbacks extends ItemClickCallbacks {
        void loadMore();

        void onSearchTermClicked(String searchTerm);
    }

    public SearchEpoxyController(Callbacks callbacks) {
        this.callbacks = callbacks;
    }

    private final Callbacks callbacks;

    @Override
    protected void buildModels(SearchViewState state) {
        if (state.getQuery().isEmpty()) {
            for (String searchTerm : state.getSearchTermHistory()) {
                new SearchHistoryItemView_()
                        .id(searchTerm)
                        .searchTerm(searchTerm)
                        .clickListener(view -> callbacks.onSearchTermClicked(searchTerm))
                        .addTo(this);
            }
        } else {
            for (BookDto item : state.getBooks()) {
                new BookItemEpoxyModel_()
                        .id(item.getIsbn13())
                        .bookDto(item)
                        .clickListener(view -> callbacks.onBookItemClicked(item))
                        .addTo(this);
            }

            if (state.isMoreToLoad()) {
                new LoadMoreViewModel_()
                        .id("load_more_view")
                        .onBind((model, view, position) -> {
                            if (state.isMoreToLoad()) {
                                callbacks.loadMore();
                            }
                        })
                        .spanSizeOverride((totalSpanCount, position, itemCount) -> totalSpanCount)
                        .addTo(this);
            }
        }
    }
}
