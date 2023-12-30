import React from "react";
import { NavBar } from "../components/NavBar";
import { useParams, useNavigate } from "react-router-dom";
import { Modal } from "../components/Modal";
import data from "../resources/client_data.json";

export const ClientSummaryPage = () => {
  //hook para el boton back
  const navigate = useNavigate();

  // constantes que nos ayudaran a poder mostrar toda la información que necesitamos para mostrar el resumen de todo el pedido
  const { id } = useParams();
  const itemsOrder = [];
  const client = data.filter((item) => item.id == id).map((obj) => obj);
  const clientName = client.map((details) => details.name);
  const clientSpecialties = client.map((details) => details.specialties);
  const dataStorage = JSON.parse(localStorage.getItem(id));
  const date = dataStorage.filter((item) => item.date).map((obj) => obj.date);
  const order = dataStorage.filter((item) => item.name);
  var total = parseInt(client.map((details) => details.price));
  for (let i = 0; i < order.length; i++) {
    for (let j = 0; j < clientSpecialties[0].length; j++) {
      if (clientSpecialties[0][j].name == order[i].name) {
        itemsOrder.push({
          name: clientSpecialties[0][j].name,
          price: clientSpecialties[0][j].price,
          number: order[i].total,
        });
        total =
          total +
          parseInt(clientSpecialties[0][j].price) * parseInt(order[i].total);
      }
    }
  }

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
            <th scope="col">{clientName}</th>
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
      <div className="container">
      <button
        type="button"
        className="btn btn-success"
        data-bs-toggle="modal"
        data-bs-target="#finishModal"
      >
        Finalizar
      </button>
      <Modal />
      </div>
    </div>
  );
};