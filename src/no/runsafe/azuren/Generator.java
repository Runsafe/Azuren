package no.runsafe.azuren;

import no.runsafe.azuren.populators.IceUnderlay;
import no.runsafe.azuren.populators.Spires;
import no.runsafe.azuren.populators.Surface;
import no.runsafe.azuren.populators.WorldFloor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SuppressWarnings("deprecation")
public class Generator extends ChunkGenerator
{
	@Override
	public List<BlockPopulator> getDefaultPopulators(World world)
	{
		List<BlockPopulator> poppers = new ArrayList<BlockPopulator>(1);
		poppers.add(new WorldFloor());
		poppers.add(new IceUnderlay());
		poppers.add(new Surface());
		poppers.add(new Spires());
		return poppers;
	}

	public Location getFixedSpawnLocation(World world, Random random)
	{
		if (!world.isChunkLoaded(0, 0))
			world.loadChunk(0, 0);
		if (world.getHighestBlockYAt(0, 0) <= 0 && world.getBlockAt(0, 0, 0).getType() == Material.AIR)
			return new Location(world, 0.0D, 64D, 0.0D);
		else
			return new Location(world, 0.0D, world.getHighestBlockYAt(0, 0), 0.0D);
	}
}