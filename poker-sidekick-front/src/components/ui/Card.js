import Card from "react-bootstrap/Card";

function Card(props) {
  retrun(
    <Card style={{ width: "50rem" }}>
      {props.children}
    </Card>
  );
}

export default Card;
