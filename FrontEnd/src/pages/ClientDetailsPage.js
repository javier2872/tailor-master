import React, { useState, useEffect } from "react";
import data from "../resources/tailor_data.json";
import { useParams, Link, useNavigate } from "react-router-dom";
import { TablePrice } from "../components/TablePrice";
import { NavBar } from "../components/NavBar";
import { TableSchedule } from "../components/TableSchedule";
import { useStorageMemory } from "../hooks/useStorageMemory";
import { getATailors } from "../services/http.service";

export const ClientDetailsPage = () => {
  //hook para el boton back
  const navigate = useNavigate();
 
  const { id } = useParams();
  //obtener los datos un tailor en expecifico
  const [tailor, setTailor] = useState([]);
  // con este useState se comprueban los pedidos
  const [orderItem, setOrderItem] = useState([]);
  // con este useState se comprueban los pedidos
  const [selectedDate, setSelectedDate] = useState("");
  //Hook que vamos a utilizar para nuestro custom hook
  const [customHook, setCustomHook] = useStorageMemory(id, "");

  const tailorSearched = (ListTailorID) => {
    return ListTailorID.availability;
  };

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

  //cuando accemos click, significa que ya hemos terminado y se guarda en memoria local
  const handleClick = () => {
    // se almacena en memoria, como texto de json
    if (orderItem && selectedDate) {
      let job = {
        idTailor: id,
        date: selectedDate,
        item: orderItem,
      };

      localStorage.setItem(id, JSON.stringify(job));
      const dataStorage = JSON.parse(localStorage.getItem(id));
      //dataStorage?.push({ date: selectedDate });
      setCustomHook(dataStorage);
    } else {
      console.log("no hay datos");
      //TODO Se quiere crear un pop-up que detecte si se han completado el formulario ModalError
    }
  };

  useEffect(() => {
    getATailors(id).then((d) => setTailor(d));
    console.log(tailor);
  },[]);

  if (!tailor) return null;

  return (
    <div>
      <NavBar title="Cliente"></NavBar>
      <button className="btn btn-success" onClick={() => navigate(-1)}>
        Go Back
      </button>
      <div className="container">
          <div key={tailor.id}>
            <div>Nombre del sastre: {tailor.name}</div>
            <div>
              <TablePrice
                specialties={tailor.specialties}
                addNewItem={addNewItem}
              ></TablePrice>
            </div>
          </div>
      </div>
      <div className="container">
        <TableSchedule
          schedule={tailorSearched(tailor)}
          addNewdate={addNewdate}
        ></TableSchedule>
      </div>
      <div className="container">
        {Object.keys(orderItem).length === 0 || selectedDate === "" ? (
          <button type="button" className="btn btn-success" disabled={true}>
            Contratar Servicios
          </button>
        ) : (
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
        )}
      </div>
    </div>
  );
};
