package app.library.features.bookdetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import app.library.R;
import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class BookDetailsActivity extends AppCompatActivity {
    public static final String KEY_ISBN_13 = "key_isbn_13";

    public static void start(Context context, String isbn13) {
        Intent starter = new Intent(context, BookDetailsActivity.class);
        starter.putExtra(KEY_ISBN_13, isbn13);
        context.startActivity(starter);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_details);

        String isbn13 = getIntent().getStringExtra(KEY_ISBN_13);
        if (isbn13 != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.details_content, BookDetailFragment.start(isbn13), null)
                    .commitNow();
        } else {
            throw new RuntimeException("Extra isbn13 shouldn't be null");
        }
    }
}
