package rogueone.rogueonemobliecomputing.Models;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by jayas on 4/10/2017.
 */

    public class LocationEntry implements Serializable {
        public long LocationEntryID = 0;
        public String Address;
        public String tripName;
        public String DateCreated = new Date().toString();
        public double Latitude;
        public double Longitude;
        public List<String> BadgeNames;
        public String Description;
        public String Comments;
    }