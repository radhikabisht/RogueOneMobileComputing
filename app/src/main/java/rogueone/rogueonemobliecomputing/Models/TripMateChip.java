package rogueone.rogueonemobliecomputing.Models;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

/**
 * Created by jayas on 6/10/2017.
 */

public class TripMateChip implements ChipInterface{
    private String TripMateName;
    private String PhoneNumber;

    public TripMateChip(String tripMateName, String phoneNumber) {
        TripMateName = tripMateName;
        PhoneNumber = phoneNumber;
    }

    public String getTripMateName() {
        return TripMateName;
    }

    public void setTripMateName(String tripMateName) {
        TripMateName = tripMateName;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }

    @Override
    public Object getId() {
        return null;
    }

    @Override
    public Uri getAvatarUri() {
        return null;
    }

    @Override
    public Drawable getAvatarDrawable() {
        return null;
    }

    @Override
    public String getLabel() {
        return null;
    }

    @Override
    public String getInfo() {
        return null;
    }
}
