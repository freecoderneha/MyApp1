package com.example.android.myapp1.data;

import android.content.ContentResolver;
import android.net.Uri;
import android.provider.BaseColumns;


public final class AppContract {

    private AppContract() {}
    public static final String CONTENT_AUTHORITY="com.example.android.myapp1";
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_COMPLAIN = "complain";

    public static final class AppEntry implements BaseColumns {
        public static final Uri CONTENT_URI = Uri.withAppendedPath(BASE_CONTENT_URI, PATH_COMPLAIN);


        public static final String CONTENT_LIST_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMPLAIN;

        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_COMPLAIN;

        public final static String TABLE_NAME = "complain";

        public final static String _ID = BaseColumns._ID;

        public final static String COLUMN_COMPLAIN_TITLE ="title";


        public final static String COLUMN_COMPLAIN_DESCRIPTION = "description";


        public final static String COLUMN_COMPLAIN_CATEGORY = "category";

        public final static String COLUMN_COMPLAIN_ISSUE_TYPE = "issue_type";

        public static final int CATEGORY_PLUMBER = 0;
        public static final int CATEGORY_ELECTRICIAN = 1;
        public static final int CATEGORY_LIFT = 2;

        public static final int ISSUE_TYPE_PERSONAL=0;
        public static final int  ISSUE_TYPE_COMMUNITY=1;

        public static boolean isValidCategory(int category) {
            if (category == CATEGORY_PLUMBER || category == CATEGORY_ELECTRICIAN || category == CATEGORY_LIFT) {
                return true;
            }
            return false;
        }
        public static boolean isValidIssue(int issue) {
            if (issue == ISSUE_TYPE_PERSONAL|| issue == ISSUE_TYPE_COMMUNITY) {
                return true;
            }
            return false;
        }
    }

}

