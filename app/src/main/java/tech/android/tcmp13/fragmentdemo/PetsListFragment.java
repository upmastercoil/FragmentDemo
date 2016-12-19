package tech.android.tcmp13.fragmentdemo;

import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tcmp13-t on 12/18/2016.
 */
public class PetsListFragment extends ListFragment {

    private static final String ONE_HUN_SIXTY_NINE = "one hundred sixty nine";

    private boolean dualPane;
    private int lastSelection;

    private List<String> pets;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        pets = generatePets();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), //The context, which is the containing activity
                android.R.layout.simple_list_item_activated_1, //the item layout
                pets);
        setListAdapter(adapter);

        //The containing activity knows about the detail fragment (container),
        //so to check the dual pane ask the activity if the container is instantiated and visible.
        View detailContainer = getActivity().findViewById(R.id.detailFragmentContainer);
        dualPane = detailContainer != null && detailContainer.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null)
            lastSelection = savedInstanceState.getInt(ONE_HUN_SIXTY_NINE);
        else
            lastSelection = 0;

        if (dualPane) {
            getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            showDetails(lastSelection);
        }
    }

    private List<String> generatePets() {

        ArrayList<String> pets = new ArrayList<>(400);
        for (int i = 0; i < 400; i++)
            pets.add("Chinchilla " + i);
        return pets;
    }

    private void showDetails(int lastSelection) {

        this.lastSelection = lastSelection;
        if (dualPane) {

            //Select the correct item (false to uncheck)
            getListView().setItemChecked(lastSelection, true);

            //The fragment manager manages the dynamic fragments, and just like the views, we can find the fragment
            //by the tag or the containing id (tag set at transaction time)
            PetDetailFragment detailFragment = (PetDetailFragment) getFragmentManager().findFragmentById(R.id.detailFragmentContainer);

            //If the detail fragment is null (the first time this fragment is displayed) or the selected pet
            //is different than the currently displayed pet, begin a transaction to replace the detail fragment.
            String selectedPet = pets.get(this.lastSelection);
            if (detailFragment == null || !detailFragment.getDisplayedPet().equals(selectedPet)) {
                detailFragment = PetDetailFragment.newInstance(selectedPet);

                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.detailFragmentContainer, detailFragment);
                transaction.commit();
            }
        } else {
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra("pet", pets.get(lastSelection));
            startActivity(intent);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        outState.putInt(ONE_HUN_SIXTY_NINE, lastSelection);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {

        showDetails(position);
    }
}
