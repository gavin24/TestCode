package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserImage {
    private String name;
    private long id;

    private long userId;
    private byte[] image;

    private UserImage() {
    }


    public long getId() {
        return id;
    }
    public String getName(){return name;}


    public byte[] getImage(){return image;}
    public long getuserId(){return userId;}

    public UserImage(UserImage.Builder builder){
        this.id=builder.id;
        this.name=builder.name;

        this.image=builder.image;
        this.userId=builder.userId;

    }

    public static class Builder {
        private long id;
        private String name;

        private byte[] image;
        private long userId;


        public UserImage.Builder name(String value) {
            this.name = value;
            return this;
        }


        public UserImage.Builder id(long value) {
            this.id = value;
            return this;
        }

        public UserImage.Builder image(byte[] value) {
            this.image = value;
            return this;
        }

        public UserImage.Builder userId(long value) {
            this.userId = value;
            return this;
        }



        public UserImage.Builder copy(UserImage value) {
            this.id = value.id;
            this.name = value.name;
            this.image = value.image;

            this.userId = value.userId;

            return this;
        }

        public UserImage build() {
            return new UserImage(this);
        }
    }
}
