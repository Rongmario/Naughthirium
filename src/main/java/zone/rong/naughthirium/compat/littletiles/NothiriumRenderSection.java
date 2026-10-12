package zone.rong.naughthirium.compat.littletiles;

import com.creativemd.littletiles.client.render.entity.LittleRenderChunk;
import com.creativemd.littletiles.common.tileentity.TileEntityLittleTiles;
import meldexun.nothirium.api.renderer.chunk.IRenderChunkProvider;
import meldexun.nothirium.mc.renderer.ChunkRenderManager;
import net.minecraft.client.Minecraft;
import net.minecraft.util.math.BlockPos;

/**
 * Stands in for the vanilla RenderChunk that LittleTiles tracks its pending tile entities against.
 * LittleTiles treats any LittleRenderChunk as a self-managed chunk, so it only calls the two overrides below.
 */
public class NothiriumRenderSection extends LittleRenderChunk {

    public NothiriumRenderSection(BlockPos sectionPos) {
        super(sectionPos);
    }

    @Override
    public void addRenderData(TileEntityLittleTiles te) { }

    @Override
    public void markCompleted() {
        Minecraft.getMinecraft().addScheduledTask(() -> {
            IRenderChunkProvider<?> provider = ChunkRenderManager.getProvider();
            if (provider != null) {
                provider.setDirty(this.pos.getX(), this.pos.getY(), this.pos.getZ());
            }
        });
    }

    // LittleTiles counts pending tile entities per chunk in a HashMap
    @Override
    public boolean equals(Object obj) {
        return obj instanceof NothiriumRenderSection && this.pos.equals(((NothiriumRenderSection) obj).pos);
    }

    @Override
    public int hashCode() {
        return this.pos.hashCode();
    }

}
