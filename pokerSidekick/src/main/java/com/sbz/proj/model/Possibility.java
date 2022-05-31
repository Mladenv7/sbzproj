package com.sbz.proj.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
@Setter
public class Possibility implements Comparable<Possibility>{
    public List<Card> cards; // 5 karata
    public Card card1, card2, card3, card4, card5;
    public PokerHand pokerHand; // sta imamo
    public boolean weKnow;
    public HashMap<String, Integer> additionalInfo;

    public Possibility(List<Card> cards, PokerHand pokerHand) {
        this.cards = cards.stream().sorted(Card::compareTo).collect(Collectors.toList());
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
        this.card3 = cards.get(2);
        this.card4 = cards.get(3);
        this.card5 = cards.get(4);

        this.pokerHand = pokerHand;
        this.weKnow = true;
    }
     public Possibility() {
         this.cards = new ArrayList<>();
         this.pokerHand = PokerHand.UNKNOWN;
         this.additionalInfo = new HashMap<>();
         this.weKnow = false;
     }

    public boolean sameSuit() {
        return cards.stream().filter(c -> c.getSuit() != cards.get(0).getSuit()).count() == 0;
    }

    public long howManyWithRank(Rank rank) {
        return cards.stream().filter(c -> c.getRank() == rank).count();
    }

    public long howManyWithSuit(Suit suit) {
        return cards.stream().filter(c -> c.getSuit() == suit).count();
    }

    public List<Suit> getAllSuits() {
        return cards.stream().map(Card::getSuit).collect(Collectors.toList());
    }

    public void setupCards() {
        this.cards = cards.stream().sorted(Card::compareTo).collect(Collectors.toList());
        this.card1 = cards.get(0);
        this.card2 = cards.get(1);
        this.card3 = cards.get(2);
        this.card4 = cards.get(3);
        this.card5 = cards.get(4);
    }

    @Override
    public int compareTo(Possibility o) {
        if (this.pokerHand != o.pokerHand)
            return this.pokerHand.strength.compareTo(o.pokerHand.strength);
        else
            return this.cards.get(4).compareTo(o.cards.get(4));
    }

    
    public int compareToComplicated(Possibility o) {
        boolean sameCards = true; // a moze i rucno da poredim 5 karata
        for (int i = 0; i < 5; i++) {
            if (this.cards.get(i) != o.cards.get(i)) {
                sameCards = false;
                break;
            }
        }
        if (sameCards) return 0;
        // this > o
        if (this.pokerHand.strength > o.pokerHand.strength)
            return 1;
        // this == o
        else if (this.pokerHand.strength.equals(o.pokerHand.strength)) {
            switch (this.pokerHand) {
                case HIGH_CARD:
                    return Card.compareLists(this.cards, o.cards);
                case PAIR:
                    return samePair(o);
                case TWO_PAIRS:
                    return sameTwoPairs(o);
                case THREE_OF_A_KIND:
                    return sameThreeOfAKind(o);
                case STRAIGHT: // gledamo rank najjacih karata, a to su poslednje
                case FLUSH: // gledamo rank najjacih karata, a to su poslednje
                case STRAIGHT_FLUSH:
                    return sameStraight_Flush_StraightFlush(o);
                case FULL_HOUSE:
                    return sameFullHouse(o);
                case FOUR_OF_A_KIND:
                    return sameFourOfAKind(o);
                case ROYAL_FLUSH:
                    return 0;
            }

            return 0;
        }
        // this < o
        else return -1;
    }

    private int sameFourOfAKind(Possibility o) {
        // todo nabavi najjacu kartu u cetvorci
        Card bestQuatroCard1 = this.cards.get(0);
        Card bestQuatroCard2 = o.cards.get(0);
        // porede se prvo cetvorke
        if (bestQuatroCard1.compareTo(bestQuatroCard2) > 0)
            return 1;
        else if (bestQuatroCard1.compareTo(bestQuatroCard2) < 0)
            return -1;
        else {
            // todo nabavi koja nije u cetvorci
            Card bestKickerCard1 = this.cards.get(4);
            Card bestKickerCard2 = o.cards.get(4);
            if (bestKickerCard1.compareTo(bestKickerCard2) > 0)
                return 1;
            else if (bestKickerCard1.compareTo(bestKickerCard2) < 0)
                return -1;
            return 0;
        }
    }

    private int sameFullHouse(Possibility o) {
        Card bestTripsCard1 = this.cards.get(additionalInfo.get("trips-index") + 2); // najjace karte u trojci
        Card bestTripsCard2 = o.cards.get(additionalInfo.get("trips-index") + 2);
        // porede se prvo trojke
        if (bestTripsCard1.compareTo(bestTripsCard2) != 0)
            return bestTripsCard1.compareTo(bestTripsCard2);
        else {
            Card bestPairCard1 = this.cards.get(additionalInfo.get("pair-index") + 1); // najjaca kartu u dvojci
            Card bestPairCard2 = o.cards.get(additionalInfo.get("pair-index") + 1);
//                        if (bestPairCard1.compareTo(bestPairCard2) != 0)
            return bestPairCard1.compareTo(bestPairCard2);
//                        return 0;
        }
    }

    private int sameStraight_Flush_StraightFlush(Possibility o) {
        // gledamo rank najjacih karata a to su poslednje
        // a2345 vs 23456 vs 34567 vs 45678 etc
        List<Integer> ranks1 = new ArrayList<>();
        List<Integer> ranks2 = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            if (this.cards.get(i).getRank() == Rank.ACE)
                ranks1.add(1);
            else
                ranks1.add(this.cards.get(i).getRank().getRank());
            //
            if (o.cards.get(i).getRank() == Rank.ACE)
                ranks2.add(1);
            else
                ranks2.add(o.cards.get(i).getRank().getRank());
        }

        ranks1.sort(Integer::compareTo);
        ranks2.sort(Integer::compareTo);

        return ranks1.get(4).compareTo(ranks2.get(4));
    }

    private int sameThreeOfAKind(Possibility o) {
        Card trips1 = this.cards.get(additionalInfo.get("trips-index") + 2);
        Card trips2 = o.cards.get(additionalInfo.get("trips-index") + 2);

        if (trips1.compareTo(trips2) != 0)
            return trips1.compareTo(trips2);
        else
            return Card.compareLists(this.cards, o.cards);
    }

    private int sameTwoPairs(Possibility o) {
        // poredimo najvece parove (drugi par)
        Card strongestCard2Pair1 = this.cards.get(this.additionalInfo.get("pair2-index") + 1);
        Card strongestCard2Pair2 = o.cards.get(this.additionalInfo.get("pair2-index") + 1);

        if (strongestCard2Pair1.compareTo(strongestCard2Pair2) != 0)
            return strongestCard2Pair1.compareTo(strongestCard2Pair2);
        else {
            Card strongestCard1Pair1 = this.cards.get(additionalInfo.get("pair1-index") + 1); // najjaca kartu u dvojci
            Card strongestCard1Pair2 = o.cards.get(additionalInfo.get("pair1-index") + 1);
            if (strongestCard1Pair1.compareTo(strongestCard1Pair2) != 0)
                return strongestCard1Pair1.compareTo(strongestCard1Pair2);
            else {
                Card kickerCard1 = this.cards.get(additionalInfo.get("kicker-index"));
                Card kickerCard2 = o.cards.get(additionalInfo.get("kicker-index"));
                return kickerCard1.compareTo(kickerCard2);
            }
        }
    }

    private int samePair(Possibility o) {
        Card pair1 = this.cards.get(this.additionalInfo.get("pair-index") + 1);
        Card pair2 = o.cards.get(this.additionalInfo.get("pair-index") + 1);

        if (pair1.compareTo(pair2) != 0)
            return pair1.compareTo(pair2);
        else
            return Card.compareLists(this.cards, o.cards);
    }

    public static List<Possibility> makeAllPossibilities(List<Card> cards) {
        List<Possibility> possibilities = new ArrayList<>();
        List<int[]> combinationsOfIndexes = generateAllIndexCombinations(cards.size(), 5);
        for (int[] combination : combinationsOfIndexes) {
            List<Card> cardList = new ArrayList<>();
            for (int i = 0; i < combination.length; i++){
                cardList.add(cards.get(combination[i]));
            }
            Possibility p = new Possibility();
            p.cards = cardList;
            p.setupCards();
            possibilities.add(p);
            //System.out.println(cardList);
        }
        return possibilities;
    }

    public static List<int[]> generateAllIndexCombinations(int n, int r) {
        List<int[]> combinations = new ArrayList<>();
        int[] combination = new int[r];

        // initialize with lowest lexicographic combination
        for (int i = 0; i < r; i++) {
            combination[i] = i;
        }

        while (combination[r - 1] < n) {
            combinations.add(combination.clone());

            // generate next combination in lexicographic order
            int t = r - 1;
            while (t != 0 && combination[t] == n - r + t) {
                t--;
            }
            combination[t]++;
            for (int i = t + 1; i < r; i++) {
                combination[i] = combination[i - 1] + 1;
            }
        }

        return combinations;
    }

}
