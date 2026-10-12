package zone.rong.naughthirium.latemixins.littletiles;

import com.creativemd.littletiles.client.render.cache.RenderingThread;
import com.creativemd.littletiles.client.render.world.RenderUtils;
import com.creativemd.littletiles.common.tileentity.TileEntityLittleTiles;
import net.minecraft.client.renderer.ViewFrustum;
import net.minecraft.client.renderer.chunk.RenderChunk;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;
import org.spongepowered.asm.mixin.injection.Redirect;
import zone.rong.naughthirium.compat.littletiles.NothiriumRenderSection;

@Mixin(value = RenderingThread.class, remap = false)
public class RenderingThreadMixin {

    // Nothirium has no ViewFrustum to look a vanilla RenderChunk up in
    @Redirect(method = "addCoordToUpdate",
            at = @At(value = "INVOKE",
                    target = "Lcom/creativemd/littletiles/client/render/world/RenderUtils;" +
                            "getRenderChunk(Lnet/minecraft/client/renderer/ViewFrustum;Lnet/minecraft/util/math/BlockPos;)" +
                            "Lnet/minecraft/client/renderer/chunk/RenderChunk;"))
    private static RenderChunk skipVanillaRenderChunk(ViewFrustum frustum, BlockPos pos) {
        return null;
    }

    // The second store is the one for tile entities in the real world
    @ModifyVariable(method = "addCoordToUpdate", at = @At(value = "STORE", ordinal = 1), index = 2)
    private static Object useNothiriumRenderSection(Object chunk, TileEntityLittleTiles te) {
        return new NothiriumRenderSection(RenderUtils.getRenderChunkPos(te.getPos()));
    }

}
