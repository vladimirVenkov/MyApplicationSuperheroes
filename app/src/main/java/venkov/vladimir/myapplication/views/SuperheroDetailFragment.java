package venkov.vladimir.myapplication.views;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import venkov.vladimir.myapplication.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroDetailFragment extends Fragment {


    private String mSuperhero;
    private TextView mSuperheroTextView;

    public SuperheroDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_superhero_detail, container, false);
        mSuperheroTextView = view.findViewById(R.id.tv_superhero);
        mSuperheroTextView.setText(mSuperhero);

        return view;
    }

    public static SuperheroDetailFragment newInstance() {
        return new SuperheroDetailFragment();
    }

    public void setSuperhero(String superhero) {
        this.mSuperhero = superhero;
        if (mSuperheroTextView == null) {
            return;
        }
        mSuperheroTextView.setText(superhero);
    }
}
