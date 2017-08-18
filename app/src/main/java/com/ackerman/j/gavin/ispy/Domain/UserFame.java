package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2017-05-24.
 */

public class UserFame {
    private int dislikes;
    private long id;
    private int likes;
    private int shares;
    private int views;
    private long storyId;
    private long userId;


    private UserFame() {
    }


    public int getDisLikes(){return dislikes;}
    public int getLikes(){return likes;}
    public long getId() {
        return id;
    }
    public long getStoryId(){return storyId;}
    public long getUserId(){return userId;}
    public int getViews(){return views;}

    public int getShares(){return shares;}

    public UserFame(UserFame.Builder builder){
        this.id=builder.id;
        this.shares=builder.shares;
        this.storyId=builder.storyId;
        this.likes=builder.likes;
        this.dislikes=builder.dislikes;
        this.userId=builder.userId;
        this.views=builder.views;
    }

    public static class Builder{
        private long id;
        private long storyId;
        private int shares;
        private int likes;
        private int dislikes;
        private long userId;
        private int views;

        public UserFame.Builder storyId(long value) {
            this.storyId = value;
            return this;
        }


        public UserFame.Builder id(long value)
        {
            this.id=value;
            return this;
        }
        public UserFame.Builder shares(int value)
        {
            this.shares=value;
            return this;
        }
        public UserFame.Builder views(int value)
        {
            this.views=value;
            return this;
        }




        public UserFame.Builder userId(long value)
        {
            this.userId=value;
            return this;
        }

        public UserFame.Builder likes(int value){
            this.likes=value;
            return this;

        }


        public UserFame.Builder dislikes(int value){
            this.dislikes=value;
            return this;

        }

        public UserFame.Builder copy(UserFame value){
            this.id=value.id;
            this.shares=value.shares;
            this.storyId=value.storyId;
            this.likes=value.likes;
            this.storyId=value.storyId;
            this.dislikes=value.dislikes;
            this.views=value.views;
            return this;
        }

        public UserFame build(){
            return new UserFame(this);
        }
    }
}
