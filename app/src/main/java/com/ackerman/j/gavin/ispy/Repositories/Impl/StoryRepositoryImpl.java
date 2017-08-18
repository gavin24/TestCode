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

import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Repositories.StoryRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class StoryRepositoryImpl implements StoryRepository {
    public static final String TABLE_STORY = "story";

    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_STORY_ID = "id";
    public static final String COLUMN_STORY_TAG = "tag";
    public static final String COLUMN_STORY_NAME = "name";
    public static final String COLUMN_STORY_TEXT = "text";
    public static final String COLUMN_STORY_USERID = "userid";
    public static final String COLUMN_STORY_IMAGEID = "imageid";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_STORY + "("
            + COLUMN_STORY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_STORY_NAME + " TEXT  NOT NULL , "
            + COLUMN_STORY_TAG + " TEXT NOT NULL ,"
            + COLUMN_STORY_TEXT + " TEXT  NULL ,"
            + COLUMN_STORY_USERID + " INTEGER NULL , "
            + COLUMN_STORY_IMAGEID + " INTEGER  NULL );";


    @Override
    public Story findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_STORY,
                new String[]{
                        COLUMN_STORY_ID,
                        COLUMN_STORY_NAME,
                        COLUMN_STORY_TAG,
                        COLUMN_STORY_TEXT,
                        COLUMN_STORY_USERID,
                        COLUMN_STORY_IMAGEID},

                COLUMN_STORY_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Story animal = new Story.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_NAME)))
                    .tag(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_TAG)))
                    .text(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_TEXT)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_USERID)))
                    .image(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_IMAGEID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Story save(Story entity) {
        SQLiteDatabase db = DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_STORY_NAME, entity.getName());
        values.put(COLUMN_STORY_TAG, entity.getTag());
        values.put(COLUMN_STORY_TEXT, entity.getText());
        values.put(COLUMN_STORY_USERID, entity.getuserId());
        values.put(COLUMN_STORY_IMAGEID, entity.getImage());
        long id = db.insertOrThrow(TABLE_STORY, null, values);
        Story insertedEntity = new Story.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Story update(Story entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_STORY_ID, entity.getId());

        values.put(COLUMN_STORY_NAME, entity.getName());
        values.put(COLUMN_STORY_TAG, entity.getTag());
        values.put(COLUMN_STORY_TEXT, entity.getText());
        values.put(COLUMN_STORY_USERID, entity.getuserId());
        values.put(COLUMN_STORY_IMAGEID, entity.getImage());
        db.update(
                TABLE_STORY,
                values,
                COLUMN_STORY_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Story delete(Story entity) {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_STORY,
                COLUMN_STORY_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Story> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Story> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_STORY, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Story animals = new Story.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_NAME)))
                        .tag(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_TAG)))
                        .text(cursor.getString(cursor.getColumnIndex(COLUMN_STORY_TEXT)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_USERID)))
                        .image(cursor.getLong(cursor.getColumnIndex(COLUMN_STORY_IMAGEID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }


    @Override
    public int deleteAll() {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_STORY,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
