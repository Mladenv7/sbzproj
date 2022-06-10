import ReactSelect from "react-select";
import { useContext, useRef, useState } from "react";
import AlowedCardsContext from "../store/cards-context";
import context from "react-bootstrap/esm/AccordionContext";

function CardInput(props) {
  const [state, setState] = useState([]);

  const reference = useRef();

  const alowedCardsCtx = useContext(AlowedCardsContext);

  function changeHandler(event) {
    console.log(event);
    
    const cardIsAlowed = alowedCardsCtx.cardIsAlowed(event.value);
    
    if (cardIsAlowed) {

      if (state) {
        console.log("prethodno stanje:", state);
        if (state.value) {
          console.log("dodajem prethodnu kartu nazad u dozvoljene:", state);
          alowedCardsCtx.addAlowedCard(state.value);
        }
      }

      console.log("Karta je dozvoljena ", event);
      props.changeCard(event.value, props.card);
      setState(event);
      alowedCardsCtx.removeAlowedCard(event.value);
    } else {
      props.error("Ne moze!");
      setState(null);
    }
  }

  return (
    <ReactSelect
      ref={reference}
      options={alowedCardsCtx.alowedCards}
      value={state}
      onChange={changeHandler}
      formatOptionLabel={(card) => (
        <div className="country-option">
          <img
            src={card.image}
            alt={JSON.stringify(card.value)}
            width="100rem"
          />
        </div>
      )}
    />
  );
}

export default CardInput;
