package app.library.inject;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

public class ViewModelFactory<T extends ViewModel> implements ViewModelProvider.Factory {

    private final T viewModel;

    @Inject
    ViewModelFactory(T viewModel) {
        this.viewModel = viewModel;
    }

    @SuppressWarnings({"unchecked", "TypeParameterHidesVisibleType"})
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) viewModel;
    }
}
