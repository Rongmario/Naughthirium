package zone.rong.naughthirium.latemixins.dimensionaledibles;

import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.client.renderer.vertex.VertexFormat;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Redirect;

// The label's background quad is built from stale buffer data, it only looks right by luck without Nothirium
@Mixin(targets = "jackyy.dimensionaledibles.client.TileCustomCakeRenderer", remap = false)
@Pseudo
public class TileCustomCakeRendererMixin {

    // The renderer only writes position and colour
    @ModifyArg(method = {"render", "func_192841_a"},
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/BufferBuilder;" +
                            "begin(ILnet/minecraft/client/renderer/vertex/VertexFormat;)V", remap = true))
    private VertexFormat useWrittenVertexFormat(VertexFormat format) {
        return DefaultVertexFormats.POSITION_COLOR;
    }

    // The matrix is already translated to the cake
    @Redirect(method = {"render", "func_192841_a"},
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/BufferBuilder;" +
                            "pos(DDD)Lnet/minecraft/client/renderer/BufferBuilder;", remap = true))
    private BufferBuilder dropCameraOffset(BufferBuilder buffer, double posX, double posY, double posZ,
                                           @Local(argsOnly = true, ordinal = 0) double x,
                                           @Local(argsOnly = true, ordinal = 1) double y,
                                           @Local(argsOnly = true, ordinal = 2) double z) {
        return buffer.pos(posX - x, posY - y, posZ - z);
    }

}
