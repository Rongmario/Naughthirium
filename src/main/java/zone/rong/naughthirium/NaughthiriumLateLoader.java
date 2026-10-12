package zone.rong.naughthirium;

import zone.rong.mixinbooter.Context;
import zone.rong.mixinbooter.ILateMixinLoader;

import java.util.Collections;
import java.util.List;

public class NaughthiriumLateLoader implements ILateMixinLoader {

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.naughthirium.late.json");
    }

    // Only this context lists mods that are not coremods
    @Override
    public boolean shouldMixinConfigQueue(Context context) {
        Naughthirium.isDimensionalEdiblesInstalled = context.isModPresent("dimensionaledibles");
        return true;
    }

}
