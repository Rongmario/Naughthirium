package zone.rong.naughthirium.mixins.optifine;

import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.List;

@Mixin(TextureMap.class)
public interface TextureMapAccessor {

    @Accessor
    List<TextureAtlasSprite> getListAnimatedSprites();

}
