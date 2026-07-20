package io.github.motwnb.authdebug.mixin;

import com.mojang.authlib.exceptions.MinecraftClientException;
import io.github.motwnb.authdebug.AuthDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClientException.class)
public class MinecraftClientExceptionMixin {

    @Inject(
            method = "<init>(Lcom/mojang/authlib/exceptions/MinecraftClientException$ErrorType;Ljava/lang/String;)V",
            at = @At("RETURN")
    )
    private void logInit(MinecraftClientException.ErrorType type, String message, CallbackInfo ci) {
        if (type == MinecraftClientException.ErrorType.SERVICE_UNAVAILABLE) {
            AuthDebug.LOGGER.error("[AUTH] MinecraftClientException(SERVICE_UNAVAILABLE): {}", message);
        }
    }

    @Inject(
            method = "<init>(Lcom/mojang/authlib/exceptions/MinecraftClientException$ErrorType;Ljava/lang/String;Ljava/lang/Throwable;)V",
            at = @At("RETURN")
    )
    private void logInitWithCause(MinecraftClientException.ErrorType type, String message, Throwable cause, CallbackInfo ci) {
        if (type == MinecraftClientException.ErrorType.SERVICE_UNAVAILABLE) {
            AuthDebug.LOGGER.error("[AUTH] MinecraftClientException(SERVICE_UNAVAILABLE): {} | causeType={} | causeMessage={}",
                    message,
                    cause != null ? cause.getClass().getName() : "null",
                    cause != null ? cause.getMessage() : "null",
                    cause  // 堆栈会跟在日志后面
            );
        }
    }
}