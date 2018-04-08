package skill.azamat.mvpauth.di.components;

import android.content.Context;

import dagger.Component;
import skill.azamat.mvpauth.di.modules.AppModule;

/**
 * Created by Asus on 07.04.2018.
 */
@Component(modules = AppModule.class)
public interface AppComponent {
    Context getContext();

}
