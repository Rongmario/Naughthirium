package zone.rong.naughthirium.mixins.loliasm;

import meldexun.nothirium.api.renderer.chunk.IChunkRenderer;
import meldexun.nothirium.mc.renderer.ChunkRenderManager;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import zone.rong.loliasm.client.sprite.ondemand.IAnimatedSpriteActivator;
import zone.rong.loliasm.client.sprite.ondemand.ICompiledChunkExpander;

@Mixin(value = TextureMap.class, priority = 1001)
public abstract class TextureMapMixin extends AbstractTexture {

    @Inject(method = "updateAnimations", at = @At("HEAD"))
    private void setNothiriumSprites(CallbackInfo ci) {
        if ((Object) this == Minecraft.getMinecraft().getTextureMapBlocks()) {
            IChunkRenderer renderer = ChunkRenderManager.getRenderer();
            if (renderer != null) {
                ((AbstractChunkRendererAccessor<?>) renderer).getChunks().forEach(list -> {
                    for (AbstractRenderChunk chunk : list) {
                        for (TextureAtlasSprite sprite : ((ICompiledChunkExpander) chunk).getVisibleTextures()) {
                            ((IAnimatedSpriteActivator) sprite).setActive(true);
                        }
                    }
                });
            }
        }
    }

}
