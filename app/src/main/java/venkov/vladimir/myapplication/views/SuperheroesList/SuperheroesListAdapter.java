package venkov.vladimir.myapplication.views.SuperheroesList;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;

public class SuperheroesListAdapter extends ArrayAdapter<Superhero> {
    private TextView mNameTextView;
    private TextView mSecretIdentityTextView;

    public SuperheroesListAdapter(@NonNull Context context) {
        super(context, -1);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.superhero_item, parent, false);
        }

        mNameTextView = view.findViewById(R.id.tv_name);
        mSecretIdentityTextView = view.findViewById(R.id.tv_secret_identity);
        Superhero superhero = getItem(position);
        mNameTextView.setText(superhero.getName());
        mSecretIdentityTextView.setText(superhero.getSecretIdentity());

        return view;
    }

}
