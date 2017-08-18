package com.ackerman.j.gavin.ispy.Config.DbConstants;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.ackerman.j.gavin.ispy.Config.Util.App;

/**
 * Created by gavin.ackerman on 2016-04-23.
 */
public class DatabaseHelper  extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="ISpy.db";
    public static final int DATABASE_VERSION=25;




    // Table Names
    public static final String TABLE_FAME = "fame";
    public static final String TABLE_IMAGE = "image";
    public static final String TABLE_STORY = "story";
    public static final String TABLE_TAG = "tag";
    public static final String TABLE_USER = "user";
    public static final String TABLE_USERTAGS = "usertags";
    public static final String TABLE_USERIMAGE = "userimage";
    public static final String TABLE_PROFILEINFO = "profileinfo";
    public static final String TABLE_USERFAME = "userfame";
    public static final String TABLE_SUBSCRIBER = "subscriber";


    public static final String COLUMN_USERFAME_ID = "id";
    public static final String COLUMN_USERFAME_USERID = "userid";
    public static final String COLUMN_USERFAME_STORYID = "storyid";
    public static final String COLUMN_USERFAME_DISLIKES = "dislikes";
    public static final String COLUMN_USERFAME_LIKES = "likes";
    public static final String COLUMN_USERFAME_SHARES = "shares";
    public static final String COLUMN_USERFAME_VIEWS = "views";

    public static final String COLUMN_SUBSCRIBER_ID = "id";
    public static final String COLUMN_SUBSCRIBER_USERID = "userid";
    public static final String COLUMN_SUBSCRIBER_STATUS = "status";
    public static final String COLUMN_SUBSCRIBER_SUBSCRIBERUSERID = "subscriberuserid";

    public static final String COLUMN_USERIMAGE_ID = "id";
    public static final String COLUMN_USERIMAGE_IMAGE = "image";
    public static final String COLUMN_USERIMAGE_NAME = "name";
    public static final String COLUMN_USERIMAGE_USERID = "userid";

    public static final String COLUMN_PROFILEINFO_ID = "id";
    public static final String COLUMN_PROFILEINFO_USERID = "userid";
    public static final String COLUMN_PROFILEINFO_AGE = "age";
    public static final String COLUMN_PROFILEINFO_ABOUT = "about";

    public static final String COLUMN_FAME_ID = "id";
    public static final String COLUMN_FAME_USERID = "userid";
    public static final String COLUMN_FAME_STORYID = "storyid";
    public static final String COLUMN_FAME_DISLIKES = "dislikes";
    public static final String COLUMN_FAME_LIKES = "likes";
    public static final String COLUMN_FAME_SHARES = "shares";
    public static final String COLUMN_FAME_VIEWS = "views";

    public static final String COLUMN_IMAGE_ID = "id";
    public static final String COLUMN_IMAGE_IMAGE = "image";
    public static final String COLUMN_IMAGE_NAME = "name";
    public static final String COLUMN_IMAGE_USERID = "userid";

    public static final String COLUMN_STORY_ID = "id";
    public static final String COLUMN_STORY_TAG = "tag";
    public static final String COLUMN_STORY_NAME = "name";
    public static final String COLUMN_STORY_TEXT = "text";
    public static final String COLUMN_STORY_USERID = "userid";
    public static final String COLUMN_STORY_IMAGEID = "imageid";

    public static final String COLUMN_TAG_ID = "id";
    public static final String COLUMN_TAG_LOCATION = "location";
    public static final String COLUMN_TAG_NAME = "name";

    public static final String COLUMN_USER_ID = "id";
    public static final String COLUMN_USER_SURNAME = "surname";
    public static final String COLUMN_USER_NAME = "name";
    public static final String COLUMN_USER_EMAIL = "email";
    public static final String COLUMN_USER_PASSWORD = "password";

    public static final String COLUMN_USERTAGS_ID = "id";
    public static final String COLUMN_USERTAGS_USERID = "userid";
    public static final String COLUMN_USERTAGS_NAME = "name";


    // Table Create Statements
    // Todo table create statement
    private static final String CREATE_TABLE_FAME = "CREATE TABLE "
            + TABLE_FAME + "("   + COLUMN_FAME_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_FAME_USERID + " INTEGER  NOT NULL , "
            + COLUMN_FAME_STORYID + " INTEGER NOT NULL ,"
            + COLUMN_FAME_DISLIKES + " INTEGER  NOT NULL , "
            + COLUMN_FAME_LIKES + " INTEGER  NOT NULL  , "
            + COLUMN_FAME_SHARES + " INTEGER  NOT NULL  , "
            + COLUMN_FAME_VIEWS + " INTEGER  NOT NULL  );";


    private static final String CREATE_TABLE_SUBSCRIBER = " CREATE TABLE "
            + TABLE_SUBSCRIBER + "("
            + COLUMN_SUBSCRIBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUBSCRIBER_USERID + " INTEGER NULL , "
            + COLUMN_SUBSCRIBER_STATUS + " INTEGER NULL , "
            + COLUMN_SUBSCRIBER_SUBSCRIBERUSERID + " INTEGER  NULL );";

    private static final String CREATE_TABLE_USERFAME = "CREATE TABLE "
            + TABLE_USERFAME + "("   + COLUMN_USERFAME_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USERFAME_USERID + " INTEGER  NOT NULL , "
            + COLUMN_USERFAME_STORYID + " INTEGER NOT NULL ,"
            + COLUMN_USERFAME_DISLIKES + " INTEGER  NOT NULL , "
            + COLUMN_USERFAME_LIKES + " INTEGER  NOT NULL  , "
            + COLUMN_USERFAME_SHARES + " INTEGER  NOT NULL  , "
            + COLUMN_USERFAME_VIEWS + " INTEGER  NOT NULL  );";

    private static final String CREATE_TABLE_PROFILEINFO = " CREATE TABLE "
            + TABLE_PROFILEINFO + "("
            + COLUMN_PROFILEINFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROFILEINFO_USERID + " INTEGER NULL , "
            + COLUMN_PROFILEINFO_AGE + " INTEGER NULL , "
            + COLUMN_PROFILEINFO_ABOUT + " TEXT  NULL );";


    private static final String CREATE_TABLE_USERIMAGE = " CREATE TABLE "
            + TABLE_USERIMAGE + "("
            + COLUMN_USERIMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERIMAGE_NAME + " TEXT NULL , "
            + COLUMN_USERIMAGE_IMAGE + " BLOB NULL , "
            + COLUMN_USERIMAGE_USERID + " INTEGER  NULL );";

    // Tag table create statement
    private static final String CREATE_TABLE_IMAGE = "CREATE TABLE " + TABLE_IMAGE
            + "("   + COLUMN_IMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_IMAGE_NAME + " TEXT NULL , "
            + COLUMN_IMAGE_IMAGE + " BLOB NOT NULL , "
            + COLUMN_IMAGE_USERID + " INTEGER  NULL );";

    // todo_tag table create statement
    private static final String CREATE_TABLE_STORY = "CREATE TABLE "
            + TABLE_STORY + "("  + COLUMN_STORY_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_STORY_NAME + " TEXT  NOT NULL , "
            + COLUMN_STORY_TAG + " TEXT NOT NULL ,"
            + COLUMN_STORY_TEXT + " TEXT  NULL ,"
            + COLUMN_STORY_USERID + " INTEGER NULL , "
            + COLUMN_STORY_IMAGEID + " INTEGER  NULL );";

    // Tag table create statement
    private static final String CREATE_TABLE_TAG = "CREATE TABLE " + TABLE_TAG
            + "("   + COLUMN_TAG_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_TAG_NAME + " TEXT  NOT NULL , "
            + COLUMN_TAG_LOCATION + " TEXT  NULL );";

    // todo_tag table create statement
    private static final String CREATE_TABLE_USER = "CREATE TABLE "
            + TABLE_USER + "("  + COLUMN_USER_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USER_NAME + " TEXT  NOT NULL , "
            + COLUMN_USER_SURNAME + " INTEGER NOT NULL ,"
            + COLUMN_USER_EMAIL + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_USER_PASSWORD + " TEXT  NULL );";

    // todo_tag table create statement
    private static final String CREATE_TABLE_USERTAGS = "CREATE TABLE "
            + TABLE_USERTAGS + "(" + COLUMN_USERTAGS_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USERTAGS_NAME + " TEXT UNIQUE  NOT NULL , "
            + COLUMN_USERTAGS_USERID + " INTEGER NULL );";

   /* public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }*/
    public DatabaseHelper( ) {
        super(App.getAppContext(), DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        // creating required tables
        db.execSQL(CREATE_TABLE_FAME);
        db.execSQL(CREATE_TABLE_TAG);
        db.execSQL(CREATE_TABLE_STORY);
        db.execSQL(CREATE_TABLE_IMAGE);
        db.execSQL(CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_USERTAGS);
        db.execSQL(CREATE_TABLE_USERIMAGE);
        db.execSQL(CREATE_TABLE_PROFILEINFO);
        db.execSQL(CREATE_TABLE_USERFAME);
        db.execSQL(CREATE_TABLE_SUBSCRIBER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // on upgrade drop older tables
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_FAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TAG);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STORY);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERTAGS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERIMAGE);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_PROFILEINFO);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERFAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBSCRIBER);

        // create new tables
        onCreate(db);
    }
    // closing database
    public void closeDB() {
        SQLiteDatabase db = this.getReadableDatabase();
        if (db != null && db.isOpen())
            db.close();
    }
}
