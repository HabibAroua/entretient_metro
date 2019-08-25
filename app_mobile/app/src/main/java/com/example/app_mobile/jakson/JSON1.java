package com.example.app_mobile.jakson;

public class JSON1
{
    private String id;
    private String nom_carrefour;
    private String annee;
    private String mois;
    private String semaine;

    public void setId(String id)
    {
        this.id = id;
    }

    public void setNom_carrefour(String nom_carrefour)
    {
        this.nom_carrefour = nom_carrefour;
    }

    public void setAnnee(String annee)
    {
        this.annee = annee;
    }

    public void setMois(String mois)
    {
        this.mois = mois;
    }

    public void setSemaine(String semaine)
    {
        this.semaine = semaine;
    }


    public String getId()
    {
        return id;
    }

    public String getNom_carrefour()
    {
        return nom_carrefour;
    }

    public String getAnnee()
    {
        return annee;
    }

    public String getMois()
    {
        return mois;
    }

    public String getSemaine()
    {
        return semaine;
    }


    public JSON1(String id, String nom_carrefour, String annee, String mois, String semaine)
    {
        this.id = id;
        this.nom_carrefour = nom_carrefour;
        this.annee = annee;
        this.mois = mois;
        this.semaine = semaine;
    }

    public JSON1()
    {}

    @Override
    public String toString() {
        return "JSON1{" +
                "id='" + id + '\'' +
                ", nom_carrefour='" + nom_carrefour + '\'' +
                ", annee='" + annee + '\'' +
                ", mois='" + mois + '\'' +
                ", semaine='" + semaine + '\'' +
                '}';
    }

}
