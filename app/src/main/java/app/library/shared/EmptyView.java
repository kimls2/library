package app.library.shared;

import android.content.Context;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.airbnb.epoxy.ModelView;

import app.library.R;

@ModelView(autoLayout = ModelView.Size.MATCH_WIDTH_MATCH_HEIGHT)
public class EmptyView extends FrameLayout {
    public EmptyView(@NonNull Context context) {
        super(context);
        init();
    }

    private void init() {
        inflate(getContext(), R.layout.empty_view, this);
    }
}
