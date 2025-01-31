package zone.rong.naughthirium.mixins.chunkanimator;

import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunkProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(value = AbstractRenderChunkProvider.class, remap = false)
public interface AbstractRenderChunkProviderAccessor {

    @Accessor
    AbstractRenderChunk[] getChunks();

}
