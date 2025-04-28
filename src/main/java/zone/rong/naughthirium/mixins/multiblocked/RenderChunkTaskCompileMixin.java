package zone.rong.naughthirium.mixins.multiblocked;

import com.cleanroommc.multiblocked.persistence.MultiblockWorldSavedData;
import meldexun.nothirium.mc.renderer.chunk.RenderChunkTaskCompile;
import meldexun.nothirium.util.VisibilityGraph;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.util.math.BlockPos;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderChunkTaskCompile.class, remap = false)
public class RenderChunkTaskCompileMixin {

    @Inject(method = "renderBlockState", at = @At(value = "FIELD", opcode = Opcodes.GETSTATIC,
            target = "Lmeldexun/nothirium/util/Direction;ALL:[Lmeldexun/nothirium/util/Direction;"), cancellable = true)
    private void shouldRenderBlockState(IBlockState blockState, BlockPos pos, VisibilityGraph visibilityGraph, RegionRenderCacheBuilder bufferBuilderPack, CallbackInfo ci) {
        MultiblockWorldSavedData.isBuildingChunk.set(true);
        if (MultiblockWorldSavedData.isModelDisabled(pos)) {
            MultiblockWorldSavedData.isBuildingChunk.set(false);
            ci.cancel();
        }
    }

    @Inject(method = "renderBlockState", at = @At("TAIL"))
    private void clearIsBuildingChunkFlag(IBlockState blockState, BlockPos pos, VisibilityGraph visibilityGraph, RegionRenderCacheBuilder bufferBuilderPack, CallbackInfo ci) {
        MultiblockWorldSavedData.isBuildingChunk.set(false);
    }

}
