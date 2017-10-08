package rogueone.rogueonemobliecomputing.Models;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.pchmn.materialchips.ChipsInput;

import java.util.List;

import rogueone.rogueonemobliecomputing.Interfaces.APIClient;
import rogueone.rogueonemobliecomputing.R;

/**
 * Created by jayas on 6/10/2017.
 */

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder>{
        private List<LocationEntry> mDataset;
        private static APIClient client;
        private static Context mcontext;
        public TripAdapter(List<LocationEntry> myDataset, APIClient Contextclient, Context context) {
            mDataset = myDataset;
            client = Contextclient;
            mcontext = context;
        }

public class ViewHolder extends RecyclerView.ViewHolder {
    // each data item is just a string in this case
    public RelativeLayout mRelativeLayout;
    public TextView mAddressTextView;
    public TextView mFirstCheckInTextView;
    public ChipsInput mEntryTagsTextView;
    public TextView mComments;
    public TextView mCheckIns;
    public ViewHolder(RelativeLayout v) {
        super(v);
        mAddressTextView = (TextView) v.findViewById(R.id.address);
        mFirstCheckInTextView = (TextView) v.findViewById(R.id.first_checkin);
        mEntryTagsTextView = (ChipsInput) v.findViewById(R.id.entry_tags);
        mComments = (TextView) v.findViewById(R.id.comments);
        mCheckIns = (TextView) v.findViewById(R.id.all_checkins);
        mRelativeLayout = v;
    }
}
    public static void showErrorToast(Throwable t,Context context){
        Toast.makeText(context,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
    }
    @Override
    public TripAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.diary_view, parent,false);

        //set the view's size, margins, paddings and layout parameters...
        TripAdapter.ViewHolder vh = new TripAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(TripAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mAddressTextView.setText(mDataset.get(position).Address);
        holder.mFirstCheckInTextView.setText(mDataset.get(position).DateCreated);
        int no_of_checkins = mDataset.get(position).CheckIns.size();
        holder.mCheckIns.setText(Integer.toString(no_of_checkins));
        holder.mComments.setText(mDataset.get(position).Comments);
        if(mDataset.get(position).BadgeNames.size()==0){
            holder.mEntryTagsTextView.setVisibility(View.GONE);
        }else{
            for(String label:mDataset.get(position).BadgeNames){
                holder.mEntryTagsTextView.addChip(label,null);
            }
        }
    }

    @Override
    public int getItemCount() {return mDataset.size();}
}
