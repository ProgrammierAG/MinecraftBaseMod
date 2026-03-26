package minecraft_base_mod.mobs;


import minecraft_base_mod.MinecraftBaseMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.PhantomRenderer;
import net.minecraft.client.renderer.entity.state.PhantomRenderState;
import net.minecraft.resources.Identifier;

public class LightBringerEntityRenderer extends PhantomRenderer {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(MinecraftBaseMod.MOD_ID, "textures/entity/light_bringer.png");

    public LightBringerEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public Identifier getTextureLocation(PhantomRenderState state) {
        return TEXTURE;
    }
}