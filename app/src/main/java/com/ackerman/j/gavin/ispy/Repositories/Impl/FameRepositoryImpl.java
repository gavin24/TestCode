package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Repositories.FameRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class FameRepositoryImpl  implements FameRepository {
    public static final String TABLE_FAME = "fame";
    private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_FAME_ID = "id";
    public static final String COLUMN_FAME_USERID = "userid";
    public static final String COLUMN_FAME_STORYID = "storyid";
    public static final String COLUMN_FAME_DISLIKES = "dislikes";
    public static final String COLUMN_FAME_LIKES = "likes";
    public static final String COLUMN_FAME_SHARES = "shares";
    public static final String COLUMN_FAME_VIEWS = "views";
    public static final String COLUMN_CREATED_AT = "createdat";


    private static final String CREATE_TABLE_FAME = "CREATE TABLE "
            + TABLE_FAME + "("   + COLUMN_FAME_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_FAME_USERID + " INTEGER  NOT NULL , "
            + COLUMN_FAME_STORYID + " INTEGER NOT NULL ,"
            + COLUMN_FAME_DISLIKES + " INTEGER  NOT NULL , "
            + COLUMN_FAME_LIKES + " INTEGER  NOT NULL  , "
            + COLUMN_FAME_SHARES + " INTEGER  NOT NULL  , "
            + COLUMN_FAME_VIEWS + " INTEGER  NOT NULL  );";



    @Override
    public Fame findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_FAME,
                new String[]{
                        COLUMN_FAME_ID,
                        COLUMN_FAME_USERID,
                        COLUMN_FAME_STORYID,
                        COLUMN_FAME_DISLIKES,
                        COLUMN_FAME_LIKES,
                        COLUMN_FAME_SHARES,
                        COLUMN_FAME_VIEWS},

                COLUMN_FAME_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Fame animal = new Fame.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_ID)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_USERID)))
                    .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_STORYID)))
                    .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_DISLIKES)))
                    .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_LIKES)))
                    .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_SHARES)))
                    .views(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_VIEWS)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }


    @Override
    public Fame findByStoryId(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_FAME,
                new String[]{
                        COLUMN_FAME_ID,
                        COLUMN_FAME_USERID,
                        COLUMN_FAME_STORYID,
                        COLUMN_FAME_DISLIKES,
                        COLUMN_FAME_LIKES,
                        COLUMN_FAME_SHARES,
                        COLUMN_FAME_VIEWS},

                COLUMN_FAME_STORYID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Fame animal = new Fame.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_ID)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_USERID)))
                    .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_STORYID)))
                    .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_DISLIKES)))
                    .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_LIKES)))
                    .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_SHARES)))
                    .views(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_VIEWS)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }
    @Override
    public Fame save(Fame entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_FAME_USERID, entity.getUserId());
        values.put(COLUMN_FAME_STORYID, entity.getStoryId());
        values.put(COLUMN_FAME_SHARES, entity.getShares());
        values.put(COLUMN_FAME_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_FAME_LIKES, entity.getLikes());
        values.put(COLUMN_FAME_VIEWS, entity.getViews());
        long id = db.insertOrThrow(TABLE_FAME, null, values);
        Fame insertedEntity = new Fame.Builder()
                .copy(entity)
                .id(id)
                .build();
    //    DatabaseManager.getInstance().closeDatabase();
        return insertedEntity;
    }

    @Override
    public Fame update(Fame entity) {
        SQLiteDatabase db =  DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_FAME_ID, entity.getId());

        values.put(COLUMN_FAME_USERID, entity.getUserId());
        values.put(COLUMN_FAME_STORYID, entity.getStoryId());
        values.put(COLUMN_FAME_SHARES, entity.getShares());
        values.put(COLUMN_FAME_DISLIKES, entity.getDisLikes());
        values.put(COLUMN_FAME_LIKES, entity.getLikes());
        values.put(COLUMN_FAME_VIEWS, entity.getViews());
        db.update(
                TABLE_FAME,
                values,
                COLUMN_FAME_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Fame delete(Fame entity) {
        DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_FAME,
                COLUMN_FAME_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<Fame> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Fame> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_FAME, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Fame animals = new Fame.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_ID)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_USERID)))
                        .storyId(cursor.getLong(cursor.getColumnIndex(COLUMN_FAME_STORYID)))
                        .dislikes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_DISLIKES)))
                        .likes(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_LIKES)))
                        .shares(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_SHARES)))
                        .views(cursor.getInt(cursor.getColumnIndex(COLUMN_FAME_VIEWS)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_FAME,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
