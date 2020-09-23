package app.library.features.home.history;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import app.library.databinding.FragmentHistoryBinding;
import app.library.features.bookdetails.BookDetailsActivity;
import app.library.inject.ViewModelFactory;
import app.library.shared.BooksEpoxyController;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class HistoryFragment extends Fragment {

    @Inject
    ViewModelFactory<HistoryViewModel> viewModelFactory;

    @Inject
    BooksEpoxyController controller;

    private HistoryViewModel viewModel;
    private FragmentHistoryBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.create(HistoryViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentHistoryBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller.setCallbacks(bookDto -> BookDetailsActivity.start(requireContext(), bookDto.getIsbn13()));
        binding.rv.setController(controller);

        viewModel.getHistory().observe(getViewLifecycleOwner(), bookDtos -> controller.setData(bookDtos));
    }
}
