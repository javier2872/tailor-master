import React, { useState, useEffect } from "react";
import { ClientTable } from "../components/ClientTable";
import { SortBy } from "../components/SortBy";
import { NavBar } from "../components/NavBar";
import { getAllTailors } from "../services/http.service";

import data from "../resources/tailor_data.json";

export const ClientMainPage = () => {
  // configuración del hooks useState
  const [dataTailors, setDataTailors] = useState([]);
  //hook para el valor por el que se ordena
  const [sortItemSelected, setSortItemSelected] = useState("none");

  useEffect(() => {
    getAllTailors().then((d) => setDataTailors(d));
  }, []);

  useEffect(() => {
    const sortByNameItems = (itemSeleted) => {
      const options = {
        asc: [...dataTailors].sort((a, b) => (a.name < b.name ? -1 : 1)),
        desc: [...dataTailors].sort((a, b) => (a.name < b.name ? 1 : -1)),
        none: [...dataTailors],
      };
      return options[itemSeleted];
    };
    setDataTailors(sortByNameItems(sortItemSelected));
  }, [sortItemSelected]);

  if (!dataTailors) return null;

  return (
    <div id="client_Mainpage_page" className="MainPage">
      <div>
        <NavBar title="Cliente">
          <SortBy sortItems={(items) => setSortItemSelected(items)}></SortBy>
        </NavBar>
        <ClientTable tailors={dataTailors}></ClientTable>
      </div>
    </div>
  );
};
