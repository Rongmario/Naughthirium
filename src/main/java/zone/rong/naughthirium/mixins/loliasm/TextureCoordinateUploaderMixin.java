package zone.rong.naughthirium.mixins.loliasm;

import meldexun.nothirium.mc.vertex.TextureCoordinateUploader;
import meldexun.nothirium.mc.vertex.VertexConsumer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import zone.rong.naughthirium.compat.loliasm.FloatVertexConsumer;

@Mixin(value = TextureCoordinateUploader.class, remap = false)
public class TextureCoordinateUploaderMixin {

    @Shadow @Mutable @Final private static VertexConsumer FLOAT = new FloatVertexConsumer();

}
