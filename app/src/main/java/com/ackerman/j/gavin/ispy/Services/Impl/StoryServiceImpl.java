package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Repositories.Impl.StoryRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.StoryRepository;
import com.ackerman.j.gavin.ispy.Services.StoryService;
import com.ackerman.j.gavin.ispy.Services.StoryService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class StoryServiceImpl extends Service implements StoryService {
    final private StoryRepository repository;

    private final IBinder localBinder = new StoryServiceLocalBinder();

    private static StoryServiceImpl service = null;

    public static StoryServiceImpl getInstance()
    {
        if(service == null)
            service = new StoryServiceImpl();
        return service;
    }

    public StoryServiceImpl()
    {
        repository = new StoryRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class StoryServiceLocalBinder extends Binder {
        public StoryServiceImpl getService() {
            return StoryServiceImpl.this;
        }
    }


    @Override
    public Story addStory(Story animal) {
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
    public Story deleteStory(Story animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Story> getAllStorys() {
        try {
            ArrayList<Story> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Story>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllStorys() {
        repository.deleteAll();
    }
    @Override
    public Story updateStory(Story animal) {
        return repository.update(animal);
    }

    @Override
    public Story getStory(Long Id) {
        return repository.findById(Id);
    }

    @Override
    public ArrayList<Story> getAllStorys(Long id) {
        try {
            ArrayList<Story> userStories = new ArrayList<>();
            ArrayList<Story> result = new ArrayList<>();
            if (result.addAll(repository.findAll())) {
                for (int i = 0; i < result.size(); i++) {
                    if (result.get(i).getuserId() == id) {
                        userStories.add(result.get(i));
                    }
                }
                return userStories;
            }
            else
                return new ArrayList<Story>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }
}
