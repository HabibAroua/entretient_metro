package com.example.app_mobile.route;

public class Route
{
    private static final String PROTOCOL="http://";
    private static final String ADDRESS="192.168.1.5";
    public  static final String URL=PROTOCOL+ADDRESS+"/webservice/Public/";
    public  static final String URL_LOGIN=URL+"index.php";
    public  static final String URL_ENTRETIRN=URL+"consulter_entretien.php?login=";
    public  static final String URL_CARREFOUR=URL+"listCarrefour.php?login=";
}