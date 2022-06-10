import NewTableStateForm from "../components/NewTableStateForm";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from "react-toastify";
import "react-toastify/dist/ReactToastify.css";

function NewTableStatePage() {
  const navigate = useNavigate();

  async function addMeetupHandler(tableData) {
    fetch("http://localhost:8080/api/current-state", {
      method: "POST",
      body: JSON.stringify(tableData),
      headers: {
        "Content-Type": "application/json",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        console.log(data);
        
        toast(data.action, {
          position: "top-right",
          autoClose: 10000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
        toast("Detailed: " + data.explanation, {
          position: "top-right",
          autoClose: 10000,
          hideProgressBar: false,
          closeOnClick: true,
          pauseOnHover: true,
          draggable: true,
          progress: undefined,
        });
      });
      await new Promise(r => setTimeout(r, 10000));
  }

  function validationErrorHandler(message) {
    toast("Error: " + message, {
      position: "top",
      autoClose: 5000,
      hideProgressBar: false,
      closeOnClick: true,
      pauseOnHover: true,
      draggable: true,
      progress: undefined,
    });
  }

  return (
    <section>
      <h1>Send Current Table State</h1>
      <ToastContainer
        position="top"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
      <NewTableStateForm onAddMeetup={addMeetupHandler} error={validationErrorHandler}/>
      <ToastContainer
        position="top-right"
        autoClose={5000}
        hideProgressBar={false}
        newestOnTop={false}
        closeOnClick
        rtl={false}
        pauseOnFocusLoss
        draggable
        pauseOnHover
      />
    </section>
  );
}

export default NewTableStatePage;
