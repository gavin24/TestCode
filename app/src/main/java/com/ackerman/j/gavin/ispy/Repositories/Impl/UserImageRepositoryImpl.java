package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Repositories.UserImageRepository;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageRepositoryImpl implements UserImageRepository {
    public static final String TABLE_USERIMAGE = "userimage";

    private DatabaseHelper dh = new DatabaseHelper();

    public static final String COLUMN_USERIMAGE_ID = "id";
    public static final String COLUMN_USERIMAGE_IMAGE = "image";
    public static final String COLUMN_USERIMAGE_NAME = "name";
    public static final String COLUMN_USERIMAGE_USERID = "userid";

    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_USERIMAGE + "("
            + COLUMN_USERIMAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_USERIMAGE_NAME + " TEXT NULL , "
            + COLUMN_USERIMAGE_IMAGE + " BLOB NULL , "
            + COLUMN_USERIMAGE_USERID + " INTEGER  NULL );";


    @Override
    public UserImage findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERIMAGE,
                new String[]{
                        COLUMN_USERIMAGE_ID,
                        COLUMN_USERIMAGE_NAME,
                        COLUMN_USERIMAGE_IMAGE,
                        COLUMN_USERIMAGE_USERID},

                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserImage animal = new UserImage.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                    .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }

    @Override
    public UserImage findByUserId(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_USERIMAGE,
                new String[]{
                        COLUMN_USERIMAGE_ID,
                        COLUMN_USERIMAGE_NAME,
                        COLUMN_USERIMAGE_IMAGE,
                        COLUMN_USERIMAGE_USERID},

                COLUMN_USERIMAGE_USERID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final UserImage animal = new UserImage.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                    .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                    .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                    .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }


    @Override
    public UserImage save(UserImage entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //     values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_USERIMAGE_NAME, entity.getName());
        values.put(COLUMN_USERIMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_USERIMAGE_USERID, entity.getuserId());
        long id = db.insertOrThrow(TABLE_USERIMAGE, null, values);

        UserImage insertedEntity = new UserImage.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public UserImage update(UserImage entity) {
        SQLiteDatabase db =    DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERIMAGE_ID, entity.getId());
        values.put(COLUMN_USERIMAGE_NAME, entity.getName());
        values.put(COLUMN_USERIMAGE_IMAGE, entity.getImage());
        values.put(COLUMN_USERIMAGE_USERID, entity.getuserId());
        db.update(
                TABLE_USERIMAGE,
                values,
                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public UserImage delete(UserImage entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_USERIMAGE,
                COLUMN_USERIMAGE_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<UserImage> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<UserImage> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_USERIMAGE, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final UserImage animals = new UserImage.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_ID)))
                        .name(cursor.getString(cursor.getColumnIndex(COLUMN_USERIMAGE_NAME)))
                        .image(cursor.getBlob(cursor.getColumnIndex(COLUMN_USERIMAGE_IMAGE)))
                        .userId(cursor.getLong(cursor.getColumnIndex(COLUMN_USERIMAGE_USERID)))
                        .build();
                animal.add(animals);
            } while (cursor.moveToNext());
        }
        DatabaseManager.getInstance().closeDatabase();
        return animal;
    }

    @Override
    public int deleteAll() {
        SQLiteDatabase db =   DatabaseManager.getInstance().openDatabase();
        int rowsDeleted = db.delete(TABLE_USERIMAGE,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
