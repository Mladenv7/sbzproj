import { Form } from "react-bootstrap";

function BlindInput(props) {

    function changeHandler(event) {
        console.log(event.target.value)
        props.changeBlind(event.target.value, props.who)
    }

  return (
    <Form.Group className="mb-3" controlId="formBasicPassword">
      <Form.Label>Blind</Form.Label>
      <Form.Select aria-label="Blind" onChange={changeHandler}>
        <option value="NONE">None</option>
        <option value="DEALER">Dealer</option>
        <option value="BIG_BLIND">Big blind</option>
        <option value="SMALL_BLIND">Small blind</option>
      </Form.Select>
    </Form.Group>
  );
}

export default BlindInput