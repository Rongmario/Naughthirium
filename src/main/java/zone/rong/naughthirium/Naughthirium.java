package zone.rong.naughthirium;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.mixinbooter.Context;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.io.File;
import java.util.*;

@IFMLLoadingPlugin.Name(Tags.MOD_NAME)
public class Naughthirium implements IFMLLoadingPlugin, IEarlyMixinLoader {

    static File source;
    static boolean isLoliAsmInstalled = false;
    static boolean isOptifineInstalled = false;
    static boolean isMultiblockedInstalled = false;

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "zone.rong.naughthirium.NaughthiriumModContainer";
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {
        source = (File) data.get("coremodLocation");
    }

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public List<String> getMixinConfigs() {
        return Collections.singletonList("mixins.naughthirium.json");
    }

    @Override
    public boolean shouldMixinConfigQueue(Context context) {
        if (context.isModPresent("loliasm")) {
            isLoliAsmInstalled = true;
        }
        if (context.isModPresent("optifine")) {
            isOptifineInstalled = true;
        }
        if (context.isModPresent("multiblocked")) {
            isMultiblockedInstalled = true;
        }
        return true;
    }

}
