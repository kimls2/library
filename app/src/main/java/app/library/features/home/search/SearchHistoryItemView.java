package app.library.features.home.search;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;

import app.library.R;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

@EpoxyModelClass(layout = R.layout.view_holder_search_history_item)
public abstract class SearchHistoryItemView extends EpoxyModelWithHolder<SearchHistoryItemView.Holder> {
    @EpoxyAttribute
    String searchTerm;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(@NonNull Holder holder) {
        super.bind(holder);
        holder.searchText.setText(searchTerm);
        holder.root.setOnClickListener(clickListener);
    }

    static class Holder extends EpoxyHolder {

        FrameLayout root;
        TextView searchText;

        @Override
        protected void bindView(@NonNull View itemView) {
            searchText = itemView.findViewById(R.id.search_text);
            root = itemView.findViewById(R.id.root);
        }
    }
}
