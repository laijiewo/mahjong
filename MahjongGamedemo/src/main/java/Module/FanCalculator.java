package Module;
import java.util.*;

public class FanCalculator {

    private Tile hunTile; // The wildcard tile (混儿牌)

    public FanCalculator(Tile hunTile) {
        this.hunTile = hunTile;
    }

    public int calculateFan(List<Tile> hand, boolean isPlayerTurn, boolean isDealer, Tile finalTile) {
        boolean selfDrawn = selfDraw(finalTile, isPlayerTurn);
        Set<String> fanTypes = new HashSet<>();
        int fan = 0;

        if (selfDrawn && fanTypes.add("selfDrawn")) {
            fan += 1; // 自摸
        }

        if (isMenQing(hand) && fanTypes.add("menQing")) {
            fan += 1; // 门清
        }

        if (isDuiDuiHu(hand) && fanTypes.add("duiDuiHu")) {
            fan += 1; // 对对胡
        }

        if (isQuanQiuRen(hand, finalTile) && fanTypes.add("quanQiuRen")) {
            fan += 1; // 全求人
        }

        if (isZhuaWuKui(finalTile) && fanTypes.add("zhuaWuKui")) {
            fan += 1; // 捉五魁
        }

        if (isHaiDiLaoYue(hand, finalTile, selfDrawn) && fanTypes.add("haiDiLaoYue")) {
            fan += 1; // 海底捞月
        }

        if (isYiTiaoLong(hand) && fanTypes.add("yiTiaoLong")) {
            fan += 2; // 一条龙
        }

        if (isQiXiaoDui(hand) && fanTypes.add("qiXiaoDui")) {
            fan += 2; // 七小对
        }

        if (isHaoHuaQiDui(hand) && fanTypes.add("haoHuaQiDui")) {
            fan += 3; // 豪华七对
        }

        if (isQingYiSe(hand) && fanTypes.add("qingYiSe")) {
            fan += 3; // 清一色
        }

        if (isTianHu(hand, isDealer) && fanTypes.add("tianHu")) {
            fan += 6; // 天和
        }

        if (isDiHu(hand, isDealer) && fanTypes.add("diHu")) {
            fan += 5; // 地和
        }

        if (isRenHu(hand, isDealer) && fanTypes.add("renHu")) {
            fan += 5; // 人和
        }

        if (isHunGang(hand) && fanTypes.add("hunGang")) {
            fan += 9; // 混杠
        }

        return fan;
    }

    private boolean selfDraw(Tile finalTile, boolean isPlayerTurn) {
        // 自摸的判断条件：最后一张牌是自己摸的并且是自己回合
        return isPlayerTurn && finalTile != null;
    }

    private boolean isMenQing(List<Tile> hand) {
        // 判断是否门清：没有碰、吃、明杠
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

    private boolean isDuiDuiHu(List<Tile> hand) {
        // 判断是否对对胡：4个刻子和1对将牌
        int tripletCount = 0;
        int pairCount = 0;

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

    private boolean isQuanQiuRen(List<Tile> hand, Tile finalTile) {
        // 判断是否全求人：碰、吃4副刻子或顺子，最后留一张牌钓将和牌
        return hand.size() == 1 && hand.get(0).equals(finalTile);
    }

    private boolean isZhuaWuKui(Tile finalTile) {
        // 判断是否捉五魁：和牌时只和一张“五万”
        if (finalTile instanceof NumberTile) {
            NumberTile numberTile = (NumberTile) finalTile;
            return numberTile.getRank() == 5 && numberTile.getSuit() == Suit.WAN;
        }
        return false;
    }

    private boolean isHaiDiLaoYue(List<Tile> hand, Tile finalTile, boolean selfDrawn) {
        // 判断是否海底捞月：拿到海底牌时自摸
        return selfDrawn && hand.size() == 14;
    }

    private boolean isYiTiaoLong(List<Tile> hand) {
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

    private boolean isQiXiaoDui(List<Tile> hand) {
        // 判断是否七小对：14张牌为7个对子
        if (hand.size() != 14) return false;
        int pairCount = 0;
        for (int i = 0; i < hand.size() - 1; i++) {
            if (hand.get(i).equals(hand.get(i + 1))) {
                pairCount++;
                i++;
            }
        }
        return pairCount == 7;
    }

    private boolean isHaoHuaQiDui(List<Tile> hand) {
        // 判断是否豪华七对：七小对和牌时，手中有4张一样的牌
        if (!isQiXiaoDui(hand)) return false;
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

    private boolean isQingYiSe(List<Tile> hand) {
        // 判断是否清一色：和牌时只有一门花色
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

    private boolean isTianHu(List<Tile> hand, boolean isDealer) {
        // 判断是否天胡：庄家发完牌就和牌
        return isDealer && hand.size() == 14;
    }

    private boolean isDiHu(List<Tile> hand, boolean isDealer) {
        // 判断是否地胡：闲家抓到第一张牌就和牌
        return !isDealer && hand.size() == 14;
    }

    private boolean isRenHu(List<Tile> hand, boolean isDealer) {
        // 判断是否人胡：庄家打出第一张牌时点炮
        return !isDealer && hand.size() == 13;
    }

    private boolean isHunGang(List<Tile> hand) {
        // 判断是否混杠：手牌有4张混牌时任何牌型都可以和牌
        return hand.stream().filter(tile -> tile.equals(hunTile)).count() == 4;
    }

    private void removeTile(List<Tile> hand, Tile tile) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i).equals(tile)) {
                hand.remove(i);
                return;
            }
        }
    }

    private boolean containsTile(List<Tile> hand, Tile tile) {
        for (Tile t : hand) {
            if (t.equals(tile)) {
                return true;
            }
        }
        return false;
    }
}
