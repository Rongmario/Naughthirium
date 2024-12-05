package zone.rong.naughthirium.mixins;

import meldexun.nothirium.api.renderer.chunk.ChunkRenderPass;
import meldexun.nothirium.renderer.chunk.AbstractChunkRenderer;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import meldexun.nothirium.util.collection.Enum2ObjMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(value = AbstractChunkRenderer.class, remap = false)
public interface AbstractChunkRendererAccessor<T extends AbstractRenderChunk> {

    @Accessor
    Enum2ObjMap<ChunkRenderPass, List<T>> getChunks();

}
