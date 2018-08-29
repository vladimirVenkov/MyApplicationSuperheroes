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

import venkov.vladimir.myapplication.AndroidApplication;
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
    private ArrayAdapter<String> mSuperheroesAdapter;
    private FirebaseFirestore mDb;
    private Repository<Superhero> mSuperheroesRepository;


    public SuperheroesListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_superheroes_list, container, false);


        mDb = FirebaseFirestore.getInstance();

        mSuperheroesListView = view.findViewById(R.id.lv_superheroes);
        mSuperheroesAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);

        mSuperheroesListView.setAdapter(mSuperheroesAdapter);
        mSuperheroesListView.setOnItemClickListener(this);

        mSuperheroesRepository =
                AndroidApplication.getRepository(
                        Superhero.class,
                        Superhero[].class
                );

//                AndroidApplication.getSuperheroRepository();

        mSuperheroesRepository.getAll(superheroes -> {
            getActivity()
                    .runOnUiThread(() -> {
                        for (Superhero superhero : superheroes) {
                            mSuperheroesAdapter.add(superhero.name);
                        }
                    });
        });
//        try {
//            loadSuperheroes();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

//        Repository<Power> powersRepository =
//                new FirebaseRepository<>(Power.class);

//
//        mDb.collection("superheros")
//                .get()
//                .addOnCompleteListener(task -> {
//                    List<Superhero> superheroes = task.getResult()
//                            .toObjects(Superhero.class);
//
//                    for (Superhero hero : superheroes) {
//                        mSuperheroesAdapter.add(hero.name);
//                    }
//                });
//
//        Superhero hero = new Superhero("Wonder Woman", "Diana Prince");
//        mDb.collection("superheros")
//                .add(hero);
//

        return view;
    }

//    private void loadSuperheroes() throws IOException {
//        OkHttpClient client = new OkHttpClient();
//        String url = "http://192.168.160.143:8080/api/superheroes";
//        Request request = new Request.Builder()
//                .get()
//                .url(url)
//                .build();
//
//        AsyncRunner.runInBackground(() -> {
//            Response response = null;
//            try {
//                response = client.newCall(request)
//                        .execute();
//                String body =
//                        response.body()
//                                .string();
//                Gson gson = new Gson();
//                Superhero[] superheroes =
//                        gson.fromJson(body, Superhero[].class);
//                int b = 5;
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//
//    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        String superhero = mSuperheroesAdapter.getItem(position);
        mNavigator.navigateWith(superhero);
    }

    public static SuperheroesListFragment newInstance() {
        return new SuperheroesListFragment();
    }

    public void setNavigator(Navigator navigator) {
        mNavigator = navigator;
    }

}
