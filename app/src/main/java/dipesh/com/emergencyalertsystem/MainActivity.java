package dipesh.com.emergencyalertsystem;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<FeatureData> mFeatureList;
    FeatureData mFeatureData;

    private static MainActivity INSTANCE;
    public static MainActivity getInstance() {
        return INSTANCE;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        INSTANCE=this;
        setContentView(R.layout.activity_main);

        //setting up a toolbar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mRecyclerView = findViewById(R.id.recyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(MainActivity.this, 2);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        mFeatureList = new ArrayList<>();

        mFeatureData = new FeatureData("Hotkey Service", R.drawable.hokey);
        mFeatureList.add(mFeatureData);

        mFeatureData = new FeatureData("Emergency Call",
                R.drawable.call);
        mFeatureList.add(mFeatureData);

        mFeatureData = new FeatureData("Blood On Request",
                R.drawable.blood);
        mFeatureList.add(mFeatureData);

        mFeatureData = new FeatureData("Violence Report",
                R.drawable.violence);
        mFeatureList.add(mFeatureData);

        mFeatureData = new FeatureData("Safety Tips",
                R.drawable.safety);
        mFeatureList.add(mFeatureData);

        mFeatureData = new FeatureData("Feedback",
                R.drawable.feedback);
        mFeatureList.add(mFeatureData);

        RecyclerViewAdapter myAdapter = new RecyclerViewAdapter(MainActivity.this, mFeatureList);
        mRecyclerView.setAdapter(myAdapter);


    }
    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}
