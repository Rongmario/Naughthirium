package zone.rong.naughthirium.mixins.nothirium;

import com.llamalad7.mixinextras.injector.v2.WrapWithCondition;
import meldexun.nothirium.api.renderer.chunk.ChunkRenderPass;
import meldexun.nothirium.mc.renderer.chunk.MinecraftChunkRenderer;
import meldexun.nothirium.mc.renderer.chunk.RenderChunk;
import meldexun.nothirium.renderer.chunk.AbstractChunkRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(value = MinecraftChunkRenderer.class, remap = false)
public abstract class MinecraftChunkRendererMixin extends AbstractChunkRenderer<RenderChunk> {

    @Inject(method = "render", at = @At("HEAD"), cancellable = true)
    private void shouldRender(ChunkRenderPass pass, CallbackInfo ci) {
        if (this.renderedChunks(pass) <= 0) {
            ci.cancel();
        }
    }

}
