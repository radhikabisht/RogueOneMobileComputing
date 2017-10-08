package rogueone.rogueonemobliecomputing.Models;

import java.io.Serializable;

/**
 * Created by jayas on 4/10/2017.
 */

public class TripMate implements Serializable{
    public long TripMateID=0;
    public String PhoneNumber;
    public String Name;

    public TripMate(String name, String phoneNumber) {
        Name = name;
        PhoneNumber = phoneNumber;
    }

}
