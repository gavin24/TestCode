package com.ackerman.j.gavin.ispy.Repositories.Impl;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseHelper;
import com.ackerman.j.gavin.ispy.Config.DbConstants.DatabaseManager;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Subscriber;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Repositories.SubscriberRepository;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class SubscriberRepositoryImpl implements SubscriberRepository {
    public static final String TABLE_SUBSCRIBER = "subscriber";

    private DatabaseHelper dh = new DatabaseHelper();

    public static final String COLUMN_SUBSCRIBER_ID = "id";
    public static final String COLUMN_SUBSCRIBER_USERID = "userid";
    public static final String COLUMN_SUBSCRIBER_STATUS = "status";
    public static final String COLUMN_SUBSCRIBER_SUBSCRIBERUSERID = "subscriberuserid";


    // Database creation sql statement
    private static final String DATABASE_CREATE = " CREATE TABLE "
            + TABLE_SUBSCRIBER + "("
            + COLUMN_SUBSCRIBER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + COLUMN_SUBSCRIBER_USERID + " INTEGER NULL , "
            + COLUMN_SUBSCRIBER_STATUS + " INTEGER NULL , "
            + COLUMN_SUBSCRIBER_SUBSCRIBERUSERID + " INTEGER  NULL );";


    @Override
    public Subscriber findById(Long id) {

        SQLiteDatabase db = dh.getReadableDatabase();
        Cursor cursor = db.query(
                TABLE_SUBSCRIBER,
                new String[]{
                        COLUMN_SUBSCRIBER_ID,
                        COLUMN_SUBSCRIBER_USERID,
                        COLUMN_SUBSCRIBER_STATUS,
                        COLUMN_SUBSCRIBER_SUBSCRIBERUSERID},

                COLUMN_SUBSCRIBER_ID + " =? ",
                new String[]{String.valueOf(id)},
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {

            final Subscriber animal = new Subscriber.Builder()
                    .id(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_ID)))
                    .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_USERID)))
                    .status(cursor.getInt(cursor.getColumnIndex(COLUMN_SUBSCRIBER_STATUS)))
                    .subscriberuserid(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_SUBSCRIBERUSERID)))
                    .build();

            return animal;
        } else {
            return null;
        }
    }



    @Override
    public Subscriber save(Subscriber entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        //     values.put(COLUMN_IMAGE_ID, entity.getId());
        values.put(COLUMN_SUBSCRIBER_USERID, entity.getUserId());
        values.put(COLUMN_SUBSCRIBER_STATUS, entity.getStatus());
        values.put(COLUMN_SUBSCRIBER_SUBSCRIBERUSERID, entity.getSubscriber());
        long id = db.insertOrThrow(TABLE_SUBSCRIBER, null, values);

        Subscriber insertedEntity = new Subscriber.Builder()
                .copy(entity)
                .id(id)
                .build();
        return insertedEntity;
    }

    @Override
    public Subscriber update(Subscriber entity) {
        SQLiteDatabase db =    DatabaseManager.getInstance().openDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_SUBSCRIBER_ID, entity.getid());
        values.put(COLUMN_SUBSCRIBER_USERID, entity.getUserId());
        values.put(COLUMN_SUBSCRIBER_STATUS, entity.getStatus());
        values.put(COLUMN_SUBSCRIBER_SUBSCRIBERUSERID, entity.getSubscriber());
        db.update(
                TABLE_SUBSCRIBER,
                values,
                COLUMN_SUBSCRIBER_ID + " =? ",
                new String[]{String.valueOf(entity.getid())}
        );
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public Subscriber delete(Subscriber entity) {
        SQLiteDatabase db =     DatabaseManager.getInstance().openDatabase();
        db.delete(
                TABLE_SUBSCRIBER,
                COLUMN_SUBSCRIBER_ID + " =? ",
                new String[]{String.valueOf(entity.getid())});
        DatabaseManager.getInstance().closeDatabase();
        return entity;
    }

    @Override
    public ArrayList<Subscriber> findAll() {
        SQLiteDatabase db = dh.getReadableDatabase();
        ArrayList<Subscriber> animal = new ArrayList<>();
        DatabaseManager.getInstance().openDatabase();
        Cursor cursor = db.query(TABLE_SUBSCRIBER, null,null,null,null,null,null);
        if (cursor.moveToFirst()) {
            do {


                final Subscriber animals = new Subscriber.Builder()
                        .id(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_ID)))
                        .userid(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_USERID)))
                        .status(cursor.getInt(cursor.getColumnIndex(COLUMN_SUBSCRIBER_STATUS)))
                        .subscriberuserid(cursor.getLong(cursor.getColumnIndex(COLUMN_SUBSCRIBER_SUBSCRIBERUSERID)))
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
        int rowsDeleted = db.delete(TABLE_SUBSCRIBER,null,null);
        DatabaseManager.getInstance().closeDatabase();
        return rowsDeleted;
    }


}
