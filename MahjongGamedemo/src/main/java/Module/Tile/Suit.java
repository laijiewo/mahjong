package Module.Tile;

import java.io.Serializable;

public enum Suit implements Serializable {
    /**
     * The {@code Suit} enum defines the possible suits for tiles in games like Mahjong.
     * This includes traditional suits such as bamboo (TIAO), characters (WAN), and dots (TONG),
     * as well as special suits like wind directions and dragons.
     */
    TIAO, WAN, TONG, WIND, DRAGON
}