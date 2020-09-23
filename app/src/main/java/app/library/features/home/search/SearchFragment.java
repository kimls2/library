package app.library.features.home.search;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;

import javax.inject.Inject;

import app.library.databinding.FragmentSearchBinding;
import app.library.features.bookdetails.BookDetailsActivity;
import app.library.inject.ViewModelFactory;
import app.library.network.dto.BookDto;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SearchFragment extends Fragment {

    @Inject
    ViewModelFactory<SearchViewModel> viewModelFactory;

    private SearchViewModel viewModel;
    private FragmentSearchBinding binding;
    private SearchEpoxyController controller;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.create(SearchViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        controller = new SearchEpoxyController(new SearchEpoxyController.Callbacks() {
            @Override
            public void loadMore() {
                viewModel.loadMore();
            }

            @Override
            public void onSearchTermClicked(String searchTerm) {
                binding.searchView.setQuery(searchTerm, true);
            }

            @Override
            public void onBookItemClicked(BookDto bookDto) {
                BookDetailsActivity.start(requireContext(), bookDto.getIsbn13());
            }
        });
        binding.rv.setController(controller);

        binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                viewModel.setSearchQuery(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                viewModel.setSearchQuery(query);
                return true;
            }
        });

        viewModel.getViewState().observe(getViewLifecycleOwner(), searchViewState -> controller.setData(searchViewState));
        viewModel.getLoading().observe(getViewLifecycleOwner(), loading -> {
            if (loading) {
                binding.searchProgress.setVisibility(View.VISIBLE);
            } else {
                binding.searchProgress.setVisibility(View.GONE);
            }
        });
    }
}
