package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;
import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Repositories.ProfileInfoRepository;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ProfileInfoRepositoryImpl implements ProfileInfoRepository {
    public static final String TABLE_PROFILEINFO = "profileinfo";

    private DatabaseHelper dh = new DatabaseHelper();


    public static final String COLUMN_PROFILEINFO_ID = "id";
    public static final String COLUMN_PROFILEINFO_USERID = "userid";
    public static final String COLUMN_PROFILEINFO_AGE = "age";
    public static final String COLUMN_PROFILEINFO_ABOUT = "about";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_PROFILEINFO + "("
            + COLUMN_PROFILEINFO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_PROFILEINFO_USERID + " INTEGER NULL , "
            + COLUMN_PROFILEINFO_AGE + " INTEGER NULL , "
            + COLUMN_PROFILEINFO_ABOUT + " TEXT  NULL );";


    @Override
    public ProfileInfo findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_PROFILEINFO,
                new String[]{
                        COLUMN_PROFILEINFO_ID,
                        COLUMN_PROFILEINFO_USERID,
                        COLUMN_PROFILEINFO_AGE,
                        COLUMN_PROFILEINFO_ABOUT},

                COLUMN_PROFILEINFO_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final ProfileInfo animal = new ProfileInfo.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_PROFILEINFO_ID)))
                    .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_PROFILEINFO_USERID)))
                    .age(cursor.getInt(cursor.getColumnIndex(COLUMN_PROFILEINFO_AGE)))
                    .about(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILEINFO_ABOUT)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public ProfileInfo save(ProfileInfo entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //     values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_PROFILEINFO_USERID, entity.getUserId());
        values.put(COLUMN_PROFILEINFO_AGE, entity.getAge());
        values.put(COLUMN_PROFILEINFO_ABOUT, entity.getAbout());
        long id = db.insertOrThrow(TABLE_PROFILEINFO, null, values);

        ProfileInfo insertedEntity = new ProfileInfo.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public ProfileInfo update(ProfileInfo entity) {
        SQLiteDatabase db =    DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_PROFILEINFO_ID, entity.getId());
        values.put(COLUMN_PROFILEINFO_USERID, entity.getUserId());
        values.put(COLUMN_PROFILEINFO_AGE, entity.getAge());
        values.put(COLUMN_PROFILEINFO_ABOUT, entity.getAbout());
        db.update(
                TABLE_PROFILEINFO,
                values,
                COLUMN_PROFILEINFO_ID + " =? ",
                new String[]{String.valueOf(entity.getId())}
        );
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ProfileInfo delete(ProfileInfo entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_PROFILEINFO,
                COLUMN_PROFILEINFO_ID + " =? ",
                new String[]{String.valueOf(entity.getId())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<ProfileInfo> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<ProfileInfo> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_PROFILEINFO, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final ProfileInfo animals = new ProfileInfo.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_PROFILEINFO_ID)))
                        .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_PROFILEINFO_USERID)))
                        .age(cursor.getInt(cursor.getColumnIndex(COLUMN_PROFILEINFO_AGE)))
                        .about(cursor.getString(cursor.getColumnIndex(COLUMN_PROFILEINFO_ABOUT)))
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
        int rowsDeleted = db.delete(TABLE_PROFILEINFO,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
