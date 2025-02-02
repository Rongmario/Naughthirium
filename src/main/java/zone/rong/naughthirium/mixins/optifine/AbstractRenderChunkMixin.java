package zone.rong.naughthirium.mixins.optifine;

import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.naughthirium.compat.optifine.AnimatedSpritesHolder;

import java.util.BitSet;

@Mixin(value = AbstractRenderChunk.class, remap = false)
public class AbstractRenderChunkMixin implements AnimatedSpritesHolder {

    @Unique private final BitSet naughthirium$optifine$animatedSprites = new BitSet();

    @Override
    public void naughthirium$optifine$record(BitSet animatedSprites) {
        if (animatedSprites != null) {
            this.naughthirium$optifine$animatedSprites.or(animatedSprites);
        }
    }

    @Override
    public void naughthirium$optifine$load() {
        SmartAnimationsAccessor.callSpritesRendered(this.naughthirium$optifine$animatedSprites);
    }

    @Inject(method = "markDirty", at = @At("RETURN"))
    private void onMarkDirty(CallbackInfo ci) {
        this.naughthirium$optifine$animatedSprites.clear();
    }

}
