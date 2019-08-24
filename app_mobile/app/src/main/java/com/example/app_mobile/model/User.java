package com.example.app_mobile.model;

public class User
{
    private String login;
    private String nom;
    private String prenom;
    private String password;

    public User(String login , String nom , String prenom , String password)
    {
        this.login=login;
        this.nom=nom;
        this.prenom=prenom;
        this.password=password;
    }

    public User()
    { }

    public String getLogin()
    {
        return this.login;
    }

    public void setLogin(String login)
    {
        this.login=login;
    }

    public String getNom()
    {
        return this.nom;
    }

    public void setNom(String nom)
    {
        this.nom=nom;
    }

    public String getPrenom()
    {
        return this.prenom;
    }

    public void setPrenom(String prenom)
    {
        this.prenom=prenom;
    }

    public String getPassword()
    {
        return this.password;
    }

    public void setPassword(String password)
    {
        this.password=password;
    }

}