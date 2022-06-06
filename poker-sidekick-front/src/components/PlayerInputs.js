import { Card, Container, CardGroup } from "react-bootstrap";
import ActionInput from "./ActionInput";
import BlindInput from "./BlindInput";
import MoneyInput from "./MoneyInput";

function PlayerInputs(props) {

  return (
    <Card className="m-3 p-2 border">
      <h3 className="text-center">Player {props.number + 1}</h3>
      <MoneyInput changeMoney={props.moneyChangeHandler} who={props.state.players[props.number]} />
      <ActionInput changeAction={props.actionChangeHandler} who={props.state.players[props.number]} />
      <BlindInput changeBlind={props.blindChangeHandler} who={props.state.players[props.number]} />
    </Card>
  );
}

export default PlayerInputs;
