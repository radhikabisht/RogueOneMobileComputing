package rogueone.rogueonemobliecomputing;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

public abstract class OptionsMenuActivity extends AppCompatActivity {
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }if(id == R.id.connect){
            return connectWithUsers();
        }if(id == R.id.friends){
            return showFriends();
        }if(id == R.id.pending){
            return showPending();
        }

        return super.onOptionsItemSelected(item);
    }
    protected abstract void startDialog();
    protected abstract void closeDialog();
    protected abstract void showErrorToast(Throwable t);
    protected abstract boolean showFriends();
    protected abstract boolean showPending();
    protected abstract boolean connectWithUsers();


}
