package salute.oneshot.dummyGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class IngrDummyGenerator {

    private static final String FILE_NAME = "ingredients.csv";
    private static final String HEADER = "ID,Name,AVB,Description,Category";
    private static final List<String> BASE_PREFIX = new ArrayList<>();
    private static final Map<String, String> SPIRIT_CATEGORY_MAP = new HashMap<>();

    static {
        BASE_PREFIX.add("Golden");
        BASE_PREFIX.add("Crimson");
        BASE_PREFIX.add("Mystic");
        BASE_PREFIX.add("Velvet");
        BASE_PREFIX.add("Frosty");
        BASE_PREFIX.add("Smoky");
        BASE_PREFIX.add("Bold");
        BASE_PREFIX.add("Elegant");
        BASE_PREFIX.add("Vibrant");
        BASE_PREFIX.add("Luminous");
        BASE_PREFIX.add("Radiant");
        BASE_PREFIX.add("Aurora");
        BASE_PREFIX.add("Midnight");
        BASE_PREFIX.add("Electric");
        BASE_PREFIX.add("Fierce");
        BASE_PREFIX.add("Spicy");
        BASE_PREFIX.add("Zesty");
        BASE_PREFIX.add("Lively");
        BASE_PREFIX.add("Icy");
        BASE_PREFIX.add("Fiery");
        BASE_PREFIX.add("Enchanted");
        BASE_PREFIX.add("Sublime");
        BASE_PREFIX.add("Lustrous");
        BASE_PREFIX.add("Gilded");
        BASE_PREFIX.add("Stellar");
        BASE_PREFIX.add("Brisk");
        BASE_PREFIX.add("Dynamic");
        BASE_PREFIX.add("Exquisite");
        BASE_PREFIX.add("Majestic");
        BASE_PREFIX.add("Regal");
        BASE_PREFIX.add("Sleek");
        BASE_PREFIX.add("Polished");
        BASE_PREFIX.add("Refined");
        BASE_PREFIX.add("Rugged");
        BASE_PREFIX.add("Intense");
        BASE_PREFIX.add("Mellow");
        BASE_PREFIX.add("Gritty");
        BASE_PREFIX.add("Mysterious");
        BASE_PREFIX.add("Revered");
        BASE_PREFIX.add("Grand");
        BASE_PREFIX.add("Imperial");
        BASE_PREFIX.add("Noble");
        BASE_PREFIX.add("Robust");
        BASE_PREFIX.add("Savory");
        BASE_PREFIX.add("Sharp");
        BASE_PREFIX.add("Smooth");
        BASE_PREFIX.add("Subtle");
        BASE_PREFIX.add("Alluring");
        BASE_PREFIX.add("Crisp");
        BASE_PREFIX.add("Divine");
        BASE_PREFIX.add("Heavenly");
        BASE_PREFIX.add("Ethereal");
        BASE_PREFIX.add("Pure");
        BASE_PREFIX.add("Vintage");
        BASE_PREFIX.add("Ornate");
        BASE_PREFIX.add("Opulent");
        BASE_PREFIX.add("Fabled");
        BASE_PREFIX.add("Legendary");
        BASE_PREFIX.add("Exotic");
        BASE_PREFIX.add("Glorious");
        BASE_PREFIX.add("Transcendent");
        BASE_PREFIX.add("Primal");
        BASE_PREFIX.add("Timeless");
        BASE_PREFIX.add("Infinite");
        BASE_PREFIX.add("Enigmatic");
        BASE_PREFIX.add("Frosted");
        BASE_PREFIX.add("Rustic");
        BASE_PREFIX.add("Arcane");
        BASE_PREFIX.add("Daring");
        BASE_PREFIX.add("Magnetic");
        BASE_PREFIX.add("Vigorous");
        BASE_PREFIX.add("Radiating");
        BASE_PREFIX.add("Incandescent");
        BASE_PREFIX.add("Shimmering");
        BASE_PREFIX.add("Lyrical");
        BASE_PREFIX.add("Bohemian");
        BASE_PREFIX.add("Spirited");
        BASE_PREFIX.add("Feverish");
        BASE_PREFIX.add("Whimsical");
        BASE_PREFIX.add("Sparkling");
        BASE_PREFIX.add("Luscious");
        BASE_PREFIX.add("Brazen");
        BASE_PREFIX.add("Earthy");
        BASE_PREFIX.add("Toasted");
        BASE_PREFIX.add("Burnished");
        BASE_PREFIX.add("Distinct");
        BASE_PREFIX.add("Keen");
        BASE_PREFIX.add("Fervent");
        BASE_PREFIX.add("Zingy");
        BASE_PREFIX.add("Piquant");
        BASE_PREFIX.add("Hearty");
        BASE_PREFIX.add("Zealous");
        BASE_PREFIX.add("Lush");
        BASE_PREFIX.add("Intrepid");
        BASE_PREFIX.add("Dazzling");
        BASE_PREFIX.add("Pristine");
        BASE_PREFIX.add("Gleaming");
        BASE_PREFIX.add("Impervious");
        BASE_PREFIX.add("Resolute");
        BASE_PREFIX.add("Exuberant");
    }
    static {
        // BEER
        SPIRIT_CATEGORY_MAP.put("Brew", "BEER");
        SPIRIT_CATEGORY_MAP.put("Draught", "BEER");
        SPIRIT_CATEGORY_MAP.put("Malt", "BEER");
        SPIRIT_CATEGORY_MAP.put("Mash", "BEER");

        // BRANDY
        SPIRIT_CATEGORY_MAP.put("Brandy", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Cognac", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Calvados", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Armagnac", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Grappa", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Pisco", "BRANDY");
        SPIRIT_CATEGORY_MAP.put("Kirsch", "BRANDY");

        // JIN
        SPIRIT_CATEGORY_MAP.put("Gin", "JIN");

        // RUM
        SPIRIT_CATEGORY_MAP.put("Rum", "RUM");
        SPIRIT_CATEGORY_MAP.put("Cachaça", "RUM");
        SPIRIT_CATEGORY_MAP.put("Arrack", "RUM");

        // TEQUILA
        SPIRIT_CATEGORY_MAP.put("Tequila", "TEQUILA");
        SPIRIT_CATEGORY_MAP.put("Mezcal", "TEQUILA");

        // VODKA
        SPIRIT_CATEGORY_MAP.put("Vodka", "VODKA");
        SPIRIT_CATEGORY_MAP.put("Soju", "VODKA");
        SPIRIT_CATEGORY_MAP.put("Baijiu", "VODKA");

        // WHISKEY
        SPIRIT_CATEGORY_MAP.put("Whiskey", "WHISKEY");
        SPIRIT_CATEGORY_MAP.put("Bourbon", "WHISKEY");
        SPIRIT_CATEGORY_MAP.put("Scotch", "WHISKEY");
        SPIRIT_CATEGORY_MAP.put("Rye", "WHISKEY");
        SPIRIT_CATEGORY_MAP.put("Moonshine", "WHISKEY");

        // WINE
        SPIRIT_CATEGORY_MAP.put("Vintage", "WINE");
        SPIRIT_CATEGORY_MAP.put("Sherry", "WINE");
        SPIRIT_CATEGORY_MAP.put("Port", "WINE");
        SPIRIT_CATEGORY_MAP.put("Mead", "WINE");
        SPIRIT_CATEGORY_MAP.put("Vermouth", "WINE");
        SPIRIT_CATEGORY_MAP.put("Oloroso", "WINE");
        SPIRIT_CATEGORY_MAP.put("Sake", "WINE");

        // OTHER
        SPIRIT_CATEGORY_MAP.put("Absinthe", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Schnapps", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Alembic", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Distillate", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Elixir", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Spirit", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Liquor", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Essence", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Nectar", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Ambrosia", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Extract", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Serum", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Concoction", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Infusion", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Potion", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Blend", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Fusion", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Tonic", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Barrel", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Cask", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Reserve", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Still", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Ember", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Oak", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Bitters", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Aquavit", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Ouzo", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Raki", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Sambuca", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Liqueur", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Amaro", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Pastis", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Grain", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Firewater", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Hooch", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Remedy", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Distiller", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Stillwater", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Vapor", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Proof", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Cellar", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Alchemy", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Cordial", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Ritual", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Charm", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Allure", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Savor", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Gusto", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Fervor", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Obscura", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Mythos", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Sundown", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Twilight", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Zenith", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Ethanol", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Alcohol", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Bouquet", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Crown", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Quintessence", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Paradox", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Eminence", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Mystique", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Rapture", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Spiritcraft", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Fable", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Vigor", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Impulse", "OTHER");
        SPIRIT_CATEGORY_MAP.put("Eclipse", "OTHER");
    }

    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>();
        Random random = new Random();
        int id=0;

        for( String base : BASE_PREFIX){
            for (String spirit : SPIRIT_CATEGORY_MAP.keySet()) {

                StringBuilder builder = new StringBuilder();

                //id
                builder.append(++id);
                builder.append(',');

                //name
                builder.append(base);
                builder.append(' ');
                builder.append(spirit);
                String name = builder.toString();
                builder.append(',');

                //avb
                float avb = Math.round(random.nextFloat(0.1f,0.7f)*10000);
                builder.append(avb/100f);
                builder.append(',');

                //description
                builder.append(name);
                builder.append(',');

                //category
                builder.append(SPIRIT_CATEGORY_MAP.get(spirit));

                dataList.add(builder.toString());
            }
        }
        CSVGenerator.generate(FILE_NAME,HEADER,dataList);
    }


}
