package no.runsafe.azuren.mining;

import no.runsafe.framework.api.IScheduler;

public class MiningHandler
{
	public MiningHandler(IScheduler scheduler)
	{
		this.scheduler = scheduler;
	}

	private void runChecks()
	{

	}

	private final IScheduler scheduler;
}
