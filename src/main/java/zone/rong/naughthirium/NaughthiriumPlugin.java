package zone.rong.naughthirium;

import org.objectweb.asm.tree.ClassNode;
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin;
import org.spongepowered.asm.mixin.extensibility.IMixinInfo;
import zone.rong.loliasm.config.LoliConfig;

import java.util.Collections;
import java.util.List;
import java.util.Set;

public class NaughthiriumPlugin implements IMixinConfigPlugin {

    @Override
    public void onLoad(String mixinPackage) { }

    @Override
    public String getRefMapperConfig() {
        return null;
    }

    @Override
    public boolean shouldApplyMixin(String targetClassName, String mixinClassName) {
        String moduleName = mixinClassName.substring("zone.rong.naughthirium.mixins.".length(), mixinClassName.lastIndexOf('.'));
        switch (moduleName) {
            case "loliasm":
                return Naughthirium.isLoliAsmInstalled && LoliConfig.instance.onDemandAnimatedTextures && !Naughthirium.isOptifineInstalled;
            case "multiblocked":
                return Naughthirium.isMultiblockedInstalled;
            case "optifine":
                return Naughthirium.isOptifineInstalled;
        }
        return true;
    }

    @Override
    public void acceptTargets(Set<String> myTargets, Set<String> otherTargets) { }

    @Override
    public List<String> getMixins() {
        return Collections.emptyList();
    }

    @Override
    public void preApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

    @Override
    public void postApply(String targetClassName, ClassNode targetClass, String mixinClassName, IMixinInfo mixinInfo) { }

}
