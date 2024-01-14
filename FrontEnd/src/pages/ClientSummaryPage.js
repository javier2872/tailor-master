import React, { useState, useEffect } from "react";
import { NavBar } from "../components/NavBar";
import { useParams, useNavigate } from "react-router-dom";
import { Modal } from "../components/Modal";
import data from "../resources/tailor_data.json";
import { useStorageMemory } from "../hooks/useStorageMemory";
import { addJob } from "../services/http.service";
import { getATailors } from "../services/http.service";

export const ClientSummaryPage = () => {
  //hook para el boton back
  const navigate = useNavigate();

  //Bloquea el boton de finalizar pedido
  const [inputValue, setInputValue] = useState("");
  // constantes que nos ayudaran a poder mostrar toda la información que necesitamos para mostrar el resumen de todo el pedido
  const { id } = useParams();
  //Hook que vamos a utilizar para nuestro custom hook
  const [customHook, setCustomHook] = useStorageMemory(id, "");

  const [itemsOrder, setItemsOrder] = useState([]);
  const [total, setTotal] = useState(0);
  //obtener los datos un tailor en expecifico
  const [tailor, setTailor] = useState([]);
  const tailorName = tailor.name;
  const tailorSpecialties = tailor.specialties;
  const dataStorage = JSON.parse(localStorage.getItem(id));
  const date = dataStorage.date;
  const order = dataStorage.item;

  useEffect(() => {
    getATailors(id).then((d) => setTailor(d));
    console.log(tailor);
  }, []);
  useEffect(() => {
    let itemsSelected = [];
    let itemsTotal = 0;
    tailorSpecialties?.map((specialyService) => {
      order?.map((orderClient) => {
        if (specialyService.name === orderClient.name) {
          itemsSelected.push({
            name: specialyService.name,
            price: specialyService.price,
            number: orderClient.number,
          });

          itemsTotal =
            itemsTotal +
            parseInt(specialyService.price) * parseInt(orderClient.number);
        }
      });
    });
    setItemsOrder(itemsSelected);
    setTotal(itemsTotal);
  }, [tailor]);

  const handleClick = () => {
    const dataStorage = JSON.parse(localStorage.getItem(id));
    let client = {
      name: inputValue,
    };
    dataStorage.client.push(client);
    setCustomHook(dataStorage);
    addJob(dataStorage);
  };

  if (!tailor) return null;

  return (
    <div id="client_summary_page">
      <NavBar title="Cliente"></NavBar>
      <button
        id="goBack_client_summary_page"
        className="btn btn-success"
        onClick={() => navigate(-1)}
      >
        Regresar
      </button>
      <div className="container">
        <table className="table table-success table-striped">
          <thead>
            <tr>
              <th scope="col" colSpan="2">
                Resumen del pedido
              </th>
            </tr>
          </thead>
          <tbody>
            <tr id="nameTailor_client_summary_page">
              <th scope="col">Nombre</th>
              <th scope="col">{tailorName}</th>
            </tr>
            <tr id="orderTailor_client_summary_page">
              <th scope="col">Pedido</th>
              <th>
                {itemsOrder?.map((specity, index) => (
                  <div key={index}>
                    Elección: {specity.name} | Precio: {specity.price}&#8364; |
                    Cantidad:{specity.number}
                  </div>
                ))}
              </th>
            </tr>
            <tr id="dateTailor_client_summary_page">
              <th scope="col">Fecha</th>
              <th scope="col">{date}</th>
            </tr>
            <tr id="totalTailor_client_summary_page">
              <th scope="col">Total</th>
              <th scope="col">{total}&#8364;</th>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="was-validated container">
        <label htmlFor="name" className="form-label">
          Nombre del cliente
        </label>
        <input
          type="text"
          className="form-control"
          id="nameClient_client_summary_page"
          placeholder="Nombre"
          onChange={(e) => setInputValue(e.target.value)}
          required
        />
        <div className="valid-feedback">Valido</div>
        <div className="invalid-feedback">Por favor rellena este campo</div>
      </div>

      <div className="container">
        <button
          id="buttonFinish_client_summary_page"
          type="button"
          className="btn btn-success"
          data-bs-toggle="modal"
          data-bs-target="#modal_finish"
          disabled={inputValue.length === 0}
          onClick={() => {
            handleClick();
          }}
        >
          Finalizar
        </button>
        <Modal />
      </div>
    </div>
  );
};
