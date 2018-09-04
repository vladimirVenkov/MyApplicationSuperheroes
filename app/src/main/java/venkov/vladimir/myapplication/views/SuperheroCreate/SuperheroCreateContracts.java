package venkov.vladimir.myapplication.views.SuperheroCreate;

import venkov.vladimir.myapplication.models.Superhero;

public interface SuperheroCreateContracts {
    interface View {

        void setPresenter(Presenter presenter);

        void navigateToHome();

        void showError(Throwable throwable);

        void hideLoading();

        void showLoading();
    }

    interface Presenter {

        void subscribe(View view);

        void unsubscribe();

        void save(Superhero superhero);
    }

    public interface Navigator {

        void navigateToHome();
    }
}
