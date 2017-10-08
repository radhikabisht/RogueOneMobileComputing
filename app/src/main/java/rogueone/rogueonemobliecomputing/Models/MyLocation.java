package rogueone.rogueonemobliecomputing.Models;

import java.io.Serializable;

/**
 * Created by jayas on 6/10/2017.
 */

public class MyLocation implements Serializable{
    public MyLocation(double lat,double lon){
        latitude = lat;
        longitude = lon;
    }
    public double latitude;
    public double longitude;
}
