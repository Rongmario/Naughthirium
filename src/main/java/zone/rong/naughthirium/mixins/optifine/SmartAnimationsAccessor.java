package zone.rong.naughthirium.mixins.optifine;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.gen.Invoker;

import java.util.BitSet;

@Mixin(targets = "net.optifine.SmartAnimations", remap = false)
@Pseudo
public interface SmartAnimationsAccessor {

    @Accessor
    static BitSet getSpritesRendered() {
        throw new AssertionError("SmartAnimationsAccessor::getSpritesRendered @Accessor was not transformed successfully.");
    }

    @Invoker
    static boolean callIsActive() {
        throw new AssertionError("SmartAnimationsAccessor::callIsActive @Invoker was not transformed successfully.");
    }

    @Invoker
    static void callSpritesRendered(BitSet animatedSprites) {
        throw new AssertionError("SmartAnimationsAccessor::callSpriteRendered @Invoker was not transformed successfully.");
    }

}
