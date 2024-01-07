import React, { useState, useEffect } from "react";
import { ClientTable } from "../components/ClientTable";
import { SortBy } from "../components/SortBy";
import { NavBar } from "../components/NavBar";

import data from "../resources/tailor_data.json";

export const ClientMainPage = () => {
  // configuración del hooks useState
  const [dataTailors, setDataTailors] = useState(data);
  //hook para el valor por el que se ordena
  const [sortItems, setSortItems] = useState("none");

  const sortByNameItems = (sortItems) => {
    const options = {
      asc: [...dataTailors].sort((a, b) => (a.name < b.name ? -1 : 1)),
      desc: [...dataTailors].sort((a, b) => (a.name < b.name ? 1 : -1)),
      none: [...dataTailors],
    };
    return options[sortItems];
  };

  useEffect(() => {
    setDataTailors(sortByNameItems(sortItems));
  }, [sortItems]);

  return (
    <div className="MainPage">
      <NavBar>
        <SortBy sortItems={(sortItems) => setSortItems(sortItems)}></SortBy>
      </NavBar>
      <ClientTable tailors={dataTailors}></ClientTable>
    </div>
  );
};
