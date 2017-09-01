package com.example.android.myapp1;

import android.app.AlertDialog;
import android.app.LoaderManager;
import android.content.ContentValues;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.android.myapp1.data.AppContract.AppEntry;

public class ComplainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<Cursor> {

    private static final int EXISTING_COMPLAIN_LOADER = 0;
    private Uri mCurrentComplainUri;
private TextInputEditText mTitleEditText;
    private TextInputEditText mDescriptionEditText;
    private Spinner mCategorySpinner;
    private Spinner mIssueSpinner;
    private int mCategory;
    private int mIssue;
    private boolean mComplainHasChanged = false;

    private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
        @Override
        public boolean onTouch(View view, MotionEvent motionEvent) {
            mComplainHasChanged = true;
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        // Set the content of the activity to use the activity_main.xml layout file
        setContentView(R.layout.activity_complain);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        mCurrentComplainUri = intent.getData();


        if (mCurrentComplainUri == null) {

            setTitle(getString(R.string.editor_activity_title_new_complain));


            invalidateOptionsMenu();
        } else {

            setTitle(getString(R.string.editor_activity_title_edit_complain));


            getLoaderManager().initLoader(EXISTING_COMPLAIN_LOADER, null, this);
        }
        mTitleEditText=(TextInputEditText) findViewById(R.id.edit_title);
        mDescriptionEditText=(TextInputEditText) findViewById(R.id.edit_description);
        mCategorySpinner=(Spinner) findViewById(R.id.spinner_category);
        setUpCategorySpinner();
        mIssueSpinner=(Spinner) findViewById(R.id.spinner_issue_type);
        setUpIssueSpinner();

        mTitleEditText.setOnTouchListener(mTouchListener);
        mDescriptionEditText.setOnTouchListener(mTouchListener);
        mCategorySpinner.setOnTouchListener(mTouchListener);
        mIssueSpinner.setOnTouchListener(mTouchListener);


    }

    private void setUpCategorySpinner(){
        ArrayAdapter categorySpinnerAdapter=ArrayAdapter.createFromResource(this,R.array.array_category,android.R.layout.simple_spinner_item);
        categorySpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mCategorySpinner.setAdapter(categorySpinnerAdapter);
        mCategorySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selection = parent.getItemAtPosition(position).toString();
                if (!TextUtils.isEmpty(selection)) {
                    if (selection.equals(getString(R.string.plumber))) {
                        mCategory = AppEntry.CATEGORY_PLUMBER;
                    } else if (selection.equals(getString(R.string.electrician))) {
                        mCategory = AppEntry.CATEGORY_ELECTRICIAN;
                    } else {
                        mCategory = AppEntry.CATEGORY_LIFT;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent){
                mCategory = AppEntry.CATEGORY_PLUMBER;
            }
        });
    }

    private void setUpIssueSpinner(){
        ArrayAdapter issueSpinnerAdapter=ArrayAdapter.createFromResource(this,R.array.array_issue_type,android.R.layout.simple_spinner_item);
        issueSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        mIssueSpinner.setAdapter(issueSpinnerAdapter);
        mIssueSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener(){


            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String select = parent.getItemAtPosition(position).toString();
                if (!TextUtils.isEmpty(select)) {
                    if (select.equals(getString(R.string.personal))) {
                        mIssue = AppEntry.ISSUE_TYPE_PERSONAL;
                    }else {
                        mIssue = AppEntry.ISSUE_TYPE_COMMUNITY;
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                mIssue = AppEntry.ISSUE_TYPE_PERSONAL;
            }
        });
    }
    private void savePet() {

        String titleString = mTitleEditText.getText().toString().trim();
        String descriptionString = mDescriptionEditText.getText().toString().trim();


        if (mCurrentComplainUri == null &&
                TextUtils.isEmpty(titleString) && TextUtils.isEmpty(descriptionString) && mCategory == AppEntry.CATEGORY_PLUMBER
                && mIssue == AppEntry.ISSUE_TYPE_PERSONAL) {

            return;
        }


        ContentValues values = new ContentValues();
        values.put(AppEntry.COLUMN_COMPLAIN_TITLE, titleString);
        values.put(AppEntry.COLUMN_COMPLAIN_DESCRIPTION, descriptionString);
        values.put(AppEntry.COLUMN_COMPLAIN_CATEGORY, mCategory);
        values.put(AppEntry.COLUMN_COMPLAIN_ISSUE_TYPE, mIssue);


        if (mCurrentComplainUri == null) {

            Uri newUri = getContentResolver().insert(AppEntry.CONTENT_URI, values);


            if (newUri == null) {

                Toast.makeText(this, getString(R.string.editor_insert_complain_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_insert_complain_successful),
                        Toast.LENGTH_SHORT).show();
            }
        } else {

            int rowsAffected = getContentResolver().update(mCurrentComplainUri, values, null, null);


            if (rowsAffected == 0) {

                Toast.makeText(this, getString(R.string.editor_update_complain_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_update_complain_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_complain, menu);
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if (mCurrentComplainUri == null) {
            MenuItem menuItem = menu.findItem(R.id.action_delete);
            menuItem.setVisible(false);
        }
        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                showDeleteConfirmationDialog();
                return true;
            case R.id.action_save:
                savePet();
                finish();
                return true;
            case android.R.id.home:
                if (!mComplainHasChanged) {
                   onBackPressed();
                }


                DialogInterface.OnClickListener discardButtonClickListener =
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // User clicked "Discard" button, navigate to parent activity.
                             onBackPressed();
                            }
                        };

                // Show a dialog that notifies the user they have unsaved changes
                showUnsavedChangesDialog(discardButtonClickListener);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        if (!mComplainHasChanged) {
            super.onBackPressed();
            return;
        }


        DialogInterface.OnClickListener discardButtonClickListener =
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // User clicked "Discard" button, close the current activity.
                        finish();
                    }
                };

        // Show dialog that there are unsaved changes
        showUnsavedChangesDialog(discardButtonClickListener);
    }
    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                AppEntry._ID,
                AppEntry.COLUMN_COMPLAIN_TITLE,
                AppEntry.COLUMN_COMPLAIN_DESCRIPTION,
                AppEntry.COLUMN_COMPLAIN_CATEGORY,
                AppEntry.COLUMN_COMPLAIN_ISSUE_TYPE};


        return new CursorLoader(this,
                mCurrentComplainUri,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor == null || cursor.getCount() < 1) {
            return;
        }


        if (cursor.moveToFirst()) {

            int titleColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_TITLE);
            int descriptionColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_DESCRIPTION);
            int categoryColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_CATEGORY);
            int issueColumnIndex = cursor.getColumnIndex(AppEntry.COLUMN_COMPLAIN_ISSUE_TYPE);


            String title = cursor.getString(titleColumnIndex);
            String description = cursor.getString(descriptionColumnIndex);
            int category = cursor.getInt(categoryColumnIndex);
            int issue = cursor.getInt(issueColumnIndex);



            mTitleEditText.setText(title);
            mDescriptionEditText.setText(description);



            switch (category) {
                case AppEntry.CATEGORY_ELECTRICIAN:
                    mCategorySpinner.setSelection(1);
                    break;
                case AppEntry.CATEGORY_LIFT:
                    mCategorySpinner.setSelection(2);
                    break;
                default:
                    mCategorySpinner.setSelection(0);
                    break;
            }
            switch(issue){
                case AppEntry.ISSUE_TYPE_COMMUNITY:
                    mIssueSpinner.setSelection(1);
                    break;
                    default:
                        mIssueSpinner.setSelection(0);
                        break;
            }
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

        mTitleEditText.setText("");
        mDescriptionEditText.setText("");
        mCategorySpinner.setSelection(0);
        mIssueSpinner.setSelection(0);
    }
    private void showUnsavedChangesDialog(
            DialogInterface.OnClickListener discardButtonClickListener) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.unsaved_changes_dialog_msg);
        builder.setPositiveButton(R.string.discard, discardButtonClickListener);
        builder.setNegativeButton(R.string.keep_editing, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void showDeleteConfirmationDialog() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(R.string.delete_dialog_msg);
        builder.setPositiveButton(R.string.delete, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                deleteComplain();
            }
        });
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

                if (dialog != null) {
                    dialog.dismiss();
                }
            }
        });


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }


    private void deleteComplain() {

        if (mCurrentComplainUri != null) {

            int rowsDeleted = getContentResolver().delete(mCurrentComplainUri, null, null);


            if (rowsDeleted == 0) {

                Toast.makeText(this, getString(R.string.editor_delete_complain_failed),
                        Toast.LENGTH_SHORT).show();
            } else {

                Toast.makeText(this, getString(R.string.editor_delete_complain_successful),
                        Toast.LENGTH_SHORT).show();
            }
        }


        finish();
    }


}