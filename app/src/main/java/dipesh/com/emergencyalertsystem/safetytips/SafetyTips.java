package dipesh.com.emergencyalertsystem.safetytips;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

import dipesh.com.emergencyalertsystem.R;

public class SafetyTips extends AppCompatActivity {

    RecyclerView mRecyclerView;
    List<TipsData> TipsList;
    TipsData tipsData;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_safety_tips);

        //setting up a toolbar
        Toolbar toolbar =  findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(android.support.v7.appcompat.R.drawable.abc_ic_ab_back_material);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView = findViewById(R.id.safetyrecyclerview);
        GridLayoutManager mGridLayoutManager = new GridLayoutManager(SafetyTips.this, 1);
        mRecyclerView.setLayoutManager(mGridLayoutManager);

        TipsList = new ArrayList<>();

        tipsData = new TipsData("What should a woman do " +
                "if she finds herself alone in the company of a " +
                "strange male as she prepares to enter a lift in a high-ri" +
                "se apartment late at night?", getString(R.string.one));
        TipsList.add(tipsData);

        tipsData = new TipsData("What to do if a stranger tries to attack you when you are alone in your house?",
                getString(R.string.two));
        TipsList.add(tipsData);

        tipsData = new TipsData("Taking an Auto or Taxi at Night.",
                getString(R.string.three));
        TipsList.add(tipsData);

        tipsData = new TipsData("What if the driver turns into a street he is not supposed to - and you feel you are entering a danger zone?",
                getString(R.string.four));
        TipsList.add(tipsData);

        tipsData = new TipsData("If you are stalked at night.",
                getString(R.string.five));
        TipsList.add(tipsData);


        RecyclerAdapter myAdapter = new RecyclerAdapter(SafetyTips.this, TipsList);
        mRecyclerView.setAdapter(myAdapter);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
