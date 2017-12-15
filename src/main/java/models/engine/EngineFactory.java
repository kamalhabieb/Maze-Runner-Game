package models.engine;

public class EngineFactory  {
    public static final String DEFAULT = "default";
    public static final String FAST = "fast" ;
    public static final String SLOW = "slow" ;
    public static final String GOD  = "god";
    public static final String TROLL = "troll";
    public static final String BLIND = "blind";

    public static Engine getInstance(String gameMode) {
        switch(gameMode){

            case DEFAULT: return new DefaultEngine();

            case FAST   : return new FastEngine();

            case SLOW   : return new SlowEngine();

            case TROLL  : return new TrollEngine();

            case GOD    : return new GodEngine();

            case BLIND  : return new BlindEngine();

            default     : return new DefaultEngine();
        }
    }
}
