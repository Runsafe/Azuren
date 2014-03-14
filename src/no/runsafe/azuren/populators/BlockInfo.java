package no.runsafe.azuren.populators;

import org.bukkit.Material;

public class BlockInfo
{
	public BlockInfo(Material material)
	{
		this(material, (byte) 0);
	}

	public BlockInfo(Material material, byte data)
	{
		blockID = material.getId();
		blockData = data;
	}

	public int getBlockID()
	{
		return blockID;
	}

	public byte getBlockData()
	{
		return blockData;
	}

	private final int blockID;
	private final byte blockData;
}
