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

import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Repositories.TagRepository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class TagRepositoryImpl implements TagRepository {
    public static final String TABLE_TAG = "tag";
    private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_TAG_ID = "id";
    public static final String COLUMN_TAG_LOCATION = "location";
    public static final String COLUMN_TAG_NAME = "name";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_TAG + "("
            + COLUMN_TAG_ID + " INTEGER PRIMARY KEY  AUTOINCREMENT, "
            + COLUMN_TAG_NAME + " TEXT  NOT NULL , "
            + COLUMN_TAG_LOCATION + " TEXT  NULL );";

    
    @Override
    public Tag findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_TAG,
                new String[]{
                        COLUMN_TAG_ID,
                        COLUMN_TAG_NAME,
                        COLUMN_TAG_LOCATION},

                COLUMN_TAG_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Tag animal = new Tag.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_TAG_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_TAG_NAME)))
                    .location(cursor.getString(cursor.getColumnIndex(COLUMN_TAG_LOCATION)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Tag save(Tag entity) {
        DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_TAG_ID, entity.getId());

        values.put(COLUMN_TAG_NAME, entity.getName());
        values.put(COLUMN_TAG_LOCATION, entity.getlocation());
        long id = db.insertOrThrow(TABLE_TAG, null, values);
        Tag insertedEntity = new Tag.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Tag update(Tag entity) {
        DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUMN_TAG_NAME, entity.getName());
        values.put(COLUMN_TAG_LOCATION, entity.getlocation());

        db.update(
                TABLE_TAG,
                values,
                COLUMN_TAG_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public Tag delete(Tag entity) {
        DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_TAG,
                COLUMN_TAG_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<Tag> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Tag> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_TAG, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Tag animals = new Tag.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_TAG_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_TAG_NAME)))
                        .location(cursor.getString(cursor.getColumnIndex(COLUMN_TAG_LOCATION)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_TAG,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
