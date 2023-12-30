import React, { useState, useEffect } from "react";
import { ClientTable } from "../components/ClientTable";
import { SortBy } from "../components/SortBy";
import { NavBar } from "../components/NavBar";

import data from "../resources/client_data.json";

export const ClientMainPage = () => {
  // configuración del hooks useState
  const [dataClients, setDataClients] = useState(data);
  //hook para el valor por el que se ordena
  const [sortItems, setSortItems] = useState("none");

  const sortByNameItems = (sortItems) => {
    const options = {
      asc: [...dataClients].sort((a, b) => (a.name < b.name ? -1 : 1)),
      desc: [...dataClients].sort((a, b) => (a.name < b.name ? 1 : -1)),
      none: [...dataClients],
    };
    return options[sortItems];
  };

  useEffect(() => {
    setDataClients(sortByNameItems(sortItems));
  }, [sortItems]);

  return (
    <div className="MainPage">
      <NavBar>
        <SortBy sortItems={(sortItems) => setSortItems(sortItems)}></SortBy>
      </NavBar>
      <ClientTable clients={dataClients}></ClientTable>
    </div>
  );
};
