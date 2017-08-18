package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class ProfileInfo {
    private int age;
    private long id;
    private long userid;
    private String about;


    private ProfileInfo() {
    }

    public int getAge(){return age;}
    public long getId() {
        return id;
    }
    public long getUserId(){return userid;}
    public String getAbout(){return about;}


    public ProfileInfo(ProfileInfo.Builder builder){
        this.id=builder.id;
        this.age=builder.age;
        this.userid=builder.userid;
        this.about=builder.about;


    }

    public static class Builder {
        private int age;
        private long id;
        private long userid;
        private String about;



        public ProfileInfo.Builder userid(long value) {
            this.userid = value;
            return this;
        }


        public ProfileInfo.Builder id(long value) {
            this.id = value;
            return this;
        }

        public ProfileInfo.Builder age(int value) {
            this.age = value;
            return this;
        }


        public ProfileInfo.Builder about(String value) {
            this.about = value;
            return this;
        }



        public ProfileInfo.Builder copy(ProfileInfo value) {
            this.id = value.id;
            this.age = value.age;

            this.about = value.about;
            this.userid = value.userid;

            return this;
        }

        public ProfileInfo build() {
            return new ProfileInfo(this);
        }
    }
}
