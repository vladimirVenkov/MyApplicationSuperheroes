package venkov.vladimir.myapplication.diconfig;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesAdapter;

@Module
public class ViewsModule {
    @Provides
    public RecyclerView.Adapter<SuperheroesAdapter.SuperheroViewHolder> superheroArrayAdapter(Context context) {
        return new SuperheroesAdapter();
    }
}

