package skill.azamat.mvpauth.di.components;

import javax.inject.Singleton;

import dagger.Component;
import skill.azamat.mvpauth.data.managers.DataManager;
import skill.azamat.mvpauth.di.modules.LocalModule;
import skill.azamat.mvpauth.di.modules.NetworkModule;

/**
 * Created by Asus on 08.04.2018.
 */
@Component(dependencies = AppComponent.class, modules = {NetworkModule.class, LocalModule.class})
@Singleton
public interface DataManagerComponent {

    void inject(DataManager dataManager);

}
