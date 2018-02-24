package coffeecatteam.cheesemod;

public class LaunchException extends Exception {

	@Override
	public String getMessage() {
		return "The developer version of the CheeseMod has been detected and can only be run in a Forge development environment. If you are not a developer, download the normal version (https://minecraft.curseforge.com/projects/cheesemod)";
	}
}
