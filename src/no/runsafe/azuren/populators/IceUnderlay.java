package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class IceUnderlay extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		generateIceLayer(random, chunk, 63, 1.0F);
		generateIceLayer(random, chunk, 62, 0.6F);
		generateIceLayer(random, chunk, 61, 0.4F);
	}

	private void generateIceLayer(Random random, Chunk chunk, int y, float chance)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				Block block = chunk.getBlock(x, y, z);
				if (random.nextFloat() <= chance)
					block.setType(Material.PACKED_ICE);
				else
					block.setTypeIdAndData(Material.STAINED_CLAY.getId(), (byte) 3, false);
			}
		}
	}
}
