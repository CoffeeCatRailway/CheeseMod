package coffeecatrailway.cheesemod.command;

import coffeecatrailway.cheesemod.ModConfig;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.command.CommandSource;
import net.minecraft.command.Commands;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.util.text.StringTextComponent;

import java.util.Map;

/**
 * @author CoffeeCatRailway
 * Created: 20/07/2019
 */
public class ConfigCommand {

    public static void register(CommandDispatcher<CommandSource> dispatcher) {
        dispatcher.register(Commands.literal("config").executes(context -> {
            ServerPlayerEntity player = context.getSource().asPlayer();
            Map<String, Object> configVals = ModConfig.CONFIG.valueMap();

            for (String value : configVals.keySet())
                player.sendMessage(new StringTextComponent("- '").appendText(value).appendText("' set to: ").appendText(String.valueOf(configVals.get(value))));
            return 1;
        }));
    }
}
