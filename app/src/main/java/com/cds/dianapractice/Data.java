package com.cds.dianapractice;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by fazal on 7/7/2017.
 */

public class Data {
    private String id;

    private String zip_code;

    private String state;

    private String city;

    public String getId ()
    {
        return id;
    }

    public void setId (String id)
    {
        this.id = id;
    }

    public String getZip_code ()
    {
        return zip_code;
    }

    public void setZip_code (String zip_code)
    {
        this.zip_code = zip_code;
    }

    public String getState ()
    {
        return state;
    }

    public void setState (String state)
    {
        this.state = state;
    }

    public String getCity ()
    {
        return city;
    }

    public void setCity (String city)
    {
        this.city = city;
    }
}
