package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class Story {
    private String name;
    private long id;
    private String tag;
    private String text;
    private long userId;
    private long imageId;

    private Story() {
    }

    public String getTag(){return tag;}
    public long getId() {
        return id;
    }
    public String getName(){return name;}
    public String getText(){return text;}

    public long getImage(){return imageId;}
    public long getuserId(){return userId;}

    public Story(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.text=builder.text;
        this.tag=builder.tag;
        this.imageId=builder.imageId;
        this.userId=builder.userId;

    }

    public static class Builder {
        private long id;
        private String name;
        private String text;
        private String tag;
        private long imageId;
        private long userId;


        public Builder name(String value) {
            this.name = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }
        public Builder text(String value) {
            this.text = value;
            return this;
        }
        public Builder image(long value) {
            this.imageId = value;
            return this;
        }

        public Builder userId(long value) {
            this.userId = value;
            return this;
        }

        public Builder tag(String value) {
            this.tag = value;
            return this;

        }


        public Builder copy(Story value) {
            this.id = value.id;
            this.name = value.name;
            this.imageId = value.imageId;
            this.tag = value.tag;
            this.userId = value.userId;
            this.text = value.text;
            return this;
        }

        public Story build() {
            return new Story(this);
        }
    }
}
