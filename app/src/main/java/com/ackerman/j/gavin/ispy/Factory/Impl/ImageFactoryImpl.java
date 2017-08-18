package com.ackerman.j.gavin.ispy.Factory.Impl;

import com.ackerman.j.gavin.ispy.Domain.Image;
import com.ackerman.j.gavin.ispy.Factory.ImageFactory;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class ImageFactoryImpl implements ImageFactory{
    private static ImageFactoryImpl factory = null;

    private ImageFactoryImpl(){

    }

    public static ImageFactoryImpl getInstance(){
        if (factory == null)
            factory = new ImageFactoryImpl();

        return factory;
    }


    public Image createImage(Long id, long userId, String name,byte[] image)
    {
        Image ticket =  new Image.Builder().id(id).name(name).userId(userId).image(image)
                .build();

        return ticket;
    }
}
