package zone.rong.naughthirium.mixins.optifine;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(targets = "net.optifine.CustomBlockLayers", remap = false)
@Pseudo
public interface CustomBlockLayersAccessor {

    @Invoker
    static BlockRenderLayer callGetRenderLayer(IBlockState blockState) {
        throw new AssertionError("CustomBlockLayersAccessor::callGetRenderLayer @Invoker was not transformed successfully.");
    }

}
