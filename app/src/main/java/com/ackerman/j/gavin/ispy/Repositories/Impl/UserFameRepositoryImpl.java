package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;
import com.ackerman.j.gavin.ispy.Repositories.FameRepository;
import com.ackerman.j.gavin.ispy.Repositories.UserFameRepository;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserFameRepositoryImpl implements UserFameRepository {
    public static final String TABLE_USERFAME = "userfame";
    private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_USERFAME_ID = "id";
    public static final String COLUMN_USERFAME_USERID = "userid";
    public static final String COLUMN_USERFAME_STORYID = "storyid";
    public static final String COLUMN_USERFAME_DISLIKES = "dislikes";
    public static final String COLUMN_USERFAME_LIKES = "likes";
    public static final String COLUMN_USERFAME_SHARES = "shares";
    public static final String COLUMN_USERFAME_VIEWS = "views";
    public static final String COLUMN_CREATED_AT = "createdat";


    private static final String CREATE_TABLE_FAME = "CREATE TABLE "
            + TABLE_USERFAME + "("   + COLUMN_USERFAME_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_USERFAME_USERID + " INTEGER  NOT NULL , "
            + COLUMN_USERFAME_STORYID + " INTEGER NOT NULL ,"
            + COLUMN_USERFAME_DISLIKES + " INTEGER  NOT NULL , "
            + COLUMN_USERFAME_LIKES + " INTEGER  NOT NULL  , "
            + COLUMN_USERFAME_SHARES + " INTEGER  NOT NULL  , "
            + COLUMN_USERFAME_VIEWS + " INTEGER  NOT NULL  );";



    @Override
    public UserFame findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERFAME,
                new String[]{
                        COLUMN_USERFAME_ID,
                        COLUMN_USERFAME_USERID,
                        COLUMN_USERFAME_STORYID,
                        COLUMN_USERFAME_DISLIKES,
                        COLUMN_USERFAME_LIKES,
                        COLUMN_USERFAME_SHARES,
                        COLUMN_USERFAME_VIEWS},

                COLUMN_USERFAME_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserFame animal = new UserFame.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_ID)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_USERID)))
                    .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_STORYID)))
                    .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_DISLIKES)))
                    .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_LIKES)))
                    .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_SHARES)))
                    .views(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_VIEWS)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }


    @Override
    public UserFame findByStoryId(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERFAME,
                new String[]{
                        COLUMN_USERFAME_ID,
                        COLUMN_USERFAME_USERID,
                        COLUMN_USERFAME_STORYID,
                        COLUMN_USERFAME_DISLIKES,
                        COLUMN_USERFAME_LIKES,
                        COLUMN_USERFAME_SHARES,
                        COLUMN_USERFAME_VIEWS},

                COLUMN_USERFAME_STORYID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserFame animal = new UserFame.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_ID)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_USERID)))
                    .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_STORYID)))
                    .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_DISLIKES)))
                    .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_LIKES)))
                    .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_SHARES)))
                    .views(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_VIEWS)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }
    @Override
    public UserFame save(UserFame entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_USERFAME_USERID, entity.getUserId());
        values.put(COLUMN_USERFAME_STORYID, entity.getStoryId());
        values.put(COLUMN_USERFAME_SHARES, entity.getShares());
        values.put(COLUMN_USERFAME_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_USERFAME_LIKES, entity.getLikes());
        values.put(COLUMN_USERFAME_VIEWS, entity.getViews());
        long id = db.insertOrThrow(TABLE_USERFAME, null, values);
        UserFame insertedEntity = new UserFame.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public UserFame update(UserFame entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERFAME_ID, entity.getId());

        values.put(COLUMN_USERFAME_USERID, entity.getUserId());
        values.put(COLUMN_USERFAME_STORYID, entity.getStoryId());
        values.put(COLUMN_USERFAME_SHARES, entity.getShares());
        values.put(COLUMN_USERFAME_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_USERFAME_LIKES, entity.getLikes());
        values.put(COLUMN_USERFAME_VIEWS, entity.getViews());
        db.update(
                TABLE_USERFAME,
                values,
                COLUMN_USERFAME_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public UserFame delete(UserFame entity) {
        DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_USERFAME,
                COLUMN_USERFAME_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<UserFame> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<UserFame> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_USERFAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final UserFame animals = new UserFame.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_ID)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_USERID)))
                        .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERFAME_STORYID)))
                        .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_DISLIKES)))
                        .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_LIKES)))
                        .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_SHARES)))
                        .views(cursor.getInt(cursor.getColumnIndex(COLUMN_USERFAME_VIEWS)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_USERFAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
