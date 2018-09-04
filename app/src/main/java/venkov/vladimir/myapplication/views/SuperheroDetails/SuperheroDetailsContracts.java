package venkov.vladimir.myapplication.views.SuperheroDetails;

import venkov.vladimir.myapplication.models.Superhero;

public interface SuperheroDetailsContracts {
    interface View {
        void showSuperhero(Superhero superhero);

        void setPresenter(Presenter presenter);

        void showError(Throwable e);

        void showLoading();

        void hideLoading();
    }

    interface Presenter {
        void subscribe(View view);

        void loadSuperhero();

        void setSuperheroId(int id);
    }
}
