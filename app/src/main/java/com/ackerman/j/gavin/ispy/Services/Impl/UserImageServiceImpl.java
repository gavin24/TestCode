package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.UserImage;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Repositories.Impl.ImageRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.Impl.UserImageRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.UserImageRepository;
import com.ackerman.j.gavin.ispy.Services.ImageService;
import com.ackerman.j.gavin.ispy.Services.UserImageService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImageServiceImpl extends Service implements UserImageService {
    final private UserImageRepository repository;

    private final IBinder localBinder = new UserImageServiceLocalBinder();

    private static UserImageServiceImpl service = null;

    public static UserImageServiceImpl getInstance()
    {
        if(service == null)
            service = new UserImageServiceImpl();
        return service;
    }

    public UserImageServiceImpl()
    {
        repository = new UserImageRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class UserImageServiceLocalBinder extends Binder {
        public UserImageServiceImpl getService() {
            return UserImageServiceImpl.this;
        }
    }


    @Override
    public UserImage addImage(UserImage animal) {
        return repository.save(animal);
    }
    @Override
    public UserImage deleteImage(UserImage animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<UserImage> getAllImages() {
        try {
            ArrayList<UserImage> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<UserImage>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllImages() {
        repository.deleteAll();
    }
    @Override
    public UserImage updateImage(UserImage animal) {
        return repository.update(animal);
    }

    @Override
    public UserImage getImage(Long Id) {
        return repository.findById(Id);
    }
    @Override
    public UserImage getImageByUserId(Long Id) {
        return repository.findByUserId(Id);
    }
}
