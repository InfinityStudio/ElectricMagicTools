package electricMagicTools.tombenpotter.electricmagictools.common.items.tools;

import ic2.api.item.ElectricItem;
import ic2.api.item.IElectricItem;
import ic2.api.item.IElectricItemManager;

import java.util.List;

import cofh.cofh.api.energy.IEnergyContainerItem;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import electricMagicTools.tombenpotter.electricmagictools.common.CreativeTab;

public class ItemOmnitoolIron extends ItemPickaxe implements IElectricItem , IEnergyContainerItem{

	public int maxCharge = 20000;
	private final int cost = 100;
	private final int hitCost = 125;
	private final int snowCost = 150;

	public ItemOmnitoolIron(int id) {
		super(id, EnumToolMaterial.EMERALD);
		this.efficiencyOnProperMaterial = 13F;
		this.setCreativeTab(CreativeTab.tabTombenpotter);
		this.setMaxDamage(27);
		this.setMaxStackSize(1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerIcons(IconRegister iconRegister) {
		this.itemIcon = iconRegister
				.registerIcon("electricmagictools:ironomnitool");
	}

	@Override
	public boolean canProvideEnergy(ItemStack itemStack) {
		return false;
	}

	@Override
	public int getChargedItemId(ItemStack itemStack) {
		return this.itemID;
	}

	@Override
	public int getEmptyItemId(ItemStack itemStack) {
		return this.itemID;
	}

	@Override
	public int getMaxCharge(ItemStack itemStack) {
		return maxCharge;
	}

	@Override
	public int getTier(ItemStack itemStack) {
		return 2;
	}

	@Override
	public int getTransferLimit(ItemStack itemStack) {
		return 200;
	}

	@SideOnly(Side.CLIENT)
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs,
			List itemList) {
		ItemStack itemStack = new ItemStack(this, 1);

		if (getChargedItemId(itemStack) == this.itemID) {
			ItemStack charged = new ItemStack(this, 1);
			ElectricItem.manager.charge(charged, 2147483647, 2147483647, true,
					false);
			itemList.add(charged);
		}

		if (getEmptyItemId(itemStack) == this.itemID)
			itemList.add(new ItemStack(this, 1, getMaxDamage()));
	}

	@Override
	public boolean onBlockDestroyed(ItemStack par1ItemStack, World par2World,
			int par3, int par4, int par5, int par6,
			EntityLivingBase par7EntityLivingBase) {
		ElectricItem.manager.use(par1ItemStack, cost, par7EntityLivingBase);
		return true;
	}

	@Override
	public boolean canHarvestBlock(Block block, ItemStack stack) {
		return Item.axeIron.canHarvestBlock(block)
				|| Item.swordIron.canHarvestBlock(block)
				|| Item.pickaxeIron.canHarvestBlock(block)
				|| Item.shovelIron.canHarvestBlock(block)
				|| Item.shears.canHarvestBlock(block);
	}

	@Override
	public float getStrVsBlock(ItemStack stack, Block block, int meta) {
		if (!ElectricItem.manager.canUse(stack, cost)) {
			return 1.0F;
		}

		if (Item.axeWood.getStrVsBlock(stack, block, meta) > 1.0F
				|| Item.swordWood.getStrVsBlock(stack, block, meta) > 1.0F
				|| Item.pickaxeWood.getStrVsBlock(stack, block, meta) > 1.0F
				|| Item.shovelWood.getStrVsBlock(stack, block, meta) > 1.0F
				|| Item.shears.getStrVsBlock(stack, block, meta) > 1.0F) {
			return efficiencyOnProperMaterial;
		} else {
			return super.getStrVsBlock(stack, block, meta);
		}
	}

	@Override
	public int getItemEnchantability() {
		return 0;
	}

	@Override
	public boolean isRepairable() {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack itemstack1, ItemStack itemstack2) {
		return false;
	}

	@Override
	public boolean hitEntity(ItemStack itemstack,
			EntityLivingBase entityliving, EntityLivingBase attacker) {
		if (ElectricItem.manager.use(itemstack, hitCost, attacker)) {
			entityliving
					.attackEntityFrom(DamageSource
							.causePlayerDamage((EntityPlayer) attacker), 8F);
		}
		return false;
	}

	@Override
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World,
			EntityPlayer par3EntityPlayer) {
		if (ElectricItem.manager.canUse(par1ItemStack, snowCost)){
		if (!par2World.isRemote) {
			EntitySnowball snowball;
			snowball = new EntitySnowball(par2World, par3EntityPlayer);
			par2World.spawnEntityInWorld(snowball);
		}
		ElectricItem.manager.use(par1ItemStack, snowCost, par3EntityPlayer);
		}
		return par1ItemStack;
	}

	@Override
	public int receiveEnergy(ItemStack container, int maxReceive,
			boolean simulate) {
		return 200;
	}

	@Override
	public int extractEnergy(ItemStack container, int maxExtract,
			boolean simulate) {
		return 0;
	}

	@Override
	public int getEnergyStored(ItemStack container) {
		return 0;
	}

	@Override
	public int getMaxEnergyStored(ItemStack container) {
		return maxCharge;
	}
}
