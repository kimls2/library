package app.library.features.home.bookmark;

import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.epoxy.EpoxyTouchHelper;

import javax.inject.Inject;

import app.library.databinding.FragmentBookmarkBinding;
import app.library.features.bookdetails.BookDetailsActivity;
import app.library.inject.ViewModelFactory;
import app.library.shared.BookItemEpoxyModel;
import app.library.shared.BooksEpoxyController;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BookmarkFragment extends Fragment {

    @Inject
    ViewModelFactory<BookmarkViewModel> viewModelFactory;

    @Inject
    BooksEpoxyController controller;

    private BookmarkViewModel viewModel;
    private FragmentBookmarkBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.create(BookmarkViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookmarkBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller.setCallbacks(bookDto -> BookDetailsActivity.start(requireContext(), bookDto.getIsbn13()));
        binding.rv.setController(controller);
        viewModel.getBookmarks().observe(getViewLifecycleOwner(), bookDtos -> controller.setData(bookDtos));

        EpoxyTouchHelper.initSwiping(binding.rv)
                .leftAndRight()
                .withTarget(BookItemEpoxyModel.class)
                .andCallbacks(new EpoxyTouchHelper.SwipeCallbacks<BookItemEpoxyModel>() {
                    @Override
                    public void onSwipeCompleted(BookItemEpoxyModel model, View itemView, int position, int direction) {
                        viewModel.removeBookmark(position);
                    }

                    @Override
                    public void onSwipeProgressChanged(BookItemEpoxyModel model, View itemView, float swipeProgress, Canvas canvas) {
                        int alpha = (int) (Math.abs(swipeProgress) * 255);
                        if (swipeProgress > 0) {
                            itemView.setBackgroundColor(Color.argb(alpha, 0, 255, 0));
                        } else {
                            itemView.setBackgroundColor(Color.argb(alpha, 255, 0, 0));
                        }
                    }

                    @Override
                    public void clearView(BookItemEpoxyModel model, View itemView) {
                        itemView.setBackgroundColor(Color.WHITE);
                    }
                });
    }
}
