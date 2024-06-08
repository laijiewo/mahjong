package Module.utils;
import Module.Game.MahjongGame;
import Module.Game.Player;
import Module.Rule.RuleImplementation;
import Module.Tile.NumberTile;
import Module.Tile.Tile;
import Module.Tile.Suit;
import Module.Game.GameBoard;

import java.util.*;
/**
 * The FanCalculator class calculates the fan points for a Mahjong hand based on various winning conditions.
 */
public class FanCalculator {
    List<String> HuTypes = new ArrayList<>();

    private Tile hunTile; // The wildcard tile
    private int tileCount;

    /**
     * Constructor for FanCalculator.
     *
     * @param hunTile The wildcard tile.
     * @param tileCount The number of tiles left in the wall.
     */
    public FanCalculator(Tile hunTile, int tileCount) {
        this.hunTile = hunTile;
        this.tileCount = tileCount;
    }
    /**
     * Gets the list of Hu types.
     *
     * @return The list of Hu types.
     */
    public List<String> getHuTypes() {
        return HuTypes;
    }
    /**
     * Calculates the fan points for a given hand.
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @return The total fan points.
     */
    public int calculateFan(List<Tile> hand, boolean isDealer) {
        boolean selfDrawn = selfDraw(hand,isDealer,tileCount);
        Set<String> fanTypes = new HashSet<>();
        int fan = 0;

        if (selfDrawn && fanTypes.add("normalType")) {
            HuTypes.add("normalType");
            System.out.println("normalType");
            fan += 1; // // Self-draw
        }

        if (isMenQing(hand,isDealer,tileCount) && fanTypes.add("menQing")) {
            HuTypes.add("menQing");
            System.out.println("menqing");
            fan += 1; // No melds
        }

        if (isDuiDuiHu(hand) && fanTypes.add("duiDuiHu")) {
            HuTypes.add("duiDuiHu");
            fan += 1; //All triplets
            System.out.println("duiduihu");
        }

        if (isZhuaWuKui(hand) && fanTypes.add("zhuaWuKui")) {
            HuTypes.add("zhuaWuKui");
            fan += 1; // Zhua Wu Kui
            System.out.println("zhuawukui");
        }

        if (isHaiDiLaoYue(hand, selfDrawn,tileCount) && fanTypes.add("haiDiLaoYue")) {
            HuTypes.add("haiDiLaoYue");
            fan += 1; // Haidi Laoyue
            System.out.println("haidilaoyue");
        }

        if (isYiTiaoLong(hand) && fanTypes.add("yiTiaoLong")) {
            HuTypes.add("yiTiaoLong");
            fan += 2; // Yi Tiao Long
            System.out.println("yitiaolong");
        }

        if (isQiXiaoDui(hand) && fanTypes.add("qiXiaoDui")) {
            HuTypes.add("qiXiaoDui");
            fan += 2; //  Seven pairs
            System.out.println("qixiaodui");
        }

        if (isHaoHuaQiDui(hand) && fanTypes.add("haoHuaQiDui")) {
            HuTypes.add("haoHuaQiDui");
            fan += 3; // Luxury seven pairs
            System.out.println("haohuaqidui");
        }

        if (isQingYiSe(hand) && fanTypes.add("qingYiSe")) {
            HuTypes.add("qingYiSe");
            fan += 3; //Pure color
            System.out.println("qingyise");
        }

        if (isTianHu(hand,isDealer,tileCount) && fanTypes.add("tianHu")) {
            HuTypes.add("tianHu");
            fan += 6; // Tian Hu
            System.out.println("tianhu");
        }

        if (isDiHu(hand, isDealer,tileCount) && fanTypes.add("diHu")) {
            HuTypes.add("diHu");
            fan += 5; // Di Hu
            System.out.println("dihu");
        }

        if (isRenHu(hand, isDealer,tileCount) && fanTypes.add("renHu")) {
            HuTypes.add("renHu");
            fan += 5; // Ren Hu
            System.out.println("renhu");
        }

        if (isHunGang(hand) && fanTypes.add("hunGang")) {
            HuTypes.add("hunGang");
            fan += 9; // Mixed Gang
            System.out.println("hungang");
        }

        return fan;
    }
    /**
     * Checks if the hand is self-drawn.
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is self-drawn, false otherwise.
     */
    private boolean selfDraw(List<Tile> hand,boolean isDealer,int tileCount) {
        // 自摸的判断条件：最后一张牌是自己摸的并且是自己回合
        if (isDiHu(hand,isDealer,tileCount)||isHunGang(hand)) {
            return false;
        }
        return true;
    }

    /**
     * Checks if the hand is MenQing (no melds).
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is MenQing, false otherwise.
     */
    private boolean isMenQing(List<Tile> hand,boolean isDealer,int tileCount) {
        Collections.sort(hand);
        System.out.println(hand.size());
        if(isDiHu(hand,isDealer,tileCount)||isTianHu(hand,isDealer,tileCount)||isHunGang(hand)||isRenHu(hand,isDealer,tileCount)){
            return  false;
        }


        // Check for no melds: no Pung, Chow, or exposed Kong
        int tripletCount = 0;
        int sequenceCount = 0;

        for (int i = 0; i < hand.size() - 2; i++) {
            Tile first = hand.get(i);
            Tile second = hand.get(i + 1);
            Tile third = hand.get(i + 2);

            if (first.equals(second) && second.equals(third)) {
                tripletCount++;
                i += 2;
            } else if (hand.get(i) instanceof NumberTile && hand.get(i + 1) instanceof NumberTile && hand.get(i + 2) instanceof NumberTile) {
                NumberTile tile1 = (NumberTile) hand.get(i);
                NumberTile tile2 = (NumberTile) hand.get(i + 1);
                NumberTile tile3 = (NumberTile) hand.get(i + 2);
                if (tile1.getSuit() == tile2.getSuit() && tile2.getSuit() == tile3.getSuit() &&
                        tile1.getRank() + 1 == tile2.getRank() && tile2.getRank() + 1 == tile3.getRank()) {
                    sequenceCount++;
                    i += 2;
                }
            }
        }

        return hand.size() == 14 && tripletCount + sequenceCount == 4;
    }

    /**
     * Checks if the hand is DuiDuiHu (all triplets).
     *
     * @param hand The player's hand.
     * @return True if the hand is DuiDuiHu, false otherwise.
     */
    private boolean isDuiDuiHu(List<Tile> hand) {
        // 判断是否对对胡：4个刻子和1对将牌
        System.out.println(hand.size());
        Collections.sort(hand);
        int tripletCount = 0;
        int pairCount = 0;
        if(isHunGang(hand)){
            return false;
        }
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).equals(hand.get(i + 1))) {
                if (i + 2 < hand.size() && hand.get(i).equals(hand.get(i + 2))) {
                    tripletCount++;
                    i += 2;
                } else {
                    pairCount++;
                    i++;
                }
            }
        }
        return tripletCount == 4 && pairCount == 1;
    }

    /**
     * Checks if the hand is ZhuaWuKui (only winning with a five Wan tile).
     *
     * @param hand The player's hand.
     * @return True if the hand is ZhuaWuKui, false otherwise.
     */
    private boolean isZhuaWuKui(List<Tile>hand) {
        //System.out.println(hand.size());
        // 判断是否捉五魁：和牌时只和一张“五万”
        if(isHunGang(hand)){
            return false;
        }
        Tile finalTile = hand.get(hand.size() - 1);
        if (finalTile instanceof NumberTile numberTile) {
            return numberTile.getRank() == 5 && numberTile.getSuit() == Suit.WAN;
        }
        return false;
    }

    /**
     * Checks if the hand is HaiDiLaoYue (winning by drawing the last tile).
     *
     * @param hand The player's hand.
     * @param selfDrawn Whether the hand is self-drawn.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is HaiDiLaoYue, false otherwise.
     */
    private boolean isHaiDiLaoYue(List<Tile> hand,  boolean selfDrawn, int tileCount) {
        System.out.println(hand.size());
        // 判断是否海底捞月：拿到海底牌时自摸
        if(isHunGang(hand)){
            return false;
        }
        return selfDrawn && tileCount==0;
    }

    /**
     * Checks if the hand is YiTiaoLong (a complete sequence from one to nine of the same suit).
     *
     * @param hand The player's hand.
     * @return True if the hand is YiTiaoLong, false otherwise.
     */
    private boolean isYiTiaoLong(List<Tile> hand) {
        System.out.println(hand.size());
        if(isHunGang(hand)){
            return false;
        }
        // 判断是否一条龙：同一门花色的一到九
        for (Suit suit : Suit.values()) {
            boolean isYiTiaoLong = true;
            for (int rank = 1; rank <= 9; rank++) {
                if (!containsTile(hand, new NumberTile(rank, suit))) {
                    isYiTiaoLong = false;
                    break;
                }
            }
            if (isYiTiaoLong) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the hand is QiXiaoDui (seven pairs).
     *
     * @param hand The player's hand.
     * @return True if the hand is QiXiaoDui, false otherwise.
     */
    private boolean isQiXiaoDui(List<Tile> hand) {
        System.out.println(hand.size());
        if (hand.size() != 14||isHunGang(hand)) return false;
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).equals(hand.get(i + 1))) {
                pairCount++;
                i++;
            }
        }
        return pairCount == 7;
    }

    /**
     * Checks if the hand is HaoHuaQiDui (luxury seven pairs).
     *
     * @param hand The player's hand.
     * @return True if the hand is HaoHuaQiDui, false otherwise.
     */
    private boolean isHaoHuaQiDui(List<Tile> hand) {
        System.out.println(hand.size());
        // 判断是否豪华七对：七小对和牌时，手中有4张一样的牌
        if (!isQiXiaoDui(hand)||isHunGang(hand)) return false;
        int quadCount = 0;
        for (int i = 0; i < hand.size() - 3; i++) {
            if (hand.get(i).equals(hand.get(i + 1)) &&
                    hand.get(i).equals(hand.get(i + 2)) &&
                    hand.get(i).equals(hand.get(i + 3))) {
                quadCount++;
                i += 3;
            }
        }
        return quadCount >= 1;
    }

    /**
     * Checks if the hand is QingYiSe (pure color).
     *
     * @param hand The player's hand.
     * @return True if the hand is QingYiSe, false otherwise.
     */
    private boolean isQingYiSe(List<Tile> hand) {
        System.out.println(hand.size());
        // 判断是否清一色：和牌时只有一门花色
        if(isHunGang(hand)){
            return false;
        }
        Suit suit = null;
        for (Tile tile : hand) {
            if (tile instanceof NumberTile) {
                if (suit == null) {
                    suit = tile.getSuit();
                } else if (tile.getSuit() != suit) {
                    return false;
                }
            }
        }
        return suit != null;
    }

    /**
     * Checks if the hand is TianHu (heavenly hand).
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is TianHu, false otherwise.
     */
    private boolean isTianHu(List<Tile> hand, boolean isDealer,int tileCount) {
        return isDealer && tileCount==82;
    }

    /**
     * Checks if the hand is DiHu (earthly hand).
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is DiHu, false otherwise.
     */
    private boolean isDiHu(List<Tile> hand, boolean isDealer,int tileCount) {
        System.out.println(tileCount);
        if(isRenHu(hand,isDealer,tileCount)){
            return false;
        }
        return !isDealer && tileCount>=79;
    }

    /**
     * Checks if the hand is RenHu (human hand).
     *
     * @param hand The player's hand.
     * @param isDealer Whether the player is the dealer.
     * @param tileCount The number of tiles left in the wall.
     * @return True if the hand is RenHu, false otherwise.
     */
    private boolean isRenHu(List<Tile> hand, boolean isDealer,int tileCount) {
        // 判断是否人胡：庄家打出第一张牌时点炮
        return !isDealer && tileCount==82;
    }

    /**
     * Checks if the hand is HunGang (wildcard kong).
     *
     * @param hand The player's hand.
     * @return True if the hand is HunGang, false otherwise.
     */
    private boolean isHunGang(List<Tile> hand) {
        // 判断是否混杠：手牌有4张混牌时任何牌型都可以和牌
        return hand.stream().filter(tile -> tile.equals(hunTile)).count() == 4;
    }

    /**
     * Removes a tile from the hand.
     *
     * @param hand The player's hand.
     * @param tile The tile to be removed.
     */
    private void removeTile(List<Tile> hand, Tile tile) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(tile)) {
                hand.remove(i);
                return;
            }
        }
    }

    /**
     * Checks if the hand contains a specific tile.
     *
     * @param hand The player's hand.
     * @param tile The tile to check for.
     * @return True if the hand contains the tile, false otherwise.
     */
    private boolean containsTile(List<Tile> hand, Tile tile) {
        for (Tile t : hand) {
            if (t.equals(tile)) {
                return true;
            }
        }
        return false;
    }
}
