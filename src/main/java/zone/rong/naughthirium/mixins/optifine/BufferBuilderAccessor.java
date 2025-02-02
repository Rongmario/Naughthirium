package zone.rong.naughthirium.mixins.optifine;

import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.BitSet;

@Mixin(targets = "net.minecraft.client.renderer.BufferBuilder", remap = false)
@Pseudo
public interface BufferBuilderAccessor {

    @Dynamic
    @Accessor
    BitSet getAnimatedSprites();

}
