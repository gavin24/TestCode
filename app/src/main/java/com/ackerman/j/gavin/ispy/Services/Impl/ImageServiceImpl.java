package com.ackerman.j.gavin.ispy.Services.Impl;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.ackerman.j.gavin.ispy.Config.Util.App;
import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Repositories.Impl.ImageRepositoryImpl;
import com.ackerman.j.gavin.ispy.Repositories.ImageRepository;
import com.ackerman.j.gavin.ispy.Services.ImageService;
import com.ackerman.j.gavin.ispy.Services.ImageService;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public class ImageServiceImpl extends Service implements ImageService {
    final private ImageRepository repository;

    private final IBinder localBinder = new ImageServiceLocalBinder();

    private static ImageServiceImpl service = null;

    public static ImageServiceImpl getInstance()
    {
        if(service == null)
            service = new ImageServiceImpl();
        return service;
    }

    public ImageServiceImpl()
    {
        repository = new ImageRepositoryImpl();
    }

    @Override
    public IBinder onBind(Intent intent) {
        return localBinder;
    }

    public class ImageServiceLocalBinder extends Binder {
        public ImageServiceImpl getService() {
            return ImageServiceImpl.this;
        }
    }


    @Override
    public Image addImage(Image animal) {
            return repository.save(animal);
    }
    @Override
    public Image deleteImage(Image animal) {
        return repository.delete(animal);
    }

    @Override
    public ArrayList<Image> getAllImages() {
        try {
            ArrayList<Image> result = new ArrayList<>();
            if (result.addAll(repository.findAll()))
                return result;
            else
                return new ArrayList<Image>();
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
    public Image updateImage(Image animal) {
        return repository.update(animal);
    }

    @Override
    public Image getImage(Long Id) {
        return repository.findById(Id);
    }


}
