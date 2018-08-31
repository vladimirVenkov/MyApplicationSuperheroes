package venkov.vladimir.myapplication.views.SuperheroesList;

import java.util.List;

import venkov.vladimir.myapplication.models.Superhero;

public interface SuperheroesListContracts {
    interface View {
        void setPresenter(Presenter presenter);

        void showSuperheroes(List<Superhero> superheroes);

        void showEmptySuperheroesList();

        void showError(Exception e);

        void showLoading();

        void hideLoading();

        void showSuperheroDetails(Superhero superhero);
    }

    interface Presenter {
        void subscribe(View view);

        void loadSuperheroes();

        void filterSuperheroes(String pattern);

        void selectSuperhero(Superhero superhero);
    }

    interface Navigator {
        void navigateWith(Superhero superhero);
    }

}
