import { useRef, useState } from "react";
import Form from "react-bootstrap/Form";
import Button from "react-bootstrap/Button";
import { Card, Container, CardGroup } from "react-bootstrap";
import CardInput from "./CardInput";
import BoardInputList from "./BoardInputList";
import BlindInput from "./BlindInput";
import MoneyInput from "./MoneyInput";
import PlayerInputs from "./PlayerInputs";

function NewTableStateForm(props) {
  const [form, setForm] = useState([]);

  function submitHandler(event) {
    event.preventDefault(); // prevent submition

    console.log("SALJEM: ", state);
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

    console.log(cardName, newValue)

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

  const empty_state = {
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
    <Form value={form} onSubmit={submitHandler} onChange={() => setForm(state)}>
      <Container>
        <Form.Group className="mb-3" controlId="formBasicPassword">
          <Form.Label>Game Stage</Form.Label>
          <Form.Select aria-label="Blind" onChange={stageChangeHandler}>
            <option value="PRE_FLOP">Pre flop</option>
            <option value="FLOP">Flop</option>
            <option value="TURN">Turn</option>
            <option value="RIVER">River</option>
          </Form.Select>
        </Form.Group>

        <BoardInputList
          numbers={["1", "2", "3", "4", "5"]}
          changeCard={cardChangeHandler}
        />
      </Container>

      <hr />

      <Form.Text className="text-lg">You</Form.Text>

      <Card className="m-3 mb-4 p-2 bg-light">
        <h3 className="text-center">Player 1 [Main]</h3>
        <MoneyInput changeMoney={moneyChangeHandler} who={state.players[0]} />

        <Container className="row justify-content-center">
          <Form.Group className="mb-3 col-2" controlId="formBasicPassword">
            <Form.Label>Your First Card</Form.Label>
            <CardInput
              error={props.error}
              changeCard={cardChangeHandler}
              card="card1"
            />
          </Form.Group>
          <Form.Group className="mb-3 col-2" controlId="formBasicPassword">
            <Form.Label>Your Second Card</Form.Label>
            <CardInput
            error={props.error}
              changeCard={cardChangeHandler}
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
