public enum Resource {
    DIAMOND,
    SAPPHIRE,
    EMERALD,
    RUBY,
    ONYX;

    public String toString(){
        switch(this){
            case DIAMOND:
                return "DIAMANT \u2666"; // DIAMANT ♦
            case SAPPHIRE:
                return "SAPHIR \u2660"; // SAPHIR ♠
            case EMERALD:
                return "EMERAUDE \u2663"; // EMERAUDE ♣
            case RUBY:
                return "RUBIS \u2665"; // RUBIS ♥
            case ONYX:
                return "ONYX \u25CF"; // ONYX ●
            default:
                return "";
        }
    }

    public String toSymbol(){
        switch(this){
            case EMERALD:
                return "\u2663E"; // ♣E
            case DIAMOND:
                return "\u2666D"; // ♦D
            case SAPPHIRE:
                return "\u2660S"; // ♠S
            case ONYX:
                return "\u25CFO"; // ●O
            case RUBY:
                return "\u2665R"; // ♥R
            default:
                return "";
        }
    }
}
