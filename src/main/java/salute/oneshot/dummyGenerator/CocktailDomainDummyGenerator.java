package salute.oneshot.dummyGenerator;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class CocktailDomainDummyGenerator {

    private static final String COCKTAIL_TABLE_NAME = "cocktails";
    private static final String COCKTAIL_COLUMNS = "id,name,description,recipe,type,created_at,modified_at,user_id,like_count,star_rate,view_count";
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

    private static final String INGR_TABLE_NAME = "ingredients";
    private static final String INGR_COLUMNS = "id,name,avb,description,category,created_at,modified_at";
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
        List<String> cokctailDataList = new ArrayList<>();
        List<String> ingrDataList = new ArrayList<>();
        List<String> cocktailIngrDataList = new ArrayList<>();

        Map<Integer, String> ingrName = new HashMap<>();
        Map<Integer, String> cocktailName = new HashMap<>();
        Random random = new Random();
        int id = 0;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bulkIngr.json"))){

            for (String base : BASE_PREFIX) {
                for (String spirit : SPIRIT_CATEGORY_MAP.keySet()) {

                    ObjectMapper objectMapper = new ObjectMapper();
                    StringBuilder builder = new StringBuilder();
                    String name = base + ' ' + spirit;
                    String category = SPIRIT_CATEGORY_MAP.get(spirit);

                    id++;
                    ingrName.put(id, name);

                    //id
                    builder.append(id);
                    builder.append(',');

                    //name
                    builder.append(base);
                    builder.append(' ');
                    builder.append(spirit);
                    builder.append(',');

                    //avb
                    float avb = Math.round(random.nextFloat(0.1f, 0.7f) * 10000);
                    builder.append(avb / 100f);
                    builder.append(',');

                    //description
                    builder.append(name);
                    builder.append(',');

                    //category
                    builder.append(category);
                    builder.append(',');

                    //createdAt
                    builder.append(LocalDateTime.now());
                    builder.append(',');

                    //modifiedAt
                    builder.append(LocalDateTime.now());

                    ingrDataList.add(builder.toString());

                    Map<String, Object> indexMeta = new HashMap<>();
                    Map<String, Object> indexContent = new HashMap<>();
                    indexContent.put("_index", "ingredients");
                    indexContent.put("_id", id);
                    indexMeta.put("index", indexContent);
                    writer.write(objectMapper.writeValueAsString(indexMeta));
                    writer.newLine();

                    Map<String, Object> ingredient = new HashMap<>();
                    ingredient.put("id", id);
                    ingredient.put("name", name);
                    ingredient.put("description", "Description of " + name);
                    ingredient.put("category",category);

                    writer.write(objectMapper.writeValueAsString(ingredient));
                    writer.newLine();
                }
            }
            CSVGenerator.generate(INGR_TABLE_NAME, INGR_COLUMNS, ingrDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("bulkCocktail.json"))) {

            List<Map<String, Object>> cocktailList = new ArrayList<>();
            id = 0;
            int ciId =0;
            for (String start : COCKTAIL_NAME_START) {
                for (String mid : COCKTAIL_NAME_MID) {
                    for (String end : COCKTAIL_NAME_END) {

                        ObjectMapper objectMapper = new ObjectMapper();
                        StringBuilder builder = new StringBuilder();
                        String name = start + " " + mid + " " + end;
                        boolean isOfficial = random.nextInt(10) == 0;
                        int userId =
                            isOfficial ? random.nextInt(1, 1001) : random.nextInt(1, 100001);

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

                        cokctailDataList.add(builder.toString());


                        Map<String, Object> indexMeta = new HashMap<>();
                        Map<String, Object> indexContent = new HashMap<>();
                        indexContent.put("_index", "cocktails");
                        indexContent.put("_id", id);
                        indexMeta.put("index", indexContent);

                        writer.write(objectMapper.writeValueAsString(indexMeta));
                        writer.newLine();

                        Map<String, Object> cocktail = new HashMap<>();
                        cocktail.put("id", id);
                        cocktail.put("name", name);
                        cocktail.put("description", name);

                        int numberOfIngr = random.nextInt(2, 7);

                        List<String> ingrList = new ArrayList<>();

                        for (int j = 0; j < numberOfIngr; j++) {
                            StringBuilder ingrCocktailBuilder = new StringBuilder();
                            int ingrId = random.nextInt(1, 10001);
                            ciId++;

                            //id
                            ingrCocktailBuilder.append(ciId);
                            ingrCocktailBuilder.append(',');

                            //cocktailId
                            ingrCocktailBuilder.append(id);
                            ingrCocktailBuilder.append(',');

                            //ingredientId
                            ingrCocktailBuilder.append(random.nextInt(1, 10001));
                            ingrCocktailBuilder.append(',');

                            //volume
                            ingrCocktailBuilder.append(random.nextInt(1, 7) * 10);
                            ingrCocktailBuilder.append("ml");

                            cocktailIngrDataList.add(ingrCocktailBuilder.toString());

                            ingrList.add(ingrName.get(ingrId));

                        }
                        cocktail.put("ingredients", ingrList);
                        cocktail.put("isOfficial",isOfficial);
                        writer.write(objectMapper.writeValueAsString(cocktail));
                        writer.newLine();
                    }
                }
            }
            CSVGenerator.generate(COCKTAIL_TABLE_NAME, COCKTAIL_COLUMNS, cokctailDataList);
            CSVGenerator.generate(COCKTAIL_TABLE_NAME, COCKTAIL_COLUMNS, cocktailIngrDataList);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}

