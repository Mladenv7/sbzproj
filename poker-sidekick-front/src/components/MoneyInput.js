import { Form } from "react-bootstrap";

function MoneyInput(props) {
  function changeHandler(event) {
    console.log(event.target.value);
    props.changeMoney(event.target.value, props.who);
  }

  return (
    <Form.Group className="mb-3" controlId="formBasicPassword">
      <Form.Label>Money</Form.Label>
      <Form.Control
        type="number"
        min="1"
        placeholder="Money"
        onChange={changeHandler}
      />
    </Form.Group>
  );
}

export default MoneyInput;
