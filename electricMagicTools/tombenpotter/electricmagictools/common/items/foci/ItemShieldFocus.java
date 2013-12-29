package electricMagicTools.tombenpotter.electricmagictools.common.items.foci;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import thaumcraft.api.aspects.Aspect;
import thaumcraft.api.aspects.AspectList;
import thaumcraft.common.items.wands.ItemWandCasting;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricMagicTools.tombenpotter.electricmagictools.common.ElectricMagicTools;

public class ItemShieldFocus extends ItemBaseFocus {

	private static final AspectList visCost = new AspectList()
			.add(Aspect.ORDER, 10).add(Aspect.WATER, 10).add(Aspect.AIR, 10);

	public ItemShieldFocus(int id) {
		super(id);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister
				.registerIcon("electricmagictools:shieldfocus");
	}

	@Override
	public int getFocusColor() {
		return 555555555;
	}

	@Override
	public boolean isUseItem() {
		return true;
	}

	@Override
	public AspectList getVisCost() {
		return visCost;
	}

	@Override
	public String getSortingHelper(ItemStack itemstack) {
		return "SHIELD";
	}

	@Override
	public void onUsingFocusTick(ItemStack itemstack, EntityPlayer player,
			int time) {
		ItemWandCasting wand = (ItemWandCasting) itemstack.getItem();
		if (wand.consumeAllVis(itemstack, player, getVisCost(), true)) {
			int x = MathHelper.floor_double(player.posX);
			int y = MathHelper.floor_double(player.posY);
			int z = MathHelper.floor_double(player.posZ);

			if (wand.consumeAllVis(itemstack, player, getVisCost(), true)) {
				// Player Level
				player.worldObj.setBlock(x + 2, y, z,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 2, y, z + 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 2, y, z - 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x, y, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x, y, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 1, y, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 1, y, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 1, y, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 1, y, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y, z,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y, z + 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y, z - 1,
						ElectricMagicTools.shield.blockID);

				// 1 Above the Player
				player.worldObj.setBlock(x + 2, y + 1, z,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 2, y + 1, z + 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 2, y + 1, z - 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x, y + 1, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x, y + 1, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 1, y + 1, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 1, y + 1, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 1, y + 1, z + 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x + 1, y + 1, z - 2,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y + 1, z,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y + 1, z + 1,
						ElectricMagicTools.shield.blockID);
				player.worldObj.setBlock(x - 2, y + 1, z - 1,
						ElectricMagicTools.shield.blockID);
				
				 player.addPotionEffect(new PotionEffect(Potion.moveSlowdown.id, 100000, 100, false));
			}
		}
	}

	@Override
	public void onPlayerStoppedUsingFocus(ItemStack itemstack, World world,
			EntityPlayer player, int count) {
		int x = MathHelper.floor_double(player.posX);
		int y = MathHelper.floor_double(player.posY);
		int z = MathHelper.floor_double(player.posZ);

		// Player Level
		player.worldObj.setBlock(x + 2, y, z, 0);
		player.worldObj.setBlock(x + 2, y, z + 1, 0);
		player.worldObj.setBlock(x + 2, y, z - 1, 0);
		player.worldObj.setBlock(x, y, z + 2, 0);
		player.worldObj.setBlock(x, y, z - 2, 0);
		player.worldObj.setBlock(x - 1, y, z - 2, 0);
		player.worldObj.setBlock(x - 1, y, z + 2, 0);
		player.worldObj.setBlock(x + 1, y, z + 2, 0);
		player.worldObj.setBlock(x + 1, y, z - 2, 0);
		player.worldObj.setBlock(x - 2, y, z, 0);
		player.worldObj.setBlock(x - 2, y, z + 1, 0);
		player.worldObj.setBlock(x - 2, y, z - 1, 0);

		// 1 Above the Player
		player.worldObj.setBlock(x + 2, y + 1, z, 0);
		player.worldObj.setBlock(x + 2, y + 1, z + 1, 0);
		player.worldObj.setBlock(x + 2, y + 1, z - 1, 0);
		player.worldObj.setBlock(x, y + 1, z + 2, 0);
		player.worldObj.setBlock(x, y + 1, z - 2, 0);
		player.worldObj.setBlock(x - 1, y + 1, z - 2, 0);
		player.worldObj.setBlock(x - 1, y + 1, z + 2, 0);
		player.worldObj.setBlock(x + 1, y + 1, z + 2, 0);
		player.worldObj.setBlock(x + 1, y + 1, z - 2, 0);
		player.worldObj.setBlock(x - 2, y + 1, z, 0);
		player.worldObj.setBlock(x - 2, y + 1, z + 1, 0);
		player.worldObj.setBlock(x - 2, y + 1, z - 1, 0);
		
		ItemStack milk = (new ItemStack(Item.bucketMilk));
		player.curePotionEffects(milk);
	}
}
