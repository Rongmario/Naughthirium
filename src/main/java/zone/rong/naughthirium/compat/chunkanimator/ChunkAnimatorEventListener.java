package zone.rong.naughthirium.compat.chunkanimator;

import meldexun.nothirium.mc.integration.ChunkAnimator;
import meldexun.nothirium.mc.renderer.ChunkRenderManager;
import meldexun.nothirium.renderer.chunk.AbstractRenderChunk;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.rong.naughthirium.mixins.chunkanimator.AbstractRenderChunkProviderAccessor;

public class ChunkAnimatorEventListener {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(ChunkAnimatorEventListener.class);
    }

    @SubscribeEvent
    public static void onClientPlayerJoin(EntityJoinWorldEvent event) {
        if (event.getEntity() == Minecraft.getMinecraft().player) {
            for (AbstractRenderChunk chunk : ((AbstractRenderChunkProviderAccessor) ChunkRenderManager.getProvider()).getChunks()) {
                ChunkAnimator.onSetCoords(chunk);
            }
        }
    }

}
