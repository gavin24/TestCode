package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.Subscriber;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Repositories.Impl.ImageRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.Impl.SubscriberRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.SubscriberRepository;
import com.ackerman.j.gavin.ispy.Services.ImageService;
import com.ackerman.j.gavin.ispy.Services.SubscriberService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class SubscriberServiceImpl extends Service implements SubscriberService {
    final private SubscriberRepository repository;

    private final IBinder localBinder = new SubscriberServiceImpl.SubscriberServiceLocalBinder();

    private static SubscriberServiceImpl service = null;

    public static SubscriberServiceImpl getInstance()
    {
        if(service == null)
            service = new SubscriberServiceImpl();
        return service;
    }

    public SubscriberServiceImpl()
    {
        repository = new SubscriberRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class SubscriberServiceLocalBinder extends Binder {
        public SubscriberServiceImpl getService() {
            return SubscriberServiceImpl.this;
        }
    }


    @Override
    public Subscriber addSubscriber(Subscriber animal) {
        return repository.save(animal);
    }
    @Override
    public Subscriber deleteSubscriber(Subscriber animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Subscriber> getAllSubs() {
        try {
            ArrayList<Subscriber> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Subscriber>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllSubs() {
        repository.deleteAll();
    }
    @Override
    public Subscriber updateSubscriber(Subscriber animal) {
        return repository.update(animal);
    }

    @Override
    public Subscriber getSubscriber(Long Id) {
        return repository.findById(Id);
    }


}
