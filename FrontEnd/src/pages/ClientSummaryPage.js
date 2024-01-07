import React, { useState } from "react";
import { NavBar } from "../components/NavBar";
import { useParams, useNavigate } from "react-router-dom";
import { Modal } from "../components/Modal";
import data from "../resources/tailor_data.json";
import { useStorageMemory } from "../hooks/useStorageMemory";

export const ClientSummaryPage = () => {
  //hook para el boton back
  const navigate = useNavigate();

  //Bloquea el boton de finalizar pedido
  const [inputValue, setInputValue] = useState("");

  // constantes que nos ayudaran a poder mostrar toda la información que necesitamos para mostrar el resumen de todo el pedido
  const { id } = useParams();
  const itemsOrder = [];
  const tailor = data.filter((item) => item.id == id).map((obj) => obj);
  const tailorName = tailor.map((details) => details.name);
  const tailorSpecialties = tailor.map((details) => details.specialties);
  const dataStorage = JSON.parse(localStorage.getItem(id));
  const date = dataStorage.filter((item) => item.date).map((obj) => obj.date);
  const order = dataStorage.filter((item) => item.name);
  var total = 0;
  for (let i = 0; i < order.length; i++) {
    for (let j = 0; j < tailorSpecialties[0].length; j++) {
      if (tailorSpecialties[0][j].name === order[i].name) {
        itemsOrder.push({
          name: tailorSpecialties[0][j].name,
          price: tailorSpecialties[0][j].price,
          number: order[i].total,
        });
        total =
          total +
          parseInt(tailorSpecialties[0][j].price) * parseInt(order[i].total);
      }
    }
  }
  //Hook que vamos a utilizar para nuestro custom hook
  const [customHook, setCustomHook] = useStorageMemory(id, "");
  const handleClick = () => {
    const dataStorage = JSON.parse(localStorage.getItem(id));
    dataStorage?.push({ client: inputValue });
    setCustomHook(dataStorage);
  };

  return (
    <div>
      <NavBar></NavBar>
      <button className="btn btn-success" onClick={() => navigate(-1)}>
        Go Back
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
            <tr>
              <th scope="col">Nombre</th>
              <th scope="col">{tailorName}</th>
            </tr>
            <tr>
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
            <tr>
              <th scope="col">Fecha</th>
              <th scope="col">{date}</th>
            </tr>
            <tr>
              <th scope="col">Total</th>
              <th scope="col">{total}&#8364;</th>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="was-validated container">
        <label for="name" class="form-label">
          Name Client
        </label>
        <input
          type="text"
          class="form-control"
          id="name"
          placeholder="Name"
          onChange={(e) => setInputValue(e.target.value)}
          required
        />
        <div class="valid-feedback">Valido</div>
        <div class="invalid-feedback">Por favor rellena este campo</div>
      </div>

      <div className="container">
        <button
          id="button-finalizar"
          type="button"
          className="btn btn-success"
          data-bs-toggle="modal"
          data-bs-target="#finishModal"
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
