package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class Subscriber {
    private long userid;
    private long id;
    private int status;
    private long subscriberuserid;

    private Subscriber() {
    }


    public long getUserId(){return userid;}
    public long getid() {
        return id;
    }
    public long getSubscriber(){return subscriberuserid;}
    public int getStatus(){return status;}

    public Subscriber(Subscriber.Builder builder){
        this.id=builder.id;

        this.userid=builder.userid;
        this.status=builder.status;

        this.subscriberuserid=builder.subscriberuserid;

    }

    public static class Builder {
        private long userid;
        private long id;
        private int status;
        private long subscriberuserid;


        public Subscriber.Builder subscriberuserid(long value) {
            this.subscriberuserid = value;
            return this;
        }


        public Subscriber.Builder id(long value) {
            this.id = value;
            return this;
        }




        public Subscriber.Builder userid(long value) {
            this.userid = value;
            return this;
        }

        public Subscriber.Builder status(int value) {
            this.status = value;
            return this;

        }


        public Subscriber.Builder copy(Subscriber value) {
            this.id = value.id;
            this.userid = value.userid;

            this.subscriberuserid = value.subscriberuserid;
            this.status = value.status;

            return this;
        }

        public Subscriber build() {
            return new Subscriber(this);
        }
    }
}
