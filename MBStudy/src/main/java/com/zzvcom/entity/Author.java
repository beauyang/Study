package com.zzvcom.entity;

/**
 * Created by young on 2017/3/8.
 */
public class Author {
    private int id;
    private String username;
    private String password;
    private String email;
    private String bio;
    private String favouriteSection;

    public Author() {
    }

    public Author(String username, String password, String email, String bio, String favouriteSection) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.favouriteSection = favouriteSection;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getFavouriteSection() {
        return favouriteSection;
    }

    public void setFavouriteSection(String favouriteSection) {
        this.favouriteSection = favouriteSection;
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", favouriteSection='" + favouriteSection + '\'' +
                '}';
    }
}
