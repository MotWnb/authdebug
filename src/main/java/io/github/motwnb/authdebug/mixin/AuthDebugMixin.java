package io.github.motwnb.authdebug.mixin;

import io.github.motwnb.authdebug.AuthDebug;
import net.minecraft.client.multiplayer.ClientHandshakePacketListenerImpl;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ClientHandshakePacketListenerImpl.class)
public class AuthDebugMixin {

    @Inject(
            method = "authenticateServer",
            at = @At("HEAD")
    )
    private void logAuthStart(String digest, CallbackInfoReturnable<Component> cir) {
        AuthDebug.LOGGER.info("[AUTH] authenticateServer START | digest='{}'", digest);
    }

    @Inject(
            method = "authenticateServer",
            at = @At("RETURN")
    )
    private void logAuthEnd(String digest, CallbackInfoReturnable<Component> cir) {
        Component result = cir.getReturnValue();
        if (result != null) {
            AuthDebug.LOGGER.error("[AUTH] authenticateServer FAILED | digest='{}' | result={}", digest, result.getString());
        } else {
            AuthDebug.LOGGER.info("[AUTH] authenticateServer SUCCESS | digest='{}'", digest);
        }
    }
}