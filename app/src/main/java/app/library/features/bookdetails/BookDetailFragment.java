package app.library.features.bookdetails;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import app.library.databinding.FragmentBookDetailsBinding;
import app.library.inject.ViewModelFactory;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BookDetailFragment extends Fragment {

    public static final String KEY_ISBN_13 = "key_isbn_13";

    public static BookDetailFragment start(String isbn13) {
        Bundle bundle = new Bundle();
        bundle.putString(KEY_ISBN_13, isbn13);
        BookDetailFragment fragment = new BookDetailFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Inject
    ViewModelFactory<BookDetailsFragmentViewModel> viewModelFactory;

    private BookDetailsFragmentViewModel viewModel;
    private FragmentBookDetailsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = viewModelFactory.create(BookDetailsFragmentViewModel.class);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentBookDetailsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel.getMemo().observe(getViewLifecycleOwner(), memo -> binding.memo.setText(memo));
        viewModel.isBookmarked().observe(getViewLifecycleOwner(), bookmarked -> binding.fabFavorite.setSelected(bookmarked));
        viewModel.getBookDetails().observe(getViewLifecycleOwner(), bookDetails -> {
            Glide.with(requireActivity())
                    .load(bookDetails.getImage())
                    .into(binding.image);

            binding.titleText.setText(bookDetails.getTitle());
            binding.subtitleText.setText(bookDetails.getSubtitle());
            binding.authors.setText(bookDetails.getAuthors());
            binding.publisher.setText(bookDetails.getPublisher());
            binding.language.setText(bookDetails.getLanguage());
            binding.isbn10.setText(bookDetails.getIsbn10());
            binding.isbn13.setText(bookDetails.getIsbn13());
            binding.pages.setText(bookDetails.getPages());
            binding.year.setText(bookDetails.getYear());
            binding.rating.setText(bookDetails.getRating());
            binding.desc.setText(bookDetails.getDesc());
            binding.price.setText(bookDetails.getPrice());
            binding.url.setText(bookDetails.getUrl());
        });

        String isbn13 = requireArguments().getString(KEY_ISBN_13);
        viewModel.load(isbn13);

        binding.fabFavorite.setOnClickListener(v -> viewModel.onFabClicked());
        binding.toolbar.setNavigationOnClickListener(v -> requireActivity().onBackPressed());
        binding.memo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // do nothing
            }

            @Override
            public void onTextChanged(CharSequence text, int start, int before, int count) {
                viewModel.setMemo(text.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // do nothing
            }
        });
    }
}
