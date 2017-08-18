package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2016-08-14.
 */
public class UserTags {
    private String name;
    private long id;
    private long userId;

    private UserTags() {
    }

    public long getId() {
        return id;
    }
    public String getName(){return name;}
    public long getUserId(){return userId;}


    public UserTags(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.userId=builder.userId;

    }

    public static class Builder {
        private long id;
        private String name;
        private long userId;


        public Builder name(String value) {
            this.name = value;
            return this;
        }

        public Builder id(long value) {
            this.id = value;
            return this;
        }
        public Builder userid(long value) {
            this.userId = value;
            return this;
        }


        public Builder copy(UserTags value) {
            this.id = value.id;
            this.name = value.name;
            this.userId = value.userId;
            return this;
        }

        public UserTags build() {
            return new UserTags(this);
        }
    }
}
