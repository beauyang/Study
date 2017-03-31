package com.zzvcom.entity;

public class Author {
    private Integer id;

    private String username;

    private String password;

    private String email;

    private String bio;

    private String favouritesection;

    public Author(Integer id, String username) {
        this.id = id;
        this.username = username;
    }

    public Author(String username, String password, String email, String bio, String favouritesection) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.favouritesection = favouritesection;
    }

    public Author(Integer id, String username, String password, String email, String bio, String favouritesection) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.bio = bio;
        this.favouritesection = favouritesection;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio == null ? null : bio.trim();
    }

    public String getFavouritesection() {
        return favouritesection;
    }

    public void setFavouritesection(String favouritesection) {
        this.favouritesection = favouritesection == null ? null : favouritesection.trim();
    }

    @Override
    public String toString() {
        return "Author{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", bio='" + bio + '\'' +
                ", favouritesection='" + favouritesection + '\'' +
                '}';
    }
}