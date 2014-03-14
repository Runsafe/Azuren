package no.runsafe.azuren.mobs;

import no.runsafe.framework.api.event.plugin.IPluginEnabled;
import no.runsafe.framework.tools.nms.EntityRegister;

public class Register implements IPluginEnabled
{
	@Override
	public void OnPluginEnabled()
	{
		EntityRegister.registerEntity(Nightstalker.class, "nightstalker", 51);
	}
}
