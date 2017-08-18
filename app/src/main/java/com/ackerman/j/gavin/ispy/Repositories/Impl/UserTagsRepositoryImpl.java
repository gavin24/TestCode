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

import com.ackerman.j.gavin.ispy.Domain.UserTags;
import com.ackerman.j.gavin.ispy.Repositories.UserTagsRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public class UserTagsRepositoryImpl  implements UserTagsRepository {
    public static final String TABLE_USERTAGS = "usertags";
    private SQLiteDatabase db;
    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_USERTAGS_ID = "id";
    public static final String COLUMN_USERTAGS_USERID = "userid";
    public static final String COLUMN_USERTAGS_NAME = "name";


    @Override
    public UserTags findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERTAGS,
                new String[]{
                        COLUMN_USERTAGS_ID,
                        COLUMN_USERTAGS_NAME,
                        COLUMN_USERTAGS_USERID},

                COLUMN_USERTAGS_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserTags animal = new UserTags.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERTAGS_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERTAGS_NAME)))
                    .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_USERTAGS_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public UserTags save(UserTags entity) {
        DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();


        values.put(COLUMN_USERTAGS_NAME, entity.getName());
        values.put(COLUMN_USERTAGS_USERID, entity.getUserId());

        long id = db.insertOrThrow(TABLE_USERTAGS, null, values);
        UserTags insertedEntity = new UserTags.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public UserTags update(UserTags entity) {
        DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERTAGS_ID, entity.getId());

        values.put(COLUMN_USERTAGS_NAME, entity.getName());
        values.put(COLUMN_USERTAGS_USERID, entity.getUserId());

        db.update(
                TABLE_USERTAGS,
                values,
                COLUMN_USERTAGS_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        return entity;
    }

    @Override
    public UserTags delete(UserTags entity) {
        DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_USERTAGS,
                COLUMN_USERTAGS_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        return entity;
    }

    @Override
    public ArrayList<UserTags> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<UserTags> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_USERTAGS, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final UserTags animals = new UserTags.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERTAGS_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERTAGS_NAME)))
                        .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_USERTAGS_USERID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        return animal;
    }

    @Override
    public int deleteAll() {
        DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_USERTAGS,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
