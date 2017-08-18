package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Image;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public interface ImageFactory {

    Image createImage(Long id, long userId, String name,byte[] image);

}
