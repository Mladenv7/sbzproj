import { createContext, useState } from "react";

const gen_cards = [
  {
    value: { suit: "SPADES", rank: "ACE" },
    label: "ace of spades",
    image: "ace_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "ACE" },
    label: "ace of hearts",
    image: "ace_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "ACE" },
    label: "ace of diamonds",
    image: "ace_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "ACE" },
    label: "ace of clubs",
    image: "ace_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "KING" },
    label: "king of spades",
    image: "king_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "KING" },
    label: "king of hearts",
    image: "king_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "KING" },
    label: "king of diamonds",
    image: "king_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "KING" },
    label: "king of clubs",
    image: "king_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "QUEEN" },
    label: "queen of spades",
    image: "queen_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "QUEEN" },
    label: "queen of hearts",
    image: "queen_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "QUEEN" },
    label: "queen of diamonds",
    image: "queen_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "QUEEN" },
    label: "queen of clubs",
    image: "queen_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "JACK" },
    label: "jack of spades",
    image: "jack_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "JACK" },
    label: "jack of hearts",
    image: "jack_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "JACK" },
    label: "jack of diamonds",
    image: "jack_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "JACK" },
    label: "jack of clubs",
    image: "jack_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "10" },
    label: "10 of spades",
    image: "10_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "10" },
    label: "10 of hearts",
    image: "10_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "10" },
    label: "10 of diamonds",
    image: "10_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "10" },
    label: "10 of clubs",
    image: "10_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "9" },
    label: "9 of spades",
    image: "9_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "9" },
    label: "9 of hearts",
    image: "9_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "9" },
    label: "9 of diamonds",
    image: "9_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "9" },
    label: "9 of clubs",
    image: "9_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "8" },
    label: "8 of spades",
    image: "8_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "8" },
    label: "8 of hearts",
    image: "8_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "8" },
    label: "8 of diamonds",
    image: "8_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "8" },
    label: "8 of clubs",
    image: "8_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "7" },
    label: "7 of spades",
    image: "7_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "7" },
    label: "7 of hearts",
    image: "7_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "7" },
    label: "7 of diamonds",
    image: "7_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "7" },
    label: "7 of clubs",
    image: "7_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "6" },
    label: "6 of spades",
    image: "6_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "6" },
    label: "6 of hearts",
    image: "6_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "6" },
    label: "6 of diamonds",
    image: "6_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "6" },
    label: "6 of clubs",
    image: "6_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "5" },
    label: "5 of spades",
    image: "5_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "5" },
    label: "5 of hearts",
    image: "5_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "5" },
    label: "5 of diamonds",
    image: "5_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "5" },
    label: "5 of clubs",
    image: "5_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "4" },
    label: "4 of spades",
    image: "4_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "4" },
    label: "4 of hearts",
    image: "4_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "4" },
    label: "4 of diamonds",
    image: "4_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "4" },
    label: "4 of clubs",
    image: "4_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "3" },
    label: "3 of spades",
    image: "3_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "3" },
    label: "3 of hearts",
    image: "3_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "3" },
    label: "3 of diamonds",
    image: "3_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "3" },
    label: "3 of clubs",
    image: "3_of_clubs.png",
  },
  {
    value: { suit: "SPADES", rank: "2" },
    label: "2 of spades",
    image: "2_of_spades.png",
  },
  {
    value: { suit: "HEARTS", rank: "2" },
    label: "2 of hearts",
    image: "2_of_hearts.png",
  },
  {
    value: { suit: "DIAMONDS", rank: "2" },
    label: "2 of diamonds",
    image: "2_of_diamonds.png",
  },
  {
    value: { suit: "CLUBS", rank: "2" },
    label: "2 of clubs",
    image: "2_of_clubs.png",
  },
];

const removed_cards = {
  card1: [],
  card2: [],
  b1: [],
  b2: [],
  b3: [],
  b4: [],
  b5: [],
};

const AlowedCardsContext = createContext({
  alowedCards: gen_cards,
  total: gen_cards.length,
  addAlowedCard: (alowedCard) => {},
  removeAlowedCard: (notAlowedCard) => {},
  cardIsAlowed: (card) => {},
});

export function AlowedCardsContextProvider(props) {
  const [alowedCardsState, setAlowedCards] = useState(gen_cards);

  function addAlowedCardHandler(alowedCard) {
      console.log(alowedCard)
    const cards = gen_cards.filter(
      (c) => c.value.rank === alowedCard.rank && c.value.suit === alowedCard.suit
    );
    const card = cards.length > 0 ? cards[0] : null;
    setAlowedCards((prevAlowedCards) => {
      return prevAlowedCards.concat(card);
    });
  }

  function removeAlowedCardHandler(notAlowedCard) {

    console.log("trying to remove card");
    setAlowedCards((prevAlowedCards) => {
      console.log(prevAlowedCards);

      const nes = prevAlowedCards.filter(
        (c) =>
          !(
            c.value.rank === notAlowedCard.rank &&
            c.value.suit === notAlowedCard.suit
          )
      );

      console.log(nes)

      return prevAlowedCards.filter(
        (c) =>
          !(
            c.value.rank === notAlowedCard.rank &&
            c.value.suit === notAlowedCard.suit
          )
      );
    });
  }

  function cardIsAlowedHandler(card) {
    console.log(card);
    console.log(context.alowedCards);
    return context.alowedCards.some(
      (c) => c.value.rank === card.rank && c.value.suit === card.suit
    );
  }


  const context = {
    alowedCards: alowedCardsState,
    total: alowedCardsState.length,
    addAlowedCard: addAlowedCardHandler,
    removeAlowedCard: removeAlowedCardHandler,
    cardIsAlowed: cardIsAlowedHandler,
  };

  return (
    <AlowedCardsContext.Provider value={context}>
      {props.children}
    </AlowedCardsContext.Provider>
  );
}

export default AlowedCardsContext;
