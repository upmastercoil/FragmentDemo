package tech.android.tcmp13.fragmentdemo;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by tcmp13-t on 12/18/2016.
 */
public class PetDetailFragment extends Fragment {

    /**
     * As a workaround to no intent starting the fragment, and the STRONG recommendation to not override
     * the default constructor, build a factory method that will instantiate the fragment and will pass
     * the parameters as argument bundle object
     *
     * @param pet the information the fragment should display
     * @return the new instance of this fragment
     */
    public static PetDetailFragment newInstance(String pet) {

        //Instantiate the new fragment
        PetDetailFragment fragment = new PetDetailFragment();

        //init the argument object
        Bundle args = new Bundle();
        args.putString("pet", pet);

        //Tie the fragment and the bundle (arguments) together
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_pet_detail, container, false);
        TextView petDescription = (TextView) view.findViewById(R.id.petDescription);
        //The replacement for getIntent() is getArguments()
        petDescription.setText(getArguments().getString("pet"));
        return view;
    }

    /**
     * Will always return the pet as the arguments are instantiated at the factory method
     *
     * @return the displayed pet
     */
    public String getDisplayedPet() {

        return getArguments().getString("pet");
    }
}
