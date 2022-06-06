import { useRef } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Card, Container, CardGroup } from "react-bootstrap";
import CardInput from "./CardInput";
import BoardInputList from "./BoardInputList";
import BlindInput from "./BlindInput";
import MoneyInput from "./MoneyInput";
import PlayerInputs from "./PlayerInputs";

function NewTableStateForm(props) {
function submitHandler(event) {
    event.preventDefault(); // prevent submition

    // state.players[0].card1 = card1InputRef.current.value;
    // state.players[0].card2 = card2InputRef.current.value;
    // state.players[0].blind = blindInputRef.current.value;
    // state.players[0].money = moneyInputRef.current.value;

    // state.players[1].blind = blind2InputRef.current.value;
    // state.players[1].money = money2InputRef.current.value;

    // state.players[2].blind = blind3InputRef.current.value;
    // state.players[2].money = money3InputRef.current.value;

    // state.players[3].blind = blind4InputRef.current.value;
    // state.players[3].money = money4InputRef.current.value;

    // state.players[4].blind = blind5InputRef.current.value;
    // state.players[4].money = money5InputRef.current.value;

    // state.currentStage = stageInputRef.current.value;
    // state.board = [];
    // if (state.currentStage === "PRE_FLOP") state.board = [];
    // else if (state.currentStage === "FLOP")
    //   state.board = [
    //     boardC1InputRef.current.value,
    //     boardC2InputRef.current.value,
    //     boardC3InputRef.current.value,
    //   ];
    // else if (state.currentStage === "TURN")
    //   state.board = [
    //     boardC1InputRef.current.value,
    //     boardC2InputRef.current.value,
    //     boardC3InputRef.current.value,
    //     boardC4InputRef.current.value,
    //   ];
    // else
    //   state.board = [
    //     boardC1InputRef.current.value,
    //     boardC2InputRef.current.value,
    //     boardC3InputRef.current.value,
    //     boardC4InputRef.current.value,
    //     boardC5InputRef.current.value,
    //   ];

    console.log(state);
    props.onAddMeetup(state);
  }

  function stageChangeHandler(event) {
    state.currentStage = event.target.value;
    console.log(state);
    console.log(state.currentStage === "FLOP");
  }

  function cardChangeHandler(newValue, cardName) {
    if (cardName === "card1") state.players[0].card1 = newValue;
    else if (cardName === "card2") state.players[0].card2 = newValue;
    else if (cardName.includes("b")) {
      const num = Number.parseInt(cardName.split("b")[1]) - 1;
      state.board[num] = newValue;
    }

    console.log(state);
  }

  function blindChangeHandler(newValue, who) {
    who.blind = newValue;

    console.log(state);
  }

  function moneyChangeHandler(newValue, who) {
    who.money = Number.parseInt(newValue);

    console.log(state);
  }

  function actionChangeHandler(newValue, who) {
    who.action[0] = newValue;

    console.log(state);
  }

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

  const state = {
    players: [
      {
        card1: { rank: "", suit: "" },
        card2: { rank: "", suit: "" },
        action: ["NONE"],
        money: 0,
        blind: "NONE",
      },
      {
        card1: null,
        card2: null,
        action: ["NONE"],
        money: 0,
        blind: "NONE",
      },
      {
        card1: null,
        card2: null,
        action: ["NONE"],
        money: 0,
        blind: "NONE",
      },
      {
        card1: null,
        card2: null,
        action: ["NONE"],
        money: 0,
        blind: "NONE",
      },
      {
        card1: null,
        card2: null,
        action: ["NONE"],
        money: 0,
        blind: "NONE",
      },
    ],
    currentStage: "PRE_FLOP",
    board: [],
  };

  return (
    <Form onSubmit={submitHandler}>
      <Container>
      <Form.Group className="mb-3" controlId="formBasicPassword">
        <Form.Label>Game Stage</Form.Label>
        <Form.Select aria-label="Blind" onChange={stageChangeHandler}>
          <option value="FLOP">Flop</option>
          <option value="PRE_FLOP">Pre flop</option>
          <option value="TURN">Turn</option>
          <option value="RIVER">River</option>
        </Form.Select>
      </Form.Group>

      <BoardInputList
        numbers={["1", "2", "3", "4", "5"]}
        changeCard={cardChangeHandler}
        cards={gen_cards}
      />
      </Container>

      <Form.Text className="text-lg">You</Form.Text>

      <Card className="m-3 p-2 bg-light">
        <h3 className="text-center">Player 1 [Main]</h3>
        <MoneyInput changeMoney={moneyChangeHandler} who={state.players[0]} />

        <Container className="row justify-content-center">
          <Form.Group className="mb-3 col-2" controlId="formBasicPassword">
            <Form.Label>Your First Card</Form.Label>
            <CardInput
              changeCard={cardChangeHandler}
              cards={gen_cards}
              card="card1"
            />
          </Form.Group>
          <Form.Group className="mb-3 col-2" controlId="formBasicPassword">
            <Form.Label>Your Second Card</Form.Label>
            <CardInput
              changeCard={cardChangeHandler}
              cards={gen_cards}
              card="card2"
            />
          </Form.Group>
        </Container>

        <BlindInput changeBlind={blindChangeHandler} who={state.players[0]} />
      </Card>

      <hr />

      <Form.Text className="text-lg">Other plyers</Form.Text>
      <CardGroup>
        {[1, 2, 3, 4].map((num) => (
          <PlayerInputs
            key={num}
            moneyChangeHandler={moneyChangeHandler}
            actionChangeHandler={actionChangeHandler}
            blindChangeHandler={blindChangeHandler}
            number={num}
            state={state}
          />
        ))}
      </CardGroup>

      <div className="d-grid gap-2">
        <Button variant="primary" type="submit" size="lg m-5">
          Send
        </Button>
      </div>
    </Form>
  );
}

export default NewTableStateForm;
