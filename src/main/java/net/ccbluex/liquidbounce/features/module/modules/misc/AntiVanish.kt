/*
 * FDPClient Hacked Client
 * A free open source mixin-based injection hacked client for Minecraft using Minecraft Forge by LiquidBounce.
 * https://github.com/UnlegitMC/FDPClient/
 */
package net.ccbluex.liquidbounce.features.module.modules.misc

import net.ccbluex.liquidbounce.LiquidBounce
import net.ccbluex.liquidbounce.event.EventTarget
import net.ccbluex.liquidbounce.event.PacketEvent
import net.ccbluex.liquidbounce.value.IntegerValue
import net.ccbluex.liquidbounce.features.module.Module
import net.ccbluex.liquidbounce.features.module.ModuleCategory
import net.ccbluex.liquidbounce.features.module.ModuleInfo
import net.ccbluex.liquidbounce.ui.client.hud.element.elements.Notification
import net.minecraft.network.play.server.S14PacketEntity
import net.minecraft.network.play.server.S1DPacketEntityEffect

@ModuleInfo(name = "AntiVanish", category = ModuleCategory.MISC, array = false, defaultOn = true)
class AntiVanish : Module() {
    private var lastNotify=-1L

    @EventTarget
    fun onPacket(event: PacketEvent){
        if (mc.theWorld == null || mc.thePlayer == null) return
        if(event.packet is S1DPacketEntityEffect){
            if(mc.theWorld.getEntityByID(event.packet.entityId)==null){
                vanish()
            }
        }else if(event.packet is S14PacketEntity){
            if(event.packet.getEntity(mc.theWorld)==null){
                vanish()
            }
        }
    }

    private fun vanish(){
        if((System.currentTimeMillis()-lastNotify)>5000){
            LiquidBounce.hud.addNotification(
                Notification("Found a vanished entity!", NotifyType.ERROR, 3000))
        }
        lastNotify=System.currentTimeMillis()

    }
}
