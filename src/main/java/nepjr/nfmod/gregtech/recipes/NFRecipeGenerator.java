package nepjr.nfmod.gregtech.recipes;

import gregtech.api.GTValues;
import gregtech.api.recipes.RecipeBuilder;
import gregtech.api.unification.OreDictUnifier;
import gregtech.api.unification.material.Material;
import gregtech.api.unification.material.properties.OreProperty;
import gregtech.api.unification.material.properties.PropertyKey;
import gregtech.api.unification.ore.OrePrefix;
import gregtech.api.unification.stack.MaterialStack;
import gregtech.api.util.GTUtility;
import gregtech.common.ConfigHolder;
import net.minecraft.item.ItemStack;

public class NFRecipeGenerator 
{
	public static void init()
	{
		OrePrefix.ore.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
        OrePrefix.oreEndstone.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
        OrePrefix.oreNetherrack.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
        if (ConfigHolder.worldgen.allUniqueStoneTypes) {
            OrePrefix.oreGranite.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreDiorite.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreAndesite.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreBasalt.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreBlackgranite.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreMarble.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreRedgranite.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreSand.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
            OrePrefix.oreRedSand.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processOre);
        }
        
        OrePrefix.crushed.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processCrushedOre);
        OrePrefix.crushedPurified.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processCrushedPurified);
        OrePrefix.crushedCentrifuged.addProcessingHandler(PropertyKey.ORE, NFRecipeGenerator::processCrushedCentrifuged);

	}
	
    public static void processOre(OrePrefix orePrefix, Material material, OreProperty property) {
        Material byproductMaterial = property.getOreByProduct(0, material);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.gem, byproductMaterial);
        if (byproductStack.isEmpty()) byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);
        ItemStack crushedStack = OreDictUnifier.get(OrePrefix.crushed, material);
        ItemStack ingotStack;
        Material smeltingMaterial = property.getDirectSmeltResult() == null ? material :
                property.getDirectSmeltResult();
        double amountOfCrushedOre = property.getOreMultiplier();
        if (smeltingMaterial.hasProperty(PropertyKey.INGOT)) {
            ingotStack = OreDictUnifier.get(OrePrefix.ingot, smeltingMaterial);
        } else if (smeltingMaterial.hasProperty(PropertyKey.GEM)) {
            ingotStack = OreDictUnifier.get(OrePrefix.gem, smeltingMaterial);
        } else {
            ingotStack = OreDictUnifier.get(OrePrefix.dust, smeltingMaterial);
        }
        int oreTypeMultiplier = orePrefix == OrePrefix.oreNetherrack || orePrefix == OrePrefix.oreEndstone ? 2 : 1;
        ingotStack.setCount(ingotStack.getCount() * property.getOreMultiplier() * oreTypeMultiplier);
        crushedStack.setCount(crushedStack.getCount() * property.getOreMultiplier());

        if (!crushedStack.isEmpty()) {
            RecipeBuilder<?> builder = NFRecipeMaps.ORE_CRUSHER.recipeBuilder();
            builder
                    .input(orePrefix, material)
                    .outputs(GTUtility.copy((int) Math.round(amountOfCrushedOre) * 4 * oreTypeMultiplier, crushedStack))
                    .outputs(byproductStack)
                    .EUt(GTValues.VA[GTValues.EV])
                    .duration(400);
            for (MaterialStack secondaryMaterial : orePrefix.secondaryMaterials) {
                if (secondaryMaterial.material.hasProperty(PropertyKey.DUST)) {
                    ItemStack dustStack = OreDictUnifier.getGem(secondaryMaterial);
                    builder.outputs(dustStack);
                }
            }

            builder.buildAndRegister();
        }
    }
    
    public static void processCrushedOre(OrePrefix crushedPrefix, Material material, OreProperty property) {
        ItemStack impureDustStack = OreDictUnifier.get(OrePrefix.dustImpure, material);
        Material byproductMaterial = property.getOreByProduct(0, material);

        // fallback for dirtyGravel, shard & clump
        if (impureDustStack.isEmpty()) {
            impureDustStack = GTUtility.copyFirst(
                    OreDictUnifier.get(OrePrefix.dirtyGravel, material),
                    OreDictUnifier.get(OrePrefix.shard, material),
                    OreDictUnifier.get(OrePrefix.clump, material),
                    OreDictUnifier.get(OrePrefix.dust, material));
        }

        NFRecipeMaps.ORE_CRUSHER.recipeBuilder()
                .input(crushedPrefix, material)
                .outputs(impureDustStack)
                .duration(400)
                .EUt(GTValues.VA[GTValues.MV])
                .outputs(OreDictUnifier.get(OrePrefix.dust, byproductMaterial, property.getByProductMultiplier()))
                .buildAndRegister();
    }
    
    public static void processCrushedCentrifuged(OrePrefix centrifugedPrefix, Material material, OreProperty property) {
        ItemStack dustStack = OreDictUnifier.get(OrePrefix.dust, material);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, property.getOreByProduct(2, material), 1);

        NFRecipeMaps.ORE_CRUSHER.recipeBuilder()
                .input(centrifugedPrefix, material)
                .outputs(dustStack)
                .outputs(byproductStack)
                .EUt(GTValues.VA[GTValues.MV])
                .duration(400)
                .buildAndRegister();
    }

    public static void processCrushedPurified(OrePrefix purifiedPrefix, Material material, OreProperty property) {
        ItemStack dustStack = OreDictUnifier.get(OrePrefix.dustPure, material);
        Material byproductMaterial = property.getOreByProduct(1, material);
        ItemStack byproductStack = OreDictUnifier.get(OrePrefix.dust, byproductMaterial);

        NFRecipeMaps.ORE_CRUSHER.recipeBuilder()
        .input(purifiedPrefix, material)
        .outputs(dustStack)
        .outputs(byproductStack)
        .EUt(GTValues.VA[GTValues.MV])
        .duration(400)
        .buildAndRegister();
    }
}
