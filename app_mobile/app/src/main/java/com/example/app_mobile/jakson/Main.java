package com.example.app_mobile.jakson;

import android.content.Context;
import android.widget.Toast;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.app_mobile.view.Navigation;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.codehaus.jackson.map.ObjectMapper;

public class Main 
{
    public static String res,res1;

    public void main() 
    {
        /*
        try
        {

            List<JSON1> users = this.convert_json_to_java();
            System.out.println("New show");
            for (int i = 0; i < users.size(); i++)
            {
                System.out.println(users.get(i));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        */
    }

    public String getJSON_Entretien(String url, String login, Context c) throws Exception 
    {
        RequestQueue queue = Volley.newRequestQueue(c);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url + login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) 
                    {
                        Main.res = response;
                        /*

                         */
                    }
                },
                new Response.ErrorListener() 
                                                      {
                    @Override
                    public void onErrorResponse(VolleyError error) 
                    {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(postRequest);
        return Main.res;
    }

    public String getJSON_CARRFOUR(String url, String login, Context c) throws Exception
    {
        RequestQueue queue = Volley.newRequestQueue(c);
        StringRequest postRequest = new StringRequest(Request.Method.POST, url + login,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {
                        Main.res1 = response;
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error)
                    {

                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<String, String>();
                return params;
            }
        };
        queue.add(postRequest);
        return Main.res1;
    }

    public String convert_json_to_java() throws Exception
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            String url = "http://192.168.1.8/webservice/Public/consulter_entretien.php?login=SafaMiri";
            URL obj = new URL(url);
            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("User-Agent", "Mozilla/5.0");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }

            in.close();
            System.out.println(response.toString());
            List<JSON1> jsons = Arrays.asList(mapper.readValue(response.toString(), JSON1[].class));
            System.out.println("\nJSON array to List of objects");
            return response.toString();
        } catch (Exception e) {
            System.out.println("Error : " + e.getMessage());
            return null;
        }
    }

    public List<JSON1> getListEntretien()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            return Arrays.asList(mapper.readValue(Main.res.toString(), JSON1[].class));
        }
        catch (Exception e) 
        {
            return null;
        }
    }

    public List<JSON2> getListCarrefour()
    {
        ObjectMapper mapper = new ObjectMapper();
        try
        {
            List<JSON2> jsons = Arrays.asList(mapper.readValue(Main.res1.toString(), JSON2[].class));
            return jsons;
        }
        catch (Exception e)
        {
            return null;
        }
    }
}
