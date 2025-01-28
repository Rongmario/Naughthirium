package zone.rong.naughthirium;

import com.google.common.eventbus.EventBus;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;
import zone.rong.loliasm.config.LoliConfig;
import zone.rong.mixinbooter.Context;
import zone.rong.mixinbooter.IEarlyMixinLoader;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

@IFMLLoadingPlugin.Name(Tags.MOD_NAME)
public class Naughthirium implements IFMLLoadingPlugin, IEarlyMixinLoader {

    static File source;

    @Override
    public String[] getASMTransformerClass() {
        return new String[0];
    }

    @Override
    public String getModContainerClass() {
        return "zone.rong.naughthirium.Naughthirium$Container";
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

    public static class Container extends DummyModContainer {

        private static ModMetadata loadMetadata() {
            try (JarFile jar = new JarFile(Naughthirium.source)) {
                ZipEntry modInfo = jar.getEntry("mcmod.info");
                try (InputStream inputStream = jar.getInputStream(modInfo)) {
                    return MetadataCollection.from(inputStream, Naughthirium.source.getName()).getMetadataForId(Tags.MOD_ID, Collections.emptyMap());
                }
            } catch (Throwable t) {
                ModMetadata meta = new ModMetadata();
                meta.modId = Tags.MOD_ID;
                meta.name = Tags.MOD_NAME;
                meta.version = Tags.VERSION;
                meta.authorList.add("Rongmario");
                return meta;
            }
        }

        public Container() {
            super(loadMetadata());
        }

        @Override
        public boolean registerBus(EventBus bus, LoadController controller) {
            bus.register(this);
            return true;
        }

    }

}
