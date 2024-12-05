package zone.rong.naughthirium.mixins;

import meldexun.nothirium.api.renderer.chunk.RenderChunkTaskResult;
import meldexun.nothirium.mc.renderer.chunk.RenderChunk;
import meldexun.nothirium.mc.renderer.chunk.RenderChunkTaskCompile;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunkTask;
import net.minecraft.client.renderer.chunk.CompiledChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import zone.rong.loliasm.client.sprite.ondemand.IAnimatedSpritePrimer;
import zone.rong.loliasm.client.sprite.ondemand.ICompiledChunkExpander;

@Mixin(value = RenderChunkTaskCompile.class, remap = false)
public abstract class RenderChunkTaskCompileMixin extends AbstractRenderChunkTask<RenderChunk> {

    RenderChunkTaskCompileMixin() {
        super(null, null, null);
        throw new AssertionError("Mixin constructor should not be invoked");
    }

    @Inject(method = "compileSection(Lnet/minecraft/client/renderer/RegionRenderCacheBuilder;)Lmeldexun/nothirium/api/renderer/chunk/RenderChunkTaskResult;",
        at = @At(value = "NEW", target = "meldexun/nothirium/util/VisibilityGraph"))
    private void startCollectingVisibleTextures(CallbackInfoReturnable<RenderChunkTaskResult> cir) {
        IAnimatedSpritePrimer.CURRENT_COMPILED_CHUNK.set(new CompiledChunk());
    }

    @Inject(method = "compileSection(Lnet/minecraft/client/renderer/RegionRenderCacheBuilder;)Lmeldexun/nothirium/api/renderer/chunk/RenderChunkTaskResult;",
            at = @At(value = "INVOKE", target = "Lmeldexun/nothirium/util/VisibilityGraph;compute()Lmeldexun/nothirium/util/VisibilitySet;"))
    private void finishCollectingVisibleTextures(CallbackInfoReturnable<RenderChunkTaskResult> cir) {
        ((ICompiledChunkExpander) this.renderChunk).resolve(((ICompiledChunkExpander) IAnimatedSpritePrimer.CURRENT_COMPILED_CHUNK.get()).getVisibleTextures());
    }

}
