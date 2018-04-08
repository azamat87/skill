package skill.azamat.mvpauth.di.components;

import javax.inject.Singleton;

import dagger.Component;
import skill.azamat.mvpauth.di.modules.ModelModule;
import skill.azamat.mvpauth.mvp.models.AbstractModel;

/**
 * Created by Asus on 08.04.2018.
 */
@Component(modules = ModelModule.class)
@Singleton
public interface ModelComponent {

    void inject(AbstractModel abstractModel);

}
