package skill.azamat.mvpauth;

import android.app.Application;

import skill.azamat.mvpauth.di.components.AppComponent;
import skill.azamat.mvpauth.di.components.DaggerAppComponent;
import skill.azamat.mvpauth.di.modules.AppModule;

/**
 * Created by Asus on 07.04.2018.
 */

public class App extends Application {

    private static AppComponent sAppComponent;


    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        createComponent();
    }

    private void createComponent() {
        sAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(getApplicationContext()))
                .build();
    }
}
