package venkov.vladimir.myapplication.views.SuperheroesList;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.firestore.FirebaseFirestore;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;
import venkov.vladimir.myapplication.repositories.FirebaseRepository;
import venkov.vladimir.myapplication.repositories.base.Repository;
import venkov.vladimir.myapplication.uiutils.Navigator;

/**
 * A simple {@link Fragment} subclass.
 */
public class SuperheroesListFragment extends Fragment implements AdapterView.OnItemClickListener {
    private Navigator mNavigator;
    private ListView mSuperheroesListView;
    private ArrayAdapter<String> mSuperHeroesAdapter;
    private FirebaseFirestore mDb;
    private Repository<Superhero> mSuperheroesRepository;

    public SuperheroesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_superheroes_list, container, false);

        mDb = FirebaseFirestore.getInstance();

        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperHeroesAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_expandable_list_item_1);

        mSuperheroesListView.setAdapter(mSuperHeroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);

        mSuperheroesRepository = new FirebaseRepository<>(Superhero.class);

        mSuperheroesRepository.add(new Superhero("Joro", "The Rabbit"),
                newSuperhero -> {
                    mSuperheroesRepository.getAll(superheroes -> {
                for (Superhero hero : superheroes) {
                mSuperHeroesAdapter.add(hero.name);
                        }
                    });
                } );



//        mSuperheroesRepository.getAll(superheroes -> {
//            for (Superhero hero : superheroes) {
//                mSuperHeroesAdapter.add(hero.name);
//            }
//        });


//        mSuperHeroesAdapter.add("Batman");
//        mSuperHeroesAdapter.add("Dreg");
//        mSuperHeroesAdapter.add("Fury");

        //.addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//@Override
// public void onComplete(@NonNull Task<QuerySnapshot> task) {
// List<Superhero> superheroes = task.getResult().toObjects(Superhero.class);
//        mDb.collection("superheros")
//                .get()
//                .addOnCompleteListener(task -> {
//                    List<Superhero> superheroes = task.getResult().toObjects(Superhero.class);
//                    for (Superhero hero : superheroes) {
//                        mSuperHeroesAdapter.add(hero.name);
//                    }
////                        for (DocumentSnapshot document : task.getResult()) {
////
////                        }
//                });
//        Superhero hero = new Superhero("Wonder Woman", "Diana Prince");
////        mDb.collection("superheros")
////                .add(hero);

        return view;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
        String superhero = mSuperHeroesAdapter.getItem(position);
        mNavigator.navigateWith(superhero);

    }

    public static SuperheroesListFragment newInstance() {
        return new SuperheroesListFragment();
    }

    public void setNavigator(Navigator navigator) {
            mNavigator = navigator;
    }
}
