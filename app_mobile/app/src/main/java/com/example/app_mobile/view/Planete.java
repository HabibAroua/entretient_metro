package com.example.app_mobile.view;

public class Planete
{
    public String mNom;
    public int mDistance;

    Planete(String nom, int distance)
    {
        mNom = nom; // nom de la planète
        mDistance = distance; // distance au soleil en Gm
    }

    public String getNom()
    {
        return this.mNom;
    }

    public int getDistance()
    {
        return this.mDistance;
    }

    public String toString()
    {
        return mNom;
    }
}
