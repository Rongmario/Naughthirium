package zone.rong.naughthirium;

import meldexun.matrixutil.UnsafeUtil;
import meldexun.nothirium.mc.vertex.ExtendedBufferBuilder;
import meldexun.nothirium.mc.vertex.VertexConsumer;
import sun.misc.Unsafe;
import zone.rong.loliasm.client.sprite.ondemand.IBufferPrimerConfigurator;

public class FloatVertexConsumer implements VertexConsumer {

    @Override
    public void tex(ExtendedBufferBuilder builder, double u, double v) {
        Unsafe unsafe = UnsafeUtil.UNSAFE;
        long address = builder.getAddress() + builder.getOffset();
        unsafe.putFloat(address, (float) u);
        unsafe.putFloat(address + 4, (float) v);
        ((IBufferPrimerConfigurator) builder).hookTexture((float) u, (float) v);
    }

    @Override
    public void lightmap(ExtendedBufferBuilder builder, int skyLight, int blockLight) {
        Unsafe unsafe = UnsafeUtil.UNSAFE;
        long address = builder.getAddress() + builder.getOffset();
        unsafe.putFloat(address, (float) skyLight);
        unsafe.putFloat(address + 4, (float) blockLight);
    }

}
