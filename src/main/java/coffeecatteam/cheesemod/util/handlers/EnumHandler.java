package coffeecatteam.cheesemod.util.handlers;

import net.minecraft.util.IStringSerializable;

public class EnumHandler {

	public static enum EnumToastType implements IStringSerializable {

		PLAIN_TOAST(0, 3, "plain"),
		FRENCH_TOAST(1, 6, "french"),
		BACON_TOAST(2, 8, "bacon");

		private static final EnumHandler.EnumToastType[] META_LOOKUP = new EnumHandler.EnumToastType[values().length];
		private final int meta, amount;
		private final String name, unlocalizedName;

		private EnumToastType(int meta, int amount, String name) {
			this(meta, amount, name, name);
		}

		private EnumToastType(int meta, int amount, String name, String unlocalizedName) {
			this.meta = meta;
			this.amount = amount;
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
		
		public int getHealAmount() {
			return amount;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumHandler.EnumToastType byMetaData(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumToastType item$enumtype : values()) {
				META_LOOKUP[item$enumtype.getMeta()] = item$enumtype;
			}
		}
	}

	public static enum EnumCrackerType implements IStringSerializable {

		PLAIN_CRACKER(0, 5, "plain"),
		CRAYFISH_CRACKER(1, 10, "crayfish"),
		CHEESE_N_CRACKER(2, 7, "cheese"),
		GRILLED_CHEESE_N_CRACKER(3, 10, "grilled_cheese"),
		CHEESE_N_CRAYFISH_CRACKER(4, 12, "cheese_n_crayfish"),
		GRILLED_CHEESE_N_CRAYFISH_CRACKER(5, 15, "grilled_cheese_n_crayfish");

		private static final EnumHandler.EnumCrackerType[] META_LOOKUP = new EnumHandler.EnumCrackerType[values().length];
		private final int meta, amount;
		private final String name, unlocalizedName;

		private EnumCrackerType(int meta, int amount, String name) {
			this(meta, amount, name, name);
		}

		private EnumCrackerType(int meta, int amount, String name, String unlocalizedName) {
			this.meta = meta;
			this.amount = amount;
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
		
		public int getHealAmount() {
			return amount;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumHandler.EnumCrackerType byMetaData(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumCrackerType item$enumtype : values()) {
				META_LOOKUP[item$enumtype.getMeta()] = item$enumtype;
			}
		}
	}

	public static enum EnumToastieType implements IStringSerializable {

		CHEESE(false, 0, 8, "cheese"),
		GRILLED_CHEESE(true, 1, 11, "grilled_cheese"),
		HAM_RAW_N_CHEESE(false, 2, 9, "ham_raw_n_cheese"),
		HAM_COOKED_N_CHEESE(false, 3, 11, "ham_cooked_n_cheese"),
		GRILLED_HAM_RAW_N_CHEESE(true, 4, 12, "grilled_ham_raw_n_cheese"),
		GRILLED_HAM_COOKED_N_CHEESE(true, 5, 14, "grilled_ham_cooked_n_cheese");

		private static final EnumHandler.EnumToastieType[] META_LOOKUP = new EnumHandler.EnumToastieType[values().length];
		private final boolean grilled;
		private final int meta, amount;
		private final String name, unlocalizedName;

		private EnumToastieType(boolean grilled, int meta, int amount, String name) {
			this(grilled, meta, amount, name, name);
		}

		private EnumToastieType(boolean grilled, int meta, int amount, String name, String unlocalizedName) {
			this.grilled = grilled;
			this.meta = meta;
			this.amount = amount;
			this.name = name;
			this.unlocalizedName = unlocalizedName;
		}

		@Override
		public String getName() {
			return this.name;
		}
		
		public boolean isGrilled() {
			return grilled;
		}

		public int getMeta() {
			return this.meta;
		}
		
		public int getHealAmount() {
			return amount;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumHandler.EnumToastieType byMetaData(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumToastieType item$enumtype : values()) {
				META_LOOKUP[item$enumtype.getMeta()] = item$enumtype;
			}
		}
	}

	public static enum EnumPizzaType implements IStringSerializable {

		BLAND(0, 5, "bland"),
		UNCOOKED_CHEESE(1, 7, "uncooked_cheese"),
		COOKED_CHEESE(2, 10, "cooked_cheese"),
		UNCOOKED_HAM_N_CHEESE(3, 9, "uncooked_ham_n_cheese"),
		COOKED_HAM_N_CHEESE(4, 13, "cooked_ham_n_cheese"),
		UNCOOKED_HAM_PINEAPPLE_N_CHEESE(5, 12, "uncooked_ham_pineapple_n_cheese"),
		COOKED_HAM_PINEAPPLE_N_CHEESE(6, 15, "cooked_ham_pineapple_n_cheese");

		private static final EnumHandler.EnumPizzaType[] META_LOOKUP = new EnumHandler.EnumPizzaType[values().length];
		private final int meta, amount;
		private final String name, unlocalizedName;

		private EnumPizzaType(int meta, int amount, String name) {
			this(meta, amount, name, name);
		}

		private EnumPizzaType(int meta, int amount, String name, String unlocalizedName) {
			this.meta = meta;
			this.amount = amount;
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
		
		public int getHealAmount() {
			return amount;
		}

		public String getUnlocalizedName() {
			return this.unlocalizedName;
		}

		@Override
		public String toString() {
			return this.name;
		}

		public static EnumHandler.EnumPizzaType byMetaData(int meta) {
			if (meta < 0 || meta >= META_LOOKUP.length) {
				meta = 0;
			}
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumPizzaType item$enumtype : values()) {
				META_LOOKUP[item$enumtype.getMeta()] = item$enumtype;
			}
		}
	}

	public static enum EnumGroundType implements IStringSerializable {

		CHEESE(0, "cheese"), HAM(1, "ham");

		private static final EnumHandler.EnumGroundType[] META_LOOKUP = new EnumHandler.EnumGroundType[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumGroundType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumGroundType(int meta, String name, String unlocalizedName) {
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

	public static enum EnumWoodType implements IStringSerializable {

		CHEESE(0, "cheese"), GRILLED_CHEESE(1, "grilled_cheese"), HAM_RAW(2, "ham_raw"), HAM_COOKED(3, "ham_cooked");

		private static final EnumHandler.EnumWoodType[] META_LOOKUP = new EnumHandler.EnumWoodType[values().length];
		private final int meta;
		private final String name, unlocalizedName;

		private EnumWoodType(int meta, String name) {
			this(meta, name, name);
		}

		private EnumWoodType(int meta, String name, String unlocalizedName) {
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

		public static EnumHandler.EnumWoodType byMetaData(int meta) {
			return META_LOOKUP[meta];
		}

		static {
			for (EnumHandler.EnumWoodType block$enumtype : values()) {
				META_LOOKUP[block$enumtype.getMeta()] = block$enumtype;
			}
		}
	}
}
