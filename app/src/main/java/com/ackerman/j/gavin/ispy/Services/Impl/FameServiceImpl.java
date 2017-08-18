package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Fame;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Repositories.Impl.FameRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.FameRepository;
import com.ackerman.j.gavin.ispy.Services.FameService;
import com.ackerman.j.gavin.ispy.Services.FameService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class FameServiceImpl extends Service implements FameService {
    final private FameRepository repository;

    private final IBinder localBinder = new FameServiceLocalBinder();

    private static FameServiceImpl service = null;

    public static FameServiceImpl getInstance()
    {
        if(service == null)
            service = new FameServiceImpl();
        return service;
    }

    public FameServiceImpl()
    {
        repository = new FameRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class FameServiceLocalBinder extends Binder {
        public FameServiceImpl getService() {
            return FameServiceImpl.this;
        }
    }


    @Override
    public Fame addFame(Fame animal) {
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
    public Fame deleteFame(Fame animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Fame> getAllFames() {
        try {
            ArrayList<Fame> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Fame>();
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
    public Fame updateFame(Fame animal) {
        return repository.update(animal);
    }

    @Override
    public Fame getFame(Long Id) {
        return repository.findById(Id);
    }
    @Override
    public Fame getFameByStoryId(Long Id) {
        return repository.findByStoryId(Id);
    }
    @Override
    public ArrayList<Fame> getAllFames(Long id) {
        try {
            ArrayList<Fame> userStories = new ArrayList<>();
            ArrayList<Fame> result = new ArrayList<>();
            if (result.addAll(repository.findAll())) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getUserId() == id) {
                        userStories.add(result.get(i));
                    }
                }
                return userStories;
            }
            else
                return new ArrayList<Fame>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
}
