package salute.oneshot.dummyGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CocktailDummyGenerator {

    private static final String TABLE_NAME = "cocktails";
    private static final String COLUMNS = "id,name,description,recipe,type,created_at,modified_at,user_id,like_count,star_rate,view_count";
    private static final List<String> COCKTAIL_NAME_START = new ArrayList<>();
    private static final List<String> COCKTAIL_NAME_MID = new ArrayList<>();
    private static final List<String> COCKTAIL_NAME_END = new ArrayList<>();

    static {
        COCKTAIL_NAME_START.add("Frosty");
        COCKTAIL_NAME_START.add("Crimson");
        COCKTAIL_NAME_START.add("Velvet");
        COCKTAIL_NAME_START.add("Electric");
        COCKTAIL_NAME_START.add("Mystic");
        COCKTAIL_NAME_START.add("Golden");
        COCKTAIL_NAME_START.add("Radiant");
        COCKTAIL_NAME_START.add("Sparkling");
        COCKTAIL_NAME_START.add("Twisted");
        COCKTAIL_NAME_START.add("Midnight");
        COCKTAIL_NAME_START.add("Lunar");
        COCKTAIL_NAME_START.add("Solar");
        COCKTAIL_NAME_START.add("Cosmic");
        COCKTAIL_NAME_START.add("Enchanted");
        COCKTAIL_NAME_START.add("Fiery");
        COCKTAIL_NAME_START.add("Icy");
        COCKTAIL_NAME_START.add("Dreamy");
        COCKTAIL_NAME_START.add("Silky");
        COCKTAIL_NAME_START.add("Bold");
        COCKTAIL_NAME_START.add("Vibrant");
        COCKTAIL_NAME_START.add("Dazzling");
        COCKTAIL_NAME_START.add("Mellow");
        COCKTAIL_NAME_START.add("Lively");
        COCKTAIL_NAME_START.add("Jazzy");
        COCKTAIL_NAME_START.add("Zesty");
        COCKTAIL_NAME_START.add("Fresh");
        COCKTAIL_NAME_START.add("Smoky");
        COCKTAIL_NAME_START.add("Breezy");
        COCKTAIL_NAME_START.add("Shimmering");
        COCKTAIL_NAME_START.add("Whimsical");
        COCKTAIL_NAME_START.add("Glowing");
        COCKTAIL_NAME_START.add("Tropical");
        COCKTAIL_NAME_START.add("Wild");
        COCKTAIL_NAME_START.add("Serene");
        COCKTAIL_NAME_START.add("Dynamic");
        COCKTAIL_NAME_START.add("Rustic");
        COCKTAIL_NAME_START.add("Emerald");
        COCKTAIL_NAME_START.add("Opulent");
        COCKTAIL_NAME_START.add("Prismatic");
        COCKTAIL_NAME_START.add("Lustrous");
        COCKTAIL_NAME_START.add("Celestial");
        COCKTAIL_NAME_START.add("Vivid");
        COCKTAIL_NAME_START.add("Luminous");
        COCKTAIL_NAME_START.add("Majestic");
        COCKTAIL_NAME_START.add("Intriguing");
        COCKTAIL_NAME_START.add("Crisp");
        COCKTAIL_NAME_START.add("Gleaming");
        COCKTAIL_NAME_START.add("Royal");
        COCKTAIL_NAME_START.add("Sizzling");
        COCKTAIL_NAME_START.add("Fragrant");
        COCKTAIL_NAME_START.add("Hypnotic");
        COCKTAIL_NAME_START.add("Glacial");
        COCKTAIL_NAME_START.add("Effervescent");
        COCKTAIL_NAME_START.add("Stellar");
        COCKTAIL_NAME_START.add("Nebulous");
        COCKTAIL_NAME_START.add("Lucid");
        COCKTAIL_NAME_START.add("Mysterious");
        COCKTAIL_NAME_START.add("Adventurous");
        COCKTAIL_NAME_START.add("Fanciful");
        COCKTAIL_NAME_START.add("Rugged");
        COCKTAIL_NAME_START.add("Exotic");
        COCKTAIL_NAME_START.add("Fierce");
        COCKTAIL_NAME_START.add("Regal");
        COCKTAIL_NAME_START.add("Sprightly");
        COCKTAIL_NAME_START.add("Lush");
        COCKTAIL_NAME_START.add("Sublime");
        COCKTAIL_NAME_START.add("Intense");
        COCKTAIL_NAME_START.add("Hyped");
        COCKTAIL_NAME_START.add("Magnetic");
        COCKTAIL_NAME_START.add("Astonishing");
        COCKTAIL_NAME_START.add("Glittering");
        COCKTAIL_NAME_START.add("Arcane");
        COCKTAIL_NAME_START.add("Blazing");
        COCKTAIL_NAME_START.add("Effulgent");
        COCKTAIL_NAME_START.add("Flamboyant");
        COCKTAIL_NAME_START.add("Invigorating");
        COCKTAIL_NAME_START.add("Zippy");
        COCKTAIL_NAME_START.add("Perky");
        COCKTAIL_NAME_START.add("Zany");
        COCKTAIL_NAME_START.add("Vigorous");
        COCKTAIL_NAME_START.add("Pristine");
        COCKTAIL_NAME_START.add("Scorching");
        COCKTAIL_NAME_START.add("Brisk");
        COCKTAIL_NAME_START.add("Gleeful");
        COCKTAIL_NAME_START.add("Sunny");
        COCKTAIL_NAME_START.add("Incandescent");
        COCKTAIL_NAME_START.add("Opalescent");
        COCKTAIL_NAME_START.add("Enigmatic");
        COCKTAIL_NAME_START.add("Spry");
        COCKTAIL_NAME_START.add("Lucent");
        COCKTAIL_NAME_START.add("Seraphic");
        COCKTAIL_NAME_START.add("Divine");
        COCKTAIL_NAME_START.add("Fervent");
        COCKTAIL_NAME_START.add("Whirlwind");
        COCKTAIL_NAME_START.add("Tempestuous");
        COCKTAIL_NAME_START.add("Chilly");
        COCKTAIL_NAME_START.add("Burning");
        COCKTAIL_NAME_START.add("Resplendent");
        COCKTAIL_NAME_START.add("Lyrical");
        COCKTAIL_NAME_START.add("Exuberant");
    }

    static {
        COCKTAIL_NAME_MID.add("Dream");
        COCKTAIL_NAME_MID.add("Whisper");
        COCKTAIL_NAME_MID.add("Mirage");
        COCKTAIL_NAME_MID.add("Symphony");
        COCKTAIL_NAME_MID.add("Serenade");
        COCKTAIL_NAME_MID.add("Harmony");
        COCKTAIL_NAME_MID.add("Eclipse");
        COCKTAIL_NAME_MID.add("Fantasy");
        COCKTAIL_NAME_MID.add("Illusion");
        COCKTAIL_NAME_MID.add("Reverie");
        COCKTAIL_NAME_MID.add("Passion");
        COCKTAIL_NAME_MID.add("Rhapsody");
        COCKTAIL_NAME_MID.add("Enigma");
        COCKTAIL_NAME_MID.add("Fusion");
        COCKTAIL_NAME_MID.add("Rhythm");
        COCKTAIL_NAME_MID.add("Pulse");
        COCKTAIL_NAME_MID.add("Journey");
        COCKTAIL_NAME_MID.add("Quest");
        COCKTAIL_NAME_MID.add("Secret");
        COCKTAIL_NAME_MID.add("Bliss");
        COCKTAIL_NAME_MID.add("Vibration");
        COCKTAIL_NAME_MID.add("Spark");
        COCKTAIL_NAME_MID.add("Flare");
        COCKTAIL_NAME_MID.add("Glitter");
        COCKTAIL_NAME_MID.add("Radiance");
        COCKTAIL_NAME_MID.add("Sparkle");
        COCKTAIL_NAME_MID.add("Velocity");
        COCKTAIL_NAME_MID.add("Euphoria");
        COCKTAIL_NAME_MID.add("Serendipity");
        COCKTAIL_NAME_MID.add("Whimsy");
        COCKTAIL_NAME_MID.add("Riddle");
        COCKTAIL_NAME_MID.add("Momentum");
        COCKTAIL_NAME_MID.add("Essence");
        COCKTAIL_NAME_MID.add("Vibrance");
        COCKTAIL_NAME_MID.add("Cascade");
        COCKTAIL_NAME_MID.add("Zephyr");
        COCKTAIL_NAME_MID.add("Tempest");
        COCKTAIL_NAME_MID.add("Alchemy");
        COCKTAIL_NAME_MID.add("Odyssey");
        COCKTAIL_NAME_MID.add("Echo");
        COCKTAIL_NAME_MID.add("Illumination");
        COCKTAIL_NAME_MID.add("Nova");
        COCKTAIL_NAME_MID.add("Spectacle");
        COCKTAIL_NAME_MID.add("Groove");
        COCKTAIL_NAME_MID.add("Enchantment");
        COCKTAIL_NAME_MID.add("Reverberation");
        COCKTAIL_NAME_MID.add("Labyrinth");
        COCKTAIL_NAME_MID.add("Nebula");
        COCKTAIL_NAME_MID.add("Solstice");
        COCKTAIL_NAME_MID.add("Infusion");
        COCKTAIL_NAME_MID.add("Rapture");
        COCKTAIL_NAME_MID.add("Intuition");
        COCKTAIL_NAME_MID.add("Equinox");
        COCKTAIL_NAME_MID.add("Vortex");
        COCKTAIL_NAME_MID.add("Quiver");
        COCKTAIL_NAME_MID.add("Resonate");
        COCKTAIL_NAME_MID.add("Cantata");
        COCKTAIL_NAME_MID.add("Frenzy");
        COCKTAIL_NAME_MID.add("Ripple");
        COCKTAIL_NAME_MID.add("Drift");
        COCKTAIL_NAME_MID.add("Mystique");
        COCKTAIL_NAME_MID.add("Panorama");
        COCKTAIL_NAME_MID.add("Excursion");
        COCKTAIL_NAME_MID.add("Whirl");
        COCKTAIL_NAME_MID.add("Flux");
        COCKTAIL_NAME_MID.add("Vibrato");
        COCKTAIL_NAME_MID.add("Zeal");
        COCKTAIL_NAME_MID.add("Delight");
        COCKTAIL_NAME_MID.add("Arcadia");
        COCKTAIL_NAME_MID.add("Aura");
        COCKTAIL_NAME_MID.add("Ambience");
        COCKTAIL_NAME_MID.add("Reflection");
        COCKTAIL_NAME_MID.add("Glow");
        COCKTAIL_NAME_MID.add("Trance");
        COCKTAIL_NAME_MID.add("Emanation");
        COCKTAIL_NAME_MID.add("Intensity");
        COCKTAIL_NAME_MID.add("Daydream");
        COCKTAIL_NAME_MID.add("Flight");
        COCKTAIL_NAME_MID.add("Spell");
        COCKTAIL_NAME_MID.add("Mirth");
        COCKTAIL_NAME_MID.add("Jubilee");
        COCKTAIL_NAME_MID.add("Solace");
        COCKTAIL_NAME_MID.add("Vista");
        COCKTAIL_NAME_MID.add("Phantom");
        COCKTAIL_NAME_MID.add("Carnival");
        COCKTAIL_NAME_MID.add("Ecstasy");
        COCKTAIL_NAME_MID.add("Embrace");
        COCKTAIL_NAME_MID.add("Opus");
        COCKTAIL_NAME_MID.add("Glee");
        COCKTAIL_NAME_MID.add("Chime");
        COCKTAIL_NAME_MID.add("Lullaby");
        COCKTAIL_NAME_MID.add("Sonnet");
        COCKTAIL_NAME_MID.add("Allegro");
        COCKTAIL_NAME_MID.add("Mosaic");
        COCKTAIL_NAME_MID.add("Echoes");
        COCKTAIL_NAME_MID.add("Flow");
        COCKTAIL_NAME_MID.add("Wave");
        COCKTAIL_NAME_MID.add("Motion");
        COCKTAIL_NAME_MID.add("Surge");
        COCKTAIL_NAME_MID.add("Crescendo");
    }

    static {
        COCKTAIL_NAME_END.add("Martini");
        COCKTAIL_NAME_END.add("Margarita");
        COCKTAIL_NAME_END.add("Mojito");
        COCKTAIL_NAME_END.add("Smash");
        COCKTAIL_NAME_END.add("Cooler");
        COCKTAIL_NAME_END.add("Punch");
        COCKTAIL_NAME_END.add("Fizz");
        COCKTAIL_NAME_END.add("Sling");
        COCKTAIL_NAME_END.add("Sour");
        COCKTAIL_NAME_END.add("Tini");
        COCKTAIL_NAME_END.add("Collins");
        COCKTAIL_NAME_END.add("Cosmo");
        COCKTAIL_NAME_END.add("Breeze");
        COCKTAIL_NAME_END.add("Elixir");
        COCKTAIL_NAME_END.add("Twist");
        COCKTAIL_NAME_END.add("Spritz");
        COCKTAIL_NAME_END.add("Rickey");
        COCKTAIL_NAME_END.add("Highball");
        COCKTAIL_NAME_END.add("Daiquiri");
        COCKTAIL_NAME_END.add("Julep");
        COCKTAIL_NAME_END.add("Refresher");
        COCKTAIL_NAME_END.add("Splash");
        COCKTAIL_NAME_END.add("Bubbler");
        COCKTAIL_NAME_END.add("Tonic");
        COCKTAIL_NAME_END.add("Spritzer");
        COCKTAIL_NAME_END.add("Fusion");
        COCKTAIL_NAME_END.add("Rumba");
        COCKTAIL_NAME_END.add("Sipper");
        COCKTAIL_NAME_END.add("Chiller");
        COCKTAIL_NAME_END.add("Shandy");
        COCKTAIL_NAME_END.add("Slush");
        COCKTAIL_NAME_END.add("Flip");
        COCKTAIL_NAME_END.add("Mule");
        COCKTAIL_NAME_END.add("Zinger");
        COCKTAIL_NAME_END.add("Lush");
        COCKTAIL_NAME_END.add("Nectar");
        COCKTAIL_NAME_END.add("Ambrosia");
        COCKTAIL_NAME_END.add("Bliss");
        COCKTAIL_NAME_END.add("Delight");
        COCKTAIL_NAME_END.add("Infusion");
        COCKTAIL_NAME_END.add("Cordial");
        COCKTAIL_NAME_END.add("Potion");
        COCKTAIL_NAME_END.add("Dream");
        COCKTAIL_NAME_END.add("Burst");
        COCKTAIL_NAME_END.add("Glow");
        COCKTAIL_NAME_END.add("Sparkle");
        COCKTAIL_NAME_END.add("Wave");
        COCKTAIL_NAME_END.add("Surge");
        COCKTAIL_NAME_END.add("Spirit");
        COCKTAIL_NAME_END.add("Mist");
        COCKTAIL_NAME_END.add("Cloud");
        COCKTAIL_NAME_END.add("Vesper");
        COCKTAIL_NAME_END.add("Remedy");
        COCKTAIL_NAME_END.add("Charm");
        COCKTAIL_NAME_END.add("Jubilee");
        COCKTAIL_NAME_END.add("Reverie");
        COCKTAIL_NAME_END.add("Aura");
        COCKTAIL_NAME_END.add("Mystery");
        COCKTAIL_NAME_END.add("Fantasy");
        COCKTAIL_NAME_END.add("Passion");
        COCKTAIL_NAME_END.add("Allure");
        COCKTAIL_NAME_END.add("Zephyr");
        COCKTAIL_NAME_END.add("Odyssey");
        COCKTAIL_NAME_END.add("Mirage");
        COCKTAIL_NAME_END.add("Fable");
        COCKTAIL_NAME_END.add("Legend");
        COCKTAIL_NAME_END.add("Myth");
        COCKTAIL_NAME_END.add("Epic");
        COCKTAIL_NAME_END.add("Riddle");
        COCKTAIL_NAME_END.add("Secret");
        COCKTAIL_NAME_END.add("Whisper");
        COCKTAIL_NAME_END.add("Horizon");
        COCKTAIL_NAME_END.add("Eclipse");
        COCKTAIL_NAME_END.add("Comet");
        COCKTAIL_NAME_END.add("Nova");
        COCKTAIL_NAME_END.add("Galaxy");
        COCKTAIL_NAME_END.add("Stellar");
        COCKTAIL_NAME_END.add("Orbit");
        COCKTAIL_NAME_END.add("Cosmos");
        COCKTAIL_NAME_END.add("Infinity");
        COCKTAIL_NAME_END.add("Tempest");
        COCKTAIL_NAME_END.add("Cyclone");
        COCKTAIL_NAME_END.add("Vortex");
        COCKTAIL_NAME_END.add("Whirl");
        COCKTAIL_NAME_END.add("Storm");
        COCKTAIL_NAME_END.add("Thunder");
        COCKTAIL_NAME_END.add("Lightning");
        COCKTAIL_NAME_END.add("Aurora");
        COCKTAIL_NAME_END.add("Sunburst");
        COCKTAIL_NAME_END.add("Daybreak");
        COCKTAIL_NAME_END.add("Twilight");
        COCKTAIL_NAME_END.add("Dusk");
        COCKTAIL_NAME_END.add("Dawn");
        COCKTAIL_NAME_END.add("Midnight");
        COCKTAIL_NAME_END.add("Zenith");
        COCKTAIL_NAME_END.add("Apex");
        COCKTAIL_NAME_END.add("Peak");
        COCKTAIL_NAME_END.add("Summit");
        COCKTAIL_NAME_END.add("Pinnacle");
        COCKTAIL_NAME_END.add("Finale");
    }

    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>();
        Random random = new Random();
        int id = 0;

        for (String start : COCKTAIL_NAME_START) {
            for (String mid : COCKTAIL_NAME_MID) {
                for (String end : COCKTAIL_NAME_END) {

                    StringBuilder builder = new StringBuilder();
                    String name = start + " " + mid + " " + end;
                    boolean isOfficial = random.nextInt(10) == 0;
                    int userId = isOfficial ? random.nextInt(1, 1001) : random.nextInt(1, 100001);

                    //id
                    builder.append(++id);
                    builder.append(',');

                    //name
                    builder.append(name);
                    builder.append(',');

                    //description
                    builder.append(name);
                    builder.append(',');

                    //recipe
                    builder.append(name);
                    builder.append(',');

                    //recipeType
                    builder.append(isOfficial ? "OFFICIAL" : "CUSTOM");
                    builder.append(',');

                    //createdAt
                    builder.append(LocalDateTime.now());
                    builder.append(',');

                    //modifiedAt
                    builder.append(LocalDateTime.now());
                    builder.append(',');

                    //userId
                    builder.append(userId);
                    builder.append(',');

                    //likeCount
                    builder.append(0);
                    builder.append(',');

                    //starRate
                    builder.append(0);
                    builder.append(',');

                    //viewCount
                    builder.append(0);

                    dataList.add(builder.toString());
                }
            }
        }
        CSVGenerator.generate(TABLE_NAME, COLUMNS, dataList);
    }


}

class CocktailIngrDummyGenerator {

    private static final String TABLE_NAME = "cocktail_ingredients";
    private static final String COLUMNS = "id,cocktail_id,ingredient_id,volume";

    public static void main(String[] args) {
        List<String> dataList = new ArrayList<>();
        Random random = new Random();
        int id = 0;

        for (int i = 1; i <= 1000000; i++) {

            int numberOfIngr = random.nextInt(2, 7);

            for (int j = 0; j < numberOfIngr; j++) {
                StringBuilder builder = new StringBuilder();

                //id
                builder.append(++id);
                builder.append(',');

                //cocktailId
                builder.append(i);
                builder.append(',');

                //ingredientId
                builder.append(random.nextInt(1,10001));
                builder.append(',');

                //volume
                builder.append(random.nextInt(1,7)*10);
                builder.append("ml");

                dataList.add(builder.toString());
            }

        }
        CSVGenerator.generate(TABLE_NAME,COLUMNS,dataList);
    }

}

