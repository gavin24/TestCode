package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Tag;
import com.ackerman.j.gavin.ispy.Repositories.Impl.TagRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.TagRepository;
import com.ackerman.j.gavin.ispy.Services.TagService;
import com.ackerman.j.gavin.ispy.Services.TagService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class TagServiceImpl extends Service implements TagService {
    final private TagRepository repository;

    private final IBinder localBinder = new TagServiceLocalBinder();

    private static TagServiceImpl service = null;

    public static TagServiceImpl getInstance()
    {
        if(service == null)
            service = new TagServiceImpl();
        return service;
    }

    public TagServiceImpl()
    {
        repository = new TagRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class TagServiceLocalBinder extends Binder {
        public TagServiceImpl getService() {
            return TagServiceImpl.this;
        }
    }


    @Override
    public Tag addTag(Tag animal) {
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
    public Tag deleteTag(Tag animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Tag> getAllTags() {
        try {
            ArrayList<Tag> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Tag>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllTags() {
        repository.deleteAll();
    }
    @Override
    public Tag updateTag(Tag animal) {
        return repository.update(animal);
    }

    @Override
    public Tag getTag(Long Id) {
        return repository.findById(Id);
    }
}
