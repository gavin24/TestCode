package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.UserFame;
import com.ackerman.j.gavin.ispy.Repositories.FameRepository;
import com.ackerman.j.gavin.ispy.Repositories.Impl.FameRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.Impl.UserFameRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.UserFameRepository;
import com.ackerman.j.gavin.ispy.Services.FameService;
import com.ackerman.j.gavin.ispy.Services.UserFameService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserFameServiceImpl extends Service implements UserFameService {
    final private UserFameRepository repository;

    private final IBinder localBinder = new UserFameServiceImpl.UserFameServiceLocalBinder();

    private static UserFameServiceImpl service = null;

    public static UserFameServiceImpl getInstance()
    {
        if(service == null)
            service = new UserFameServiceImpl();
        return service;
    }

    public UserFameServiceImpl()
    {
        repository = new UserFameRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class UserFameServiceLocalBinder extends Binder {
        public UserFameServiceImpl getService() {
            return UserFameServiceImpl.this;
        }
    }


    @Override
    public UserFame addFame(UserFame animal) {
        try{
            return repository.save(animal);
        }
        catch(Exception x)

        {
            x.printStackTrace();
        }
        return null;
    }
    @Override
    public UserFame deleteFame(UserFame animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<UserFame> getAllFames() {
        try {
            ArrayList<UserFame> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<UserFame>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllFames() {
        repository.deleteAll();
    }
    @Override
    public UserFame updateFame(UserFame animal) {
        return repository.update(animal);
    }

    @Override
    public UserFame getFame(Long Id) {
        return repository.findById(Id);
    }
    @Override
    public UserFame getFameByStoryId(Long Id) {
        return repository.findByStoryId(Id);
    }
    @Override
    public ArrayList<UserFame> getAllFames(Long id) {
        try {
            ArrayList<UserFame> userStories = new ArrayList<>();
            ArrayList<UserFame> result = new ArrayList<>();
            if (result.addAll(repository.findAll())) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getUserId() == id) {
                        userStories.add(result.get(i));
                    }
                }
                return userStories;
            }
            else
                return new ArrayList<UserFame>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
}
