package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Domain.ProfileInfo;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Repositories.Impl.ImageRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.Impl.ProfileInfoRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.ProfileInfoRepository;
import com.ackerman.j.gavin.ispy.Services.ImageService;
import com.ackerman.j.gavin.ispy.Services.ProfileInfoService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ProfileInfoServiceImpl extends Service implements ProfileInfoService {
    final private ProfileInfoRepository repository;

    private final IBinder localBinder = new ProfileInfoServiceImpl.ProfileInfoServiceLocalBinder();

    private static ProfileInfoServiceImpl service = null;

    public static ProfileInfoServiceImpl getInstance()
    {
        if(service == null)
            service = new ProfileInfoServiceImpl();
        return service;
    }

    public ProfileInfoServiceImpl()
    {
        repository = new ProfileInfoRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ProfileInfoServiceLocalBinder extends Binder {
        public ProfileInfoServiceImpl getService() {
            return ProfileInfoServiceImpl.this;
        }
    }


    @Override
    public ProfileInfo addProfileInfo(ProfileInfo animal) {
        return repository.save(animal);
    }
    @Override
    public ProfileInfo deleteProfileInfo(ProfileInfo animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<ProfileInfo> getAllProfileInfo() {
        try {
            ArrayList<ProfileInfo> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<ProfileInfo>();
        } catch (Exception x) {
            x.printStackTrace();
        }
        return null;
    }

    @Override
    public void removeAllProfileInfo() {
        repository.deleteAll();
    }
    @Override
    public ProfileInfo updateProfileInfo(ProfileInfo animal) {
        return repository.update(animal);
    }

    @Override
    public ProfileInfo getProfileInfo(Long Id) {
        return repository.findById(Id);
    }

}
