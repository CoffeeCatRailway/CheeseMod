package coffeecatteam.cheesemod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

	public enum EnumGroundType implements IStringSerializable {

		CHEESE(0, "cheese"), HAM(1, "ham");

		private static final EnumHandler.EnumGroundType[] META_LOOKUP = new EnumHandler.EnumGroundType[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		EnumGroundType(int meta, String name) {
			this(meta, name, name);
		}

		EnumGroundType(int meta, String name, String unlocalizedName) {
			this.meta = meta;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName() {
			return this.name;
		}

		public int getMeta() {
			return this.meta;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumHandler.EnumGroundType byMetaData(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumGroundType block$enumtype : values()) {
				META_LOOKUP[block$enumtype.getMeta()] = block$enumtype;
			}
		}
	}
}
