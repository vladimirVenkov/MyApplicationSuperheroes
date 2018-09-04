package venkov.vladimir.myapplication.diconfig;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;
import venkov.vladimir.myapplication.Constants;
import venkov.vladimir.myapplication.http.HttpRequester;
import venkov.vladimir.myapplication.http.OkHttpHttpRequester;


@Module
public class HttpModule {
    @Provides
    public HttpRequester httpRequester() {
        return new OkHttpHttpRequester();
    }

    @Provides
    @Named("baseServerUrl")
    public String baseServerUrl() {
        return Constants.BASE_SERVER_URL;
    }
}

