package rogueone.rogueonemobliecomputing.Models;

import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.pchmn.materialchips.model.ChipInterface;

/**
 * Created by jayas on 6/10/2017.
 */

public class BadgeChip implements ChipInterface{
    private String label;
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
