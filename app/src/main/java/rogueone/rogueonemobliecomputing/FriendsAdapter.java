package rogueone.rogueonemobliecomputing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import rogueone.rogueonemobliecomputing.Interfaces.APIClient;

/**
 * Created by jayas on 5/10/2017.
 */

class FriendsAdapter extends RecyclerView.Adapter<FriendsAdapter.ViewHolder> {
    private List<String> mDataset;
    private static APIClient client;
    private static Context mcontext;
    public FriendsAdapter(List<String> myDataset, APIClient Contextclient, Context context) {
        mDataset = myDataset;
        client = Contextclient;
        mcontext = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public RelativeLayout mRelativeLayout;
        public TextView mTextView;
        public Button button;
        public ViewHolder(RelativeLayout v) {
            super(v);
            button = (Button) v.findViewById(R.id.go_to_profile);
            mTextView = (TextView) v.findViewById(R.id.friend);
            /*button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String Username = mTextView.getText().toString();
                    Call<ResponseBody> call = client.ConfirmRequest(Username);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                            if(response.isSuccessful()){
                                Toast.makeText(mcontext,"Accepted successfully",Toast.LENGTH_LONG).show();
                            }else {
                                showErrorToast(new Throwable("Error occured while accepting the request"),mcontext);
                            }
                        }
                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {
                            showErrorToast(t,mcontext);
                        }
                    });
                }
            });*/
            mRelativeLayout = v;
        }
    }
    public static void showErrorToast(Throwable t,Context context){
        Toast.makeText(context,t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
    }
    @Override
    public FriendsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        RelativeLayout v = (RelativeLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.friend_view, parent,false);

        //set the view's size, margins, paddings and layout parameters...
        FriendsAdapter.ViewHolder vh = new FriendsAdapter.ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(FriendsAdapter.ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        holder.mTextView.setText(mDataset.get(position));
    }

    @Override
    public int getItemCount() {return mDataset.size();}
}
