package zone.rong.naughthirium.mixins.chunkanimator;

import meldexun.nothirium.mc.integration.ChunkAnimator;
import meldexun.nothirium.mc.renderer.chunk.RenderChunk;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = RenderChunk.class, remap = false)
public abstract class RenderChunkMixin extends AbstractRenderChunk {

    protected RenderChunkMixin(int sectionX, int sectionY, int sectionZ) {
        super(sectionX, sectionY, sectionZ);
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    private void onInit(CallbackInfo ci) {
        ChunkAnimator.onSetCoords(this);
    }

}
