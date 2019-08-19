package coffeecatrailway.cheesemod.commands;

import coffeecatrailway.cheesemod.CheeseMod;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.StringArgumentType;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.world.dimension.DimensionType;

/**
 * @author CoffeeCatRailway
 * Created: 31/07/2019
 */
public class FoodDimensionCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("foodDim").then(Commands.argument("id", StringArgumentType.greedyString()).executes(context -> {
            ServerPlayerEntity player = context.getSource().asPlayer();

            DimensionType type = DimensionType.byName(CheeseMod.getLocation(StringArgumentType.getString(context, "id")));
            if (type != null)
                player.changeDimension(type);
            else
                player.sendMessage(new StringTextComponent("Ahhh... Something is wrong..."), ChatType.GAME_INFO);
            return 1;
        })));
    }
}