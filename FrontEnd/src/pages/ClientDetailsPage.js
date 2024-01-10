import React, { useState } from "react";
import data from "../resources/tailor_data.json";
import { useParams, Link, useNavigate } from "react-router-dom";
import { TablePrice } from "../components/TablePrice";
import { NavBar } from "../components/NavBar";
import { TableSchedule } from "../components/TableSchedule";
import { useStorageMemory } from "../hooks/useStorageMemory";

export const ClientDetailsPage = () => {
  //hook para el boton back
  const navigate = useNavigate();
  //obtener los datos un tailor en expecifico
  const { id } = useParams();
  const tailor = data.filter((dataItem) => dataItem.id == id);
  const tailorSearched = (ListTailorID) => {
    return ListTailorID.filter((item) => item.id == id).map(
      (obj) => (obj = obj.availability)
    );
  };
  // con este useState se comprueban los pedidos
  const [orderItem, setOrderItem] = useState([]);
  // con este useState se comprueban los pedidos
  const [selectedDate, setSelectedDate] = useState("");
  // aqui se controla que solo haya un numero de pedido por item
  const addNewItem = (itemName, number) => {
    if (orderItem.find((it) => it.name == itemName)) {
      orderItem.map((item) => {
        if (item.name === itemName) {
          item.total = number;
        }
      });
    } else {
      setOrderItem([...orderItem, { name: itemName, total: number }]);
    }
  };
  // aqui se controla que solo haya un numero de pedido por item
  const addNewdate = (date) => {
    setSelectedDate(date);
  };
  //Hook que vamos a utilizar para nuestro custom hook
  const [customHook, setCustomHook] = useStorageMemory(id, "");
  //cuando accemos click, significa que ya hemos terminado y se guarda en memoria local
  const handleClick = () => {
    // se almacena en memoria, como texto de json
    if (orderItem && selectedDate) {
      localStorage.setItem(id, JSON.stringify(orderItem));
      const dataStorage = JSON.parse(localStorage.getItem(id));
      dataStorage?.push({ date: selectedDate });
      setCustomHook(dataStorage);
    } else {
      console.log("no hay datos");
      //TODO Se quiere crear un pop-up que detecte si se han completado el formulario ModalError
    }
  };

  return (
    <div>
      <NavBar title="Cliente"></NavBar>
      <button className="btn btn-success" onClick={() => navigate(-1)}>
        Go Back
      </button>
      <div className="container">
        {tailor?.map((detailsTailor) => (
          <div key={detailsTailor.id}>
            <div>Nombre {detailsTailor.name}</div>
            <div>
              <TablePrice
                specialties={detailsTailor.specialties}
                addNewItem={addNewItem}
              ></TablePrice>
            </div>
          </div>
        ))}
      </div>
      <div className="container">
        <TableSchedule
          schedule={tailorSearched(data)[0]}
          addNewdate={addNewdate}
        ></TableSchedule>
      </div>
      <div className="container">
        <Link to={`/client/${id}/summary`}>
          <button
            type="button"
            className="btn btn-success"
            onClick={() => {
              setCustomHook(orderItem);
              handleClick();
            }}
          >
            Contratar Servicios
          </button>
        </Link>
      </div>
    </div>
  );
};
