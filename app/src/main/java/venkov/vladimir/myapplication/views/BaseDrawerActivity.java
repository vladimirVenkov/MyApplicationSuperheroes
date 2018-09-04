package venkov.vladimir.myapplication.views;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import butterknife.BindView;
import dagger.android.support.DaggerAppCompatActivity;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.views.SuperheroCreate.SuperheroCreateActivity;
import venkov.vladimir.myapplication.views.SuperheroesList.SuperheroesListActivity;

public abstract class BaseDrawerActivity extends DaggerAppCompatActivity {

    @BindView(R.id.drawer_toolbar)
    Toolbar mToolbar;

    public BaseDrawerActivity() {

    }

    public void setupDrawer() {
        PrimaryDrawerItem listSuperheroesItem = new PrimaryDrawerItem()
                .withIdentifier(SuperheroesListActivity.IDENTIFIER)
                .withName("Superheroes");

        PrimaryDrawerItem createSuperheroItem = new PrimaryDrawerItem()
                .withIdentifier(SuperheroCreateActivity.IDENTIFIER)
                .withIcon(android.R.drawable.btn_plus)
                .withName("Create superhero");

        Drawer drawer = new DrawerBuilder()
                .withActivity(this)
                .withToolbar(mToolbar)
                .addDrawerItems(
                        listSuperheroesItem,
                        new DividerDrawerItem(),
                        createSuperheroItem
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(
                            View view,
                            int position,
                            IDrawerItem drawerItem) {
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

    private Intent getNextIntent(long identifier) {
        if (identifier == SuperheroCreateActivity.IDENTIFIER) {
            return new Intent(this, SuperheroCreateActivity.class);
        }

        return null;
    }

    public Toolbar getToolbar() {
        return mToolbar;
    }

    protected abstract long getIdentifier();

    @Override
    protected void onStart() {
        super.onStart();
        setupDrawer();
    }
}