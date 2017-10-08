package rogueone.rogueonemobliecomputing;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.os.ResultReceiver;

/**
 * Created by jayas on 6/10/2017.
 */
public class AddressResultReceiver extends ResultReceiver {
    public String mAddressOutput;
    private Context mContext;
    private Receiver mReceiver;
    public AddressResultReceiver(Handler handler, Context context) {
        super(handler);
        mContext = context;
    }
    public interface Receiver {
        public void onReceiveResult(int resultCode, Bundle resultData);

    }
    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }
    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {

        // Display the address string
        // or an error message sent from the intent service.
        mAddressOutput = resultData.getString(Constants.RESULT_DATA_KEY);

        // Show a toast message if an address was found.
        if (resultCode == Constants.SUCCESS_RESULT) {
            if (mReceiver != null) {
                mReceiver.onReceiveResult(resultCode, resultData);
            }
        }

    }
}
