package venkov.vladimir.myapplication.views.SuperheroCreate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.views.BaseDrawerActivity;

public class SuperheroCreateActivity extends BaseDrawerActivity {
    public static final long IDENTIFIER = 284;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_superhero_create);
        mToolbar = findViewById(R.id.drawer_toolbar);
    }

    @Override
    protected long getIdentifier() {
        return IDENTIFIER;
    }

    @Override
    protected Toolbar getDrawerToolbar() {
        return mToolbar;
    }
}
