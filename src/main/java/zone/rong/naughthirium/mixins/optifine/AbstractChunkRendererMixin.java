package zone.rong.naughthirium.mixins.optifine;

import meldexun.nothirium.renderer.chunk.AbstractChunkRenderer;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.naughthirium.compat.optifine.AnimatedSpritesHolder;

@Mixin(value = AbstractChunkRenderer.class, remap = false)
public class AbstractChunkRendererMixin {

    @Inject(method = "addToRenderLists",
            at = @At(value = "INVOKE",
                    target = "Lmeldexun/nothirium/util/collection/Enum2ObjMap;forEach(Ljava/util/function/BiConsumer;)V"))
    private void onAddToRenderList(AbstractRenderChunk renderChunk, CallbackInfo ci) {
        if (SmartAnimationsAccessor.callIsActive()) {
            ((AnimatedSpritesHolder) renderChunk).naughthirium$optifine$load();
        }
    }

}
