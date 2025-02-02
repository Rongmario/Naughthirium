package zone.rong.naughthirium;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.MetadataCollection;
import net.minecraftforge.fml.common.ModMetadata;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.FMLLaunchHandler;
import zone.rong.naughthirium.compat.chunkanimator.ChunkAnimatorEventListener;
import zone.rong.naughthirium.compat.optifine.DebugLinesEventListener;

import java.io.File;
import java.io.InputStream;
import java.util.Collections;
import java.util.jar.JarFile;
import java.util.zip.ZipEntry;

public class NaughthiriumModContainer extends DummyModContainer {

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

    public NaughthiriumModContainer() {
        super(loadMetadata());
    }

    @Override
    public File getSource() {
        return Naughthirium.source;
    }

    @Override
    public boolean shouldLoadInEnvironment() {
        return FMLLaunchHandler.side().isClient();
    }

    @Override
    public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
    }

    @Subscribe
    public void onPreInit(FMLPreInitializationEvent event) {
        if (Naughthirium.isChunkAnimatorInstalled) {
            ChunkAnimatorEventListener.register();
        }
        if (Naughthirium.isOptifineInstalled) {
            DebugLinesEventListener.register();
        }
    }

}
