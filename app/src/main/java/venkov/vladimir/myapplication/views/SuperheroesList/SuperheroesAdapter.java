package venkov.vladimir.myapplication.views.SuperheroesList;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import venkov.vladimir.myapplication.R;
import venkov.vladimir.myapplication.models.Superhero;



public class SuperheroesAdapter extends RecyclerView.Adapter<SuperheroesAdapter.SuperheroViewHolder> {
    private List<Superhero> mSuperheroes;
    private OnSuperheroClickListener mOnSuperheroClickListener;

    @Inject
    public SuperheroesAdapter() {
        mSuperheroes = new ArrayList<>();
    }

    @NonNull
    @Override
    public SuperheroViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.superhero_item, parent, false);
        int height = parent.getMeasuredHeight() / 3;
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        lp.height = height;
        view.setLayoutParams(lp);
        view.setMinimumHeight(height);
        return new SuperheroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SuperheroViewHolder holder, int position) {
        holder.setOnClickListener(mOnSuperheroClickListener);
        holder.bind(mSuperheroes.get(position));
    }

    @Override
    public int getItemCount() {
        return mSuperheroes.size();
    }

    public Superhero getItem(int position) {
        return mSuperheroes.get(position);
    }

    public void clear() {
        mSuperheroes.clear();
    }

    public void addAll(List<Superhero> superheroes) {
        mSuperheroes.addAll(superheroes);
    }

    public void setOnSuperheroClickListener(OnSuperheroClickListener onSuperheroClickListener) {
        this.mOnSuperheroClickListener = onSuperheroClickListener;
    }

    public static class SuperheroViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_name)
        TextView mNameTextView;

        @BindView(R.id.tv_secret_identity)
        TextView mSecretIdentityTextView;

        @BindView(R.id.iv_superhero)
        ImageView mSuperheroImageView;
        private OnSuperheroClickListener mOnClickListener;
        private Superhero mSuperhero;

        SuperheroViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        void bind(Superhero superhero) {
            mNameTextView.setText(superhero.getName());
            mSecretIdentityTextView.setText(superhero.getSecretIdentity());
            Picasso.get()
                    .load(superhero.getImageUrl())
                    .into(mSuperheroImageView);
            mSuperhero = superhero;
        }

        @OnClick
        public void onClick() {
            mOnClickListener.onClick(mSuperhero);
        }

        public void setOnClickListener(OnSuperheroClickListener onClickListener) {
            mOnClickListener = onClickListener;
        }
    }

    interface OnSuperheroClickListener {
        void onClick(Superhero superhero);
    }
}
