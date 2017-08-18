package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2016-04-28.
 */
public class Tag {
    private String name;
    private long id;
    private String location;


    private Tag() {
    }


    public long getId() {
        return id;
    }
    public String getName(){return name;}
    public String getlocation(){return location;}




    public Tag(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.location=builder.location;

    }

    public static class Builder {
        private long id;
        private String name;
        private String location;


        public Builder name(String value) {
            this.name = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }
        public Builder location(String value) {
            this.location = value;
            return this;
        }




        public Builder copy(Tag value) {
            this.id = value.id;
            this.name = value.name;

            return this;
        }

        public Tag build() {
            return new Tag(this);
        }
    }
}
