package zone.rong.naughthirium.compat.optifine;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import zone.rong.naughthirium.mixins.optifine.SmartAnimationsAccessor;
import zone.rong.naughthirium.mixins.optifine.TextureMapAccessor;

import java.util.ListIterator;

public class DebugLinesEventListener {

    public static void register() {
        MinecraftForge.EVENT_BUS.register(DebugLinesEventListener.class);
    }

    @SubscribeEvent
    public static void onRenderText(RenderGameOverlayEvent.Text event) {
        if (Minecraft.getMinecraft().gameSettings.showDebugInfo) {
            ListIterator<String> left = event.getLeft().listIterator();
            while (left.hasNext()) {
                String text = left.next();
                if (text.startsWith("Tile Entities:")) {
                    TextureMapAccessor textureMap = (TextureMapAccessor) Minecraft.getMinecraft().getTextureMapBlocks();
                    int totalAnimatedSprites = textureMap.getListAnimatedSprites().size();
                    StringBuilder textToAdd = new StringBuilder("Animated Sprites: ");
                    if (SmartAnimationsAccessor.callIsActive()) {
                        textToAdd.append(SmartAnimationsAccessor.getSpritesRendered().cardinality());
                        textToAdd.append(" / ");
                        textToAdd.append(totalAnimatedSprites);
                    } else {
                        textToAdd.append(totalAnimatedSprites);
                        textToAdd.append(" / ");
                        textToAdd.append(totalAnimatedSprites);
                    }
                    left.add(textToAdd.toString());
                    return;
                }
            }
        }
    }

}
