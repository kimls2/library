package app.library.shared;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.EpoxyAttribute;
import com.airbnb.epoxy.EpoxyHolder;
import com.airbnb.epoxy.EpoxyModelClass;
import com.airbnb.epoxy.EpoxyModelWithHolder;
import com.bumptech.glide.Glide;
import com.google.android.material.card.MaterialCardView;

import app.library.R;
import app.library.network.dto.BookDto;

import static com.airbnb.epoxy.EpoxyAttribute.Option.DoNotHash;

@EpoxyModelClass(layout = R.layout.view_holder_book_item)
public abstract class BookItemEpoxyModel extends EpoxyModelWithHolder<BookItemEpoxyModel.Holder> {

    @EpoxyAttribute
    BookDto bookDto;

    @EpoxyAttribute(DoNotHash)
    View.OnClickListener clickListener;

    @Override
    public void bind(@NonNull Holder holder) {
        super.bind(holder);
        holder.title.setText(bookDto.getTitle());
        holder.subtitle.setText(bookDto.getSubtitle());
        holder.price.setText(bookDto.getPrice());
        holder.url.setText(bookDto.getUrl());
        holder.isbn13.setText(bookDto.getIsbn13());
        holder.root.setOnClickListener(clickListener);
        Glide.with(holder.image)
                .load(bookDto.getImage())
                .centerCrop()
                .into(holder.image);
    }

    static class Holder extends EpoxyHolder {
        MaterialCardView root;
        ImageView image;
        TextView title;
        TextView subtitle;
        TextView price;
        TextView url;
        TextView isbn13;

        @Override
        protected void bindView(@NonNull View itemView) {
            root = itemView.findViewById(R.id.root);
            image = itemView.findViewById(R.id.book_image);
            title = itemView.findViewById(R.id.title_text);
            subtitle = itemView.findViewById(R.id.subtitle_text);
            price = itemView.findViewById(R.id.price);
            url = itemView.findViewById(R.id.url);
            isbn13 = itemView.findViewById(R.id.isbn13);
        }
    }
}
