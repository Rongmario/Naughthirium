package zone.rong.naughthirium.mixins.optifine;

import com.llamalad7.mixinextras.sugar.Local;
import meldexun.nothirium.api.renderer.chunk.IChunkRenderer;
import meldexun.nothirium.api.renderer.chunk.IRenderChunkDispatcher;
import meldexun.nothirium.mc.renderer.chunk.RenderChunk;
import meldexun.nothirium.mc.renderer.chunk.RenderChunkTaskCompile;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunkTask;
import meldexun.nothirium.util.VisibilitySet;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.RegionRenderCacheBuilder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.naughthirium.compat.optifine.AnimatedSpritesHolder;

@Mixin(value = RenderChunkTaskCompile.class, remap = false)
public abstract class RenderChunkTaskCompileMixin extends AbstractRenderChunkTask<RenderChunk> {

    protected RenderChunkTaskCompileMixin(IChunkRenderer<?> chunkRenderer, IRenderChunkDispatcher taskDispatcher, RenderChunk renderChunk) {
        super(chunkRenderer, taskDispatcher, renderChunk);
    }

    @Inject(method = "lambda$compileSection$4",
            at = @At(value = "INVOKE",
                    target = "Lmeldexun/nothirium/mc/renderer/chunk/RenderChunk;" +
                            "setVBOPart(Lmeldexun/nothirium/api/renderer/chunk/ChunkRenderPass;" +
                            "Lmeldexun/nothirium/api/renderer/IVBOPart;)V", ordinal = 1))
    private void afterCompilingSection(VisibilitySet visSet, BufferBuilder[] bufferBuilders, RegionRenderCacheBuilder rrcb,
                     CallbackInfo ci, @Local BufferBuilder bufferBuilder) {
        ((AnimatedSpritesHolder) this.renderChunk).naughthirium$optifine$record(((BufferBuilderAccessor) bufferBuilder).getAnimatedSprites());
    }

}
