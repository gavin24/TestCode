package com.ackerman.j.gavin.ispy.Services;

import com.ackerman.j.gavin.ispy.Domain.Image;

import java.util.ArrayList;

/**
 * Created by gavin.ackerman on 2016-06-22.
 */
public interface ImageService {
    Image addImage(Image animal);
    Image updateImage( Image animal);
    Image deleteImage( Image animal);
    Image getImage(Long d);
    ArrayList<Image> getAllImages( );
    void removeAllImages();
}
