package app.library.features.home.newbooks;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import app.library.databinding.FragmentNewBooksBinding;
import app.library.features.bookdetails.BookDetailsActivity;
import app.library.inject.ViewModelFactory;
import app.library.shared.BooksEpoxyController;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class NewBooksFragment extends Fragment {

    @Inject
    ViewModelFactory<NewBooksViewModel> viewModelFactory;

    @Inject
    BooksEpoxyController controller;

    private FragmentNewBooksBinding binding;
    private NewBooksViewModel viewModel;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.create(NewBooksViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentNewBooksBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller.setCallbacks(bookDto -> BookDetailsActivity.start(requireContext(), bookDto.getIsbn13()));
        binding.rv.setController(controller);

        viewModel.getBooks().observe(getViewLifecycleOwner(), books -> controller.setData(books));
        viewModel.getLoadingStatus().observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                binding.loadingProgress.setVisibility(View.VISIBLE);
            } else {
                binding.loadingProgress.setVisibility(View.GONE);
            }
        });
        viewModel.load();
    }
}
