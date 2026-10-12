package zone.rong.naughthirium.mixins.littletiles;

import com.creativemd.creativecore.client.rendering.model.BufferBuilderUtils;
import com.creativemd.littletiles.client.render.cache.ChunkBlockLayerCache;
import com.creativemd.littletiles.common.tileentity.TileEntityLittleTiles;
import com.llamalad7.mixinextras.sugar.Local;
import meldexun.nothirium.mc.renderer.chunk.RenderChunkTaskCompile;
import meldexun.nothirium.util.VisibilityGraph;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderChunkTaskCompile.class, remap = false)
public class RenderChunkTaskCompileMixin {

    @Shadow @Final private IBlockAccess chunkCache;

    // LittleTiles builds each tile entity's geometry on its own threads, vanilla appends it when uploading the chunk
    @Inject(method = "renderBlockState",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraftforge/client/ForgeHooksClient;setRenderLayer(Lnet/minecraft/util/BlockRenderLayer;)V",
                    ordinal = 1))
    private void appendLittleTiles(IBlockState blockState, BlockPos pos, VisibilityGraph visibilityGraph, RegionRenderCacheBuilder bufferBuilderPack,
                                   CallbackInfo ci, @Local BlockRenderLayer layer, @Local BufferBuilder bufferBuilder) {
        if (!blockState.getBlock().hasTileEntity(blockState)) {
            return;
        }
        TileEntity tileEntity = this.chunkCache.getTileEntity(pos);
        if (!(tileEntity instanceof TileEntityLittleTiles)) {
            return;
        }
        TileEntityLittleTiles te = (TileEntityLittleTiles) tileEntity;
        if (!te.hasLoaded()) {
            return;
        }
        if (layer == BlockRenderLayer.SOLID) {
            te.updateQuadCache(null);
        }
        ChunkBlockLayerCache cache = new ChunkBlockLayerCache(layer.ordinal());
        cache.add(te.render, te.render.getBufferCache().get(layer.ordinal()));
        if (cache.expanded() > 0) {
            // CreativeCore's growBufferSmall swaps the buffer behind Nothirium's cached address
            BufferBuilderUtils.growBuffer(bufferBuilder, cache.expanded());
            cache.fillBuilder(bufferBuilder);
        }
    }

}
