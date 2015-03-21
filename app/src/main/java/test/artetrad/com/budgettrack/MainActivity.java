package test.artetrad.com.budgettrack;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity {
    Button mCurrentBudget;
    Button mBudgetHistory;
    Button mSettings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeButtons();
    }

    private void initializeButtons() {
        mCurrentBudget = (Button) findViewById(R.id.open_current_budget);
        mBudgetHistory = (Button) findViewById(R.id.budget_history);
        mSettings = (Button) findViewById(R.id.settings);

        mCurrentBudget.setAlpha(125);
        mBudgetHistory.setAlpha(125);
        mSettings.setAlpha(125);

        mCurrentBudget.setText("Display Current Budget:\n xx/xx/xx - yy/yy/yy");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
        }

        return super.onOptionsItemSelected(item);
    }

    public void buttonPicker(View view) {
        int id = view.getId();

        switch (id){
            case R.id.create_new_budget:
                Intent newBudget = new Intent(MainActivity.this, CreateNewBudgetActivity.class);
                startActivity(newBudget);
                break;
            case R.id.open_current_budget:
                Intent currentBudget = new Intent(MainActivity.this, CurrentBudgetActivity.class);
                startActivity(currentBudget);
                break;
            case R.id.budget_history:
                Toast.makeText(this, "Launch budget history activity", Toast.LENGTH_LONG).show();
                break;
            case R.id.settings:
                Toast.makeText(this, "Launch Settings activity", Toast.LENGTH_LONG).show();
                break;
        }
    }
}
