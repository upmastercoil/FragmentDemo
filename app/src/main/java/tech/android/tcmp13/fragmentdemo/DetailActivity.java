package tech.android.tcmp13.fragmentdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //If this is the first run of the detail activity, init the detail FRAGMENT.
        //If it isn't the fragment manager knows how to put the contained fragments and their state
        //into the savedInstanceState so we can save ourselves the trouble....
        if (savedInstanceState == null) {
            String pet = getIntent().getStringExtra("pet");

            //With the fragment manager init the new fragment
            getFragmentManager().beginTransaction()
                    .add(R.id.detailFragmentContainer, PetDetailFragment.newInstance(pet))
                    .commit();
        }
    }
}
