package com.example.android.myapp1;

import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.myapp1.data.AppContract.AppEntry;

public class ComplainFragment extends Fragment implements
        LoaderManager.LoaderCallbacks<Cursor>{
    View v;

    private static final int COMPLAIN_LOADER = 0;

    AppCursorAdapter mCursorAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_complain, container, false);

        final FloatingActionButton newPage = (FloatingActionButton) v.findViewById(R.id.fab1);
        newPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {

                Intent intent = new Intent(getActivity(), ComplainActivity.class);
                startActivity(intent);
            }
        });

    ListView petListView = (ListView)v.findViewById(R.id.list);

    View emptyView =v.findViewById(R.id.empty_view);
        petListView.setEmptyView(emptyView);

    mCursorAdapter = new AppCursorAdapter(getActivity(), null);
        petListView.setAdapter(mCursorAdapter);

        petListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {

            Intent intent = new Intent(getActivity(), ComplainActivity.class);

            Uri currentPetUri = ContentUris.withAppendedId(AppEntry.CONTENT_URI, id);

            intent.setData(currentPetUri);

            startActivity(intent);
        }
    });


    getLoaderManager().initLoader(COMPLAIN_LOADER, null, this);

        return v;
}

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        String[] projection = {
                AppEntry._ID,
                AppEntry.COLUMN_COMPLAIN_TITLE,
                AppEntry.COLUMN_COMPLAIN_DESCRIPTION};


        return new CursorLoader(getActivity(),
                AppEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }


    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        mCursorAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        mCursorAdapter.swapCursor(null);
    }


}


