package zone.rong.naughthirium.mixins.loliasm;

import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import zone.rong.loliasm.client.sprite.ondemand.ICompiledChunkExpander;

import java.util.Collections;
import java.util.Set;

@Mixin(value = AbstractRenderChunk.class, remap = false)
public class AbstractRenderChunkMixin implements ICompiledChunkExpander {

    @Unique private Set<TextureAtlasSprite> naughthirium$visibleTextures = Collections.emptySet();

    @Override
    public void resolve(Set<TextureAtlasSprite> visibleTextures) {
        if (visibleTextures == null) {
            return;
        }
        this.naughthirium$visibleTextures = visibleTextures;
    }

    @Override
    public Set<TextureAtlasSprite> getVisibleTextures() {
        return this.naughthirium$visibleTextures;
    }

    @Override
    public void resolve(TextureAtlasSprite sprite) { }

}
