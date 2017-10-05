package rogueone.rogueonemobliecomputing.Models;

import java.util.List;

/**
 * Created by jayas on 4/10/2017.
 */

public class Trip {
    public int TripID;
    public String Destination;
    public int PlannedDuration;
    public String StartDate;
    public List<DiaryEntry> TripEntries;
    public List<TripMate> TripMates;
    public String Description;
}