import { Form } from "react-bootstrap";

function ActionInput(props) {

    function changeHandler(event) {
        console.log(event.target.value)
        props.changeAction(event.target.value, props.who)
    }

  return (
    <Form.Group className="mb-3" controlId="formBasicPassword">
      <Form.Label>Action</Form.Label>
      <Form.Select aria-label="Blind" onChange={changeHandler}>
        <option value="NONE">None</option>
        <option value="CHECK">Check</option>
        <option value="RAISE">Raise</option>
        <option value="FOLD">Fold</option>
        <option value="BET">Bet</option>
        <option value="CALL">Call</option>
        <option value="ALL_IN">All in</option>
      </Form.Select>
    </Form.Group>
  );
}

export default ActionInput