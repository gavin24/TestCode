package com.ackerman.j.gavin.ispy.Domain;

/**
 * Created by gavin.ackerman on 2016-06-21.
 */
public class User {
    private String name;
    private long id;
    private String surname;
    private String email;
    private String password;

    private User() {
    }


    public String getEmail(){return email;}
    public long getid() {
        return id;
    }
    public String getName(){return name;}
    public String getsurname(){return surname;}


    public String getpassword(){return password;}

    public User(Builder builder){
        this.id=builder.id;
        this.name=builder.name;
        this.surname=builder.surname;
        this.email=builder.email;

        this.password=builder.password;

    }

    public static class Builder {
        private long id;
        private String name;
        private String surname;
        private String email;

        private String password;


        public Builder name(String value) {
            this.name = value;
            return this;
        }


        public Builder id(long value) {
            this.id = value;
            return this;
        }

        public Builder surname(String value) {
            this.surname = value;
            return this;
        }


        public Builder password(String value) {
            this.password = value;
            return this;
        }

        public Builder email(String value) {
            this.email = value;
            return this;

        }


        public Builder copy(User value) {
            this.id = value.id;
            this.name = value.name;

            this.email = value.email;
            this.password = value.password;

            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
