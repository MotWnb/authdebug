package io.github.motwnb.authdebug.mixin;

import com.mojang.authlib.yggdrasil.YggdrasilMinecraftSessionService;
import io.github.motwnb.authdebug.AuthDebug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.UUID;

@Mixin(YggdrasilMinecraftSessionService.class)
public class YggdrasilSessionServiceMixin {

	@Inject(
			method = "joinServer(Ljava/util/UUID;Ljava/lang/String;Ljava/lang/String;)V",
			at = @At("HEAD")
	)
	private void logJoinServer(UUID profileId, String authenticationToken, String serverId, CallbackInfo ci) {
		AuthDebug.LOGGER.info("[AUTH] joinServer START | profileId={} | serverId='{}' | serverIdLength={} | tokenPrefix={}",
				profileId,
				serverId,
				serverId != null ? serverId.length() : -1,
				authenticationToken != null ? authenticationToken.substring(0, Math.min(4, authenticationToken.length())) + "..." : "null"
		);
	}
}