package skill.azamat.mvpauth.mvp.models;

import javax.inject.Inject;

import skill.azamat.mvpauth.data.managers.DataManager;
import skill.azamat.mvpauth.di.DaggerService;
import skill.azamat.mvpauth.di.components.DaggerModelComponent;
import skill.azamat.mvpauth.di.components.DataManagerComponent;
import skill.azamat.mvpauth.di.components.ModelComponent;
import skill.azamat.mvpauth.di.modules.ModelModule;

/**
 * Created by Asus on 08.04.2018.
 */

public abstract class AbstractModel {

    @Inject
    DataManager mDataManager;

    public AbstractModel() {
        ModelComponent component = DaggerService.getComponent(ModelComponent.class);
        if (component == null) {
            component = createDaggerComponent();
            DaggerService.registerComponent(DataManagerComponent.class, component);
        }
        component.inject(this);
    }

    private ModelComponent createDaggerComponent() {
        return DaggerModelComponent.builder()
                .modelModule(new ModelModule())
                .build();
    }
}
