package app.library.shared;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.ModelView;

import app.library.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
public class LoadMoreView extends FrameLayout {
    public LoadMoreView(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.load_more, this);
    }
}
