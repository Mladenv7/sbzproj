import ReactSelect from "react-select"

function CardInput(props) {


    function changeHandler(event) {
        console.log(event.value)
        props.changeCard(event.value, props.card)
    }

    //   ref={props.ref}

    return <ReactSelect
              options={props.cards}
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
}

export default CardInput