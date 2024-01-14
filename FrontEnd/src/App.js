import React from "react";
import { ClientMainPage } from "./pages/ClientMainPage.js";
import { BrowserRouter, Route, Routes } from "react-router-dom";
import { ClientDetailsPage } from "./pages/ClientDetailsPage";
import { ClientSummaryPage } from "./pages/ClientSummaryPage";
import { MainPage } from "./pages/MainPage.js";
import { TailorPage } from "./pages/TailorPage.js";

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<MainPage />} />
        <Route path="/client" element={<ClientMainPage />} />
        <Route path="/client/:id" element={<ClientDetailsPage />} />
        <Route path="/client/:id/summary" element={<ClientSummaryPage />} />
        <Route path="/tailor" element={<TailorPage />} />
        <Route path="*" element={<MainPage />} />
      </Routes>
    </BrowserRouter>
  );
}

export default App;
