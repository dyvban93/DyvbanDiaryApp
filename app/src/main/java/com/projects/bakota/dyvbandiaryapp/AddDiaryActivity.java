package com.projects.bakota.dyvbandiaryapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Date;

import DAO.AppDatabase;
import DAO.DiaryEntry;

public class AddDiaryActivity extends AppCompatActivity  {


    public static final String EXTRA_DIARY_ID = "extraDiaryId";
    // Extra for the task ID to be received after rotation
    public static final String INSTANCE_DIARY_ID = "instanceDiaryId";
    private static final int DEFAULT_TASK_ID = -1;
    // Constant for logging
    private static final String TAG = AddDiaryActivity.class.getSimpleName();
    // Fields for views
    EditText mEditText;
    EditText mTitle;
    Button mButton;

    private int mTaskId = DEFAULT_TASK_ID;

    // Member variable for the Database
    private AppDatabase mDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_diary);

        initViews();

        mDb = AppDatabase.getInstance(getApplicationContext());

        if (savedInstanceState != null && savedInstanceState.containsKey(INSTANCE_DIARY_ID)) {
            mTaskId = savedInstanceState.getInt(INSTANCE_DIARY_ID, DEFAULT_TASK_ID);
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(INSTANCE_DIARY_ID, mTaskId);
        super.onSaveInstanceState(outState);
    }

    private void initViews() {
        mEditText = findViewById(R.id.description_editext);
        mTitle = findViewById(R.id.title_editext);

        mButton = findViewById(R.id.saveButton);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onSaveButtonClicked();
            }
        });
    }


    /**
     * onSaveButtonClicked is called when the "save" button is clicked.
     * It retrieves user input and inserts that new task data into the underlying database.
     */
    public void onSaveButtonClicked() {
        String description = mEditText.getText().toString();
       String title = mTitle.getText().toString();
        Date date = new Date();

        final DiaryEntry diary = new DiaryEntry(title, description, date);
        AppExecutors.getInstance().diskIO().execute(new Runnable() {
            @Override
            public void run() {

                if (mTaskId == DEFAULT_TASK_ID) {
                    // insert new diary
                    mDb.diaryDao().addDiary(diary);
                } /*else {
                    //update task
                    diary.setId(mTaskId);
                    mDb.diaryDao().updateDiary(diary);
                }*/
                finish();
            }
        });
    }



}
