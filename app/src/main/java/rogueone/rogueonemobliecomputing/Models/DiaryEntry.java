package rogueone.rogueonemobliecomputing.Models;

import java.io.Serializable;
import java.util.List;

/**
 * Created by jayas on 4/10/2017.
 */

public class DiaryEntry implements Serializable {
    public long DiaryEntryID;
    public String DateCreated;
    public String DisplayFriendlyName;
    public double Latitude;
    public double Longitude;
    public List<String> BadgeNames;
    public String Description;
}
