package com.ackerman.j.gavin.ispy.Factory;

import com.ackerman.j.gavin.ispy.Domain.Story;
import com.ackerman.j.gavin.ispy.Domain.Subscriber;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public interface SubscriberFactory {
    Subscriber createSubscriber(Long id, int status, long userId, long subscriberuserid);
}
