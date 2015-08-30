package cristaltek.hitekmod.plugins.nei;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import codechicken.lib.inventory.InventoryUtils;
import codechicken.nei.FastTransferManager;
import codechicken.nei.PositionedStack;
import codechicken.nei.api.IOverlayHandler;
import codechicken.nei.recipe.DefaultOverlayHandler.DistributedIngred;
import codechicken.nei.recipe.DefaultOverlayHandler.IngredientDistribution;
import codechicken.nei.recipe.IRecipeHandler;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

// Essentially a copy of the DefaultOverlayHandler class
// The adapted part is the isSlotEqualPositionedStack function (now used in clearIngredients & mapIngredSlots)
public class PreciseOverlayHandler implements IOverlayHandler {

	private int xStart, yStart;
	private int xSpacing, ySpacing;
	
	public PreciseOverlayHandler(int xStart, int yStart, int xSpacing, int ySpacing) {
		this.xStart = xStart;
		this.yStart = yStart;
		this.xSpacing = xSpacing;
		this.ySpacing = ySpacing;
	}

	@Override
	public void overlayRecipe(GuiContainer gui, IRecipeHandler recipe, int recipeIndex, boolean shift) {
		List<PositionedStack> ingredients = recipe.getIngredientStacks(recipeIndex);
		List<DistributedIngred> ingredStacks = getPermutationIngredients(ingredients);
		
		if(!clearIngredients(gui, ingredients))
			return;
		
		findInventoryQuantities(gui, ingredStacks);
		
		List<IngredientDistribution> assignedIngredients = assignIngredients(ingredients, ingredStacks);
		if (assignedIngredients == null)
			return;
		
		assignIngredSlots(gui, ingredients, assignedIngredients);
		int quantity = calculateRecipeQuantity(assignedIngredients);
		
		if (quantity != 0)
			moveIngredients(gui, assignedIngredients, quantity);
	}
	
	@SuppressWarnings("unchecked")
	private boolean clearIngredients(GuiContainer gui, List<PositionedStack> ingredients) {
		for (PositionedStack pstack : ingredients) {
			for (Slot slot : (List<Slot>)gui.inventorySlots.inventorySlots) {
				if (isSlotEqualPositionedStack(slot, pstack)) {
					if (!slot.getHasStack())
						continue;
					
					FastTransferManager.clickSlot(gui, slot.slotNumber, 0, 1);
					if (slot.getHasStack())
						return false;
				}
			}
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	private void findInventoryQuantities(GuiContainer gui, List<DistributedIngred> ingredStacks) {
		for (Slot slot : (List<Slot>)gui.inventorySlots.inventorySlots) {
			if (slot.getHasStack() && canMoveFrom(slot, gui)) {
				ItemStack pstack = slot.getStack();
				DistributedIngred istack = findIngred(ingredStacks, pstack);
				if (istack != null)
					istack.invAmount += pstack.stackSize;
			}
		}
	}
	
	private List<IngredientDistribution> assignIngredients(List<PositionedStack> ingredients, List<DistributedIngred> ingredStacks) {
		ArrayList<IngredientDistribution> assignedIngredients = new ArrayList<IngredientDistribution>();
		for (PositionedStack posstack : ingredients) {
			DistributedIngred biggestIngred = null;
			ItemStack permutation = null;
			int biggestSize = 0;
			for (ItemStack pstack : posstack.items) {
				for (int j = 0; j < ingredStacks.size(); j++) {
					DistributedIngred istack = ingredStacks.get(j);
					if (!InventoryUtils.canStack(pstack, istack.stack) || istack.invAmount-istack.distributed < pstack.stackSize)
						continue;
					
					int relsize = (istack.invAmount-istack.invAmount/istack.recipeAmount*istack.distributed)/pstack.stackSize;
					if (relsize > biggestSize) {
						biggestSize = relsize;
						biggestIngred = istack;
						permutation = pstack;
						break;
					}
				}
			}
			
			if (biggestIngred == null)
				return null;
			
			biggestIngred.distributed += permutation.stackSize;
			assignedIngredients.add(new IngredientDistribution(biggestIngred, permutation));
		}
		
		return assignedIngredients;
	}
	
	private Slot[][] assignIngredSlots(GuiContainer gui, List<PositionedStack> ingredients, List<IngredientDistribution> assignedIngredients) {
		Slot[][] recipeSlots = mapIngredSlots(gui, ingredients);//setup the slot map
		
		HashMap<Slot, Integer> distribution = new HashMap<Slot, Integer>();
		for (int i = 0; i < recipeSlots.length; i++) {
			for (Slot slot : recipeSlots[i]) {
				if (!distribution.containsKey(slot))
					distribution.put(slot, -1);
			}
		}
		
		HashSet<Slot> avaliableSlots = new HashSet<Slot>(distribution.keySet());
		HashSet<Integer> remainingIngreds = new HashSet<Integer>();
		ArrayList<LinkedList<Slot>> assignedSlots = new ArrayList<LinkedList<Slot>>();
		for (int i = 0; i < ingredients.size(); i++) {
			remainingIngreds.add(i);
			assignedSlots.add(new LinkedList<Slot>());
		}
		
		while (avaliableSlots.size() > 0 && remainingIngreds.size() > 0) {
			for (Iterator<Integer> iterator = remainingIngreds.iterator(); iterator.hasNext();) {
				int i = iterator.next();
				boolean assigned = false;
				DistributedIngred istack = assignedIngredients.get(i).distrib;
				
				for (Slot slot : recipeSlots[i]) {
					if (avaliableSlots.contains(slot)) {
						avaliableSlots.remove(slot);
						if (slot.getHasStack())
							continue;
						
						istack.numSlots++;
						assignedSlots.get(i).add(slot);
						assigned = true;
						break;
					}
				}
				
				if (!assigned || istack.numSlots*istack.stack.getMaxStackSize() >= istack.invAmount)
					iterator.remove();
			}
		}
		
		for (int i = 0; i < ingredients.size(); i++)
			assignedIngredients.get(i).slots = assignedSlots.get(i).toArray(new Slot[0]);
		return recipeSlots;
	}
	
	private int calculateRecipeQuantity(List<IngredientDistribution> assignedIngredients) {
		int quantity = Integer.MAX_VALUE;
		for (IngredientDistribution distrib : assignedIngredients) {
			DistributedIngred istack = distrib.distrib;
			if (istack.numSlots == 0)
				return 0;
			
			int allSlots = istack.invAmount;
			if (allSlots/istack.numSlots > istack.stack.getMaxStackSize())
				allSlots = istack.numSlots*istack.stack.getMaxStackSize();
			
			quantity = Math.min(quantity, allSlots/istack.distributed);
		}
		
		return quantity;
	}
	
	@SuppressWarnings("unchecked")
	private void moveIngredients(GuiContainer gui, List<IngredientDistribution> assignedIngredients, int quantity) {
		for (IngredientDistribution distrib : assignedIngredients) {
			ItemStack pstack = distrib.permutation;
			int transferCap = quantity*pstack.stackSize;
			int transferred = 0;
			
			int destSlotIndex = 0;
			Slot dest = distrib.slots[0];
			int slotTransferred = 0;
			int slotTransferCap = pstack.getMaxStackSize();
			
			for (Slot slot : (List<Slot>)gui.inventorySlots.inventorySlots) {
				if (!slot.getHasStack() || !canMoveFrom(slot, gui))
					continue;
				
				ItemStack stack = slot.getStack();
				if (!InventoryUtils.canStack(stack, pstack))
					continue;
				
				FastTransferManager.clickSlot(gui, slot.slotNumber);
				int amount = Math.min(transferCap-transferred, stack.stackSize);
				for (int c = 0; c < amount; c++) {
					FastTransferManager.clickSlot(gui, dest.slotNumber, 1);
					transferred++;
					slotTransferred++;
					if (slotTransferred >= slotTransferCap) {
						destSlotIndex++;
						if (destSlotIndex == distrib.slots.length) {
							dest = null;
							break;
						}
						dest = distrib.slots[destSlotIndex];
						slotTransferred = 0;
					}
				}
				FastTransferManager.clickSlot(gui, slot.slotNumber);
				if (transferred >= transferCap || dest == null)
					break;
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	private Slot[][] mapIngredSlots(GuiContainer gui, List<PositionedStack> ingredients) {
		Slot[][] recipeSlotList = new Slot[ingredients.size()][];
		for (int i = 0; i < ingredients.size(); i++) {
			LinkedList<Slot> recipeSlots = new LinkedList<Slot>();
			PositionedStack pstack = ingredients.get(i);
			for (Slot slot : (List<Slot>)gui.inventorySlots.inventorySlots) {
				if (isSlotEqualPositionedStack(slot, pstack)) {
					recipeSlots.add(slot);
					break;
				}
			}
			recipeSlotList[i] = recipeSlots.toArray(new Slot[0]);
		}
		return recipeSlotList;
	}

	private List<DistributedIngred> getPermutationIngredients(List<PositionedStack> ingredients) {
		ArrayList<DistributedIngred> ingredStacks = new ArrayList<DistributedIngred>();
		for (PositionedStack posstack : ingredients) {
			for (ItemStack pstack : posstack.items) {
				DistributedIngred istack = findIngred(ingredStacks, pstack);
				if (istack == null)
					ingredStacks.add(istack = new DistributedIngred(pstack));
				istack.recipeAmount += pstack.stackSize;
			}
		}
		return ingredStacks;
	}
	
	private DistributedIngred findIngred(List<DistributedIngred> ingredStacks, ItemStack pstack) {
		for (DistributedIngred istack : ingredStacks)
			if (InventoryUtils.canStack(pstack, istack.stack))
				return istack;
		return null;
	}
	
	private boolean canMoveFrom(Slot slot, GuiContainer gui) {
		return slot.inventory instanceof InventoryPlayer;
	}
	
	private boolean isSlotEqualPositionedStack(Slot slot, PositionedStack pstack) {
		// Calculate row & column from hard-coded NEI stack position
		int col = (pstack.relx - 25) / 18;
		int row = (pstack.rely - 6) / 18;
		
		return (slot.xDisplayPosition == this.xStart + col * this.xSpacing &&
				slot.yDisplayPosition == this.yStart + row * this.ySpacing);
	}
}
