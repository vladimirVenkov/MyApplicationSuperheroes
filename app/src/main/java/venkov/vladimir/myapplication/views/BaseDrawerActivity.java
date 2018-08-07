package venkov.vladimir.myapplication.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.views.SuperheroCreate.SuperheroCreateActivity;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListActivity;

public abstract class BaseDrawerActivity extends AppCompatActivity{

    public void setupDrawer() {
        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem listSuperheroesItem = new PrimaryDrawerItem()
                .withIdentifier(SuperheroesListActivity.IDENTIFIER)
                .withName("Superheroes");
        PrimaryDrawerItem createSuperheroesItem = new PrimaryDrawerItem()
                .withIdentifier(SuperheroCreateActivity.IDENTIFIER)
                .withIcon(R.drawable.common_full_open_on_phone)
                .withName("Create");

//create the drawer and remember the `Drawer` result object
        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(getDrawerToolbar())
                .addDrawerItems(
                        listSuperheroesItem,
                        new DividerDrawerItem(),
                        createSuperheroesItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        long identifier = drawerItem.getIdentifier();
                        if (getIdentifier() == identifier) {
                            return false;
                        }
                            Intent intent = getNextIntent(identifier);
                        if (intent == null) {
                            return false;
                        }

                        startActivity(intent);
                        return true;
                    }
                })
                .build();
    }
//TODO 1:22:01
    private Intent getNextIntent(long identifier) {
        if (identifier == SuperheroesListActivity.IDENTIFIER){
            return new Intent(BaseDrawerActivity.this,
                    SuperheroesListActivity.class);

        }else if(identifier == SuperheroCreateActivity.IDENTIFIER) {
            return new Intent(BaseDrawerActivity.this,
                    SuperheroCreateActivity.class);
        }

        return null;
    }

    protected abstract long getIdentifier();

    protected abstract Toolbar getDrawerToolbar();

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }
}
