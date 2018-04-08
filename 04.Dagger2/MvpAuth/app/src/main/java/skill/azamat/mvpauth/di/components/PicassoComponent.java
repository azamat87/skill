package skill.azamat.mvpauth.di.components;

import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import skill.azamat.mvpauth.di.modules.PicassoCacheModule;

/**
 * Created by Asus on 08.04.2018.
 */
@Component(dependencies = AppComponent.class, modules = PicassoCacheModule.class)
@Singleton
public interface PicassoComponent {
    Picasso getPicasso();
}
