package no.runsafe.azuren.populators;

import org.bukkit.Chunk;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

public abstract class SheetGenerator extends BlockPopulator
{
	protected void generateSheet(Chunk chunk, int y, int material, byte data)
	{
		for (int x = 0; x < 16; x++)
		{
			for (int z = 0; z < 16; z++)
			{
				Block block = chunk.getBlock(x, y, z);
				block.setTypeIdAndData(material, data, false);
			}
		}
	}
}
