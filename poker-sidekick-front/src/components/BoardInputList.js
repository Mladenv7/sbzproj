import CardInput from "./CardInput";
import { Card, Container, CardGroup } from "react-bootstrap";
import { Form } from "react-bootstrap";

function BoardInputList(props) {
  return (
    <Container className="row justify-content-center">
      {props.numbers.map((number) => (
        <Form.Group key={number} className="mb-3 col-2" controlId="formBasicPassword">
          <Form.Label>Board Card {number}</Form.Label>
          <CardInput
            changeCard={props.changeCard}
            cards={props.cards}
            card={"b" + number}
          />
        </Form.Group>
      ))}
    </Container>
  );
}

export default BoardInputList;
