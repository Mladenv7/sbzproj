import NewTableStateForm from "../components/NewTableStateForm";
import { useNavigate } from "react-router-dom";
import { ToastContainer, toast } from 'react-toastify';
  import 'react-toastify/dist/ReactToastify.css';

function NewTableStatePage() {
  const navigate = useNavigate();

  function addMeetupHandler(tableData) {
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
        alert(JSON.stringify(data));
        toast.success(data);
        navigate("/");
      });
  }

  return (
    <section>
      <h1>Send Current Table State</h1>
      <NewTableStateForm onAddMeetup={addMeetupHandler} />
      <ToastContainer />
    </section>
  );
}

export default NewTableStatePage;
