package com.devcolibri.translator.activity;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.devcolibri.translator.R;
import com.devcolibri.translator.MyCredentialsApp;
import com.devcolibri.translator.db.AppDatabase;
import com.devcolibri.translator.entity.Word;

public class AddWordActivity extends AppCompatActivity {
    private EditText wordEditText;
    private EditText translationEditText;
    private Button addWordButton;

    private AppDatabase appDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_word);

        initViews();

        appDatabase = MyCredentialsApp.getDatabase();
    }

    private void initViews() {
        getSupportActionBar().setTitle(R.string.add_creds_screen);

        wordEditText = findViewById(R.id.word_edit_text);
        translationEditText = findViewById(R.id.translation_edit_text);
        addWordButton = findViewById(R.id.add_word_button);

        addWordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String wordText = wordEditText.getText().toString();
                String translationText = translationEditText.getText().toString();

                Word word = new Word(wordText, translationText);
                saveToDatabase(word);
            }
        });
    }

    @SuppressLint("StaticFieldLeak")
    private void saveToDatabase(final Word word) {
        new AsyncTask<Void, Void, Boolean>() {
            @Override
            protected Boolean doInBackground(Void... voids) {
                appDatabase.getPersonDao().insert(word);
                return true;
            }

            @Override
            protected void onPostExecute(Boolean isSuccess) {
                setResult(RESULT_OK);
                finish();
            }
        }.execute();
    }

}
