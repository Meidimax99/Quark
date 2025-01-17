package vazkii.quark.base.network.message.oddities;

import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraftforge.network.NetworkEvent;
import vazkii.arl.network.IMessage;
import vazkii.quark.addons.oddities.inventory.CrateMenu;

public class ScrollCrateMessage implements IMessage {

	private static final long serialVersionUID = -921358009630134620L;
	
	public boolean down;
	
	public ScrollCrateMessage() { }
	
	public ScrollCrateMessage(boolean down) {
		this.down = down;
	}
	
	@Override
	public boolean receive(NetworkEvent.Context context) {
		context.enqueueWork(() -> {
			ServerPlayer player = context.getSender();
			AbstractContainerMenu container = player.containerMenu;
			
			if(container instanceof CrateMenu)
				((CrateMenu) container).scroll(down, false);
		});
		
		return true;
	}

}
