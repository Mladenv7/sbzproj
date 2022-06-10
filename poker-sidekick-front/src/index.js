import React from "react";
import ReactDOM from "react-dom/client";
import "./index.css";
import App from "./App";
import { BrowserRouter } from "react-router-dom";
import { AlowedCardsContextProvider } from "./store/cards-context";

const root = ReactDOM.createRoot(document.getElementById("root"));
root.render(
  <AlowedCardsContextProvider>
    <BrowserRouter>
      <App />
    </BrowserRouter>
  </AlowedCardsContextProvider>
);
