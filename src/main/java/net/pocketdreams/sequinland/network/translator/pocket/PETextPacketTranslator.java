package net.pocketdreams.sequinland.network.translator.pocket;

import com.flowpowered.network.Message;

import net.glowstone.net.message.play.game.IncomingChatMessage;
import net.pocketdreams.sequinland.glowstone.net.GlowPocketSession;
import net.pocketdreams.sequinland.glowstone.net.message.GamePacket;
import net.pocketdreams.sequinland.glowstone.net.message.TextPacket;
import net.pocketdreams.sequinland.network.translator.PEToPCTranslator;

public class PETextPacketTranslator implements PEToPCTranslator<GamePacket> {
    @Override
    public Message[] translate(GlowPocketSession session, GamePacket packet) {
        TextPacket pePacket = (TextPacket) packet;
        if (pePacket.message.startsWith("!")) {
            // Remember the super workaround from the pre-0.16 days?
            // Well, it is back since I'm dumb and I'm lazy to properly implement the available commands packet!
            pePacket.message = pePacket.message.replaceFirst("!", "/");
        }
        IncomingChatMessage pcPacket = new IncomingChatMessage(pePacket.message);
        return new Message[] { pcPacket };
    }
}
