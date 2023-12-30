import React from "react";
import { Link } from "react-router-dom";

// extraemos las propiedades id, price
export const Card = ({ id, name, description }) => {
  return (
    <div
      className="card card-just-text"
      data-background="color"
      data-color="green"
      data-radius="none"
    >
      <div className="card-just-text">
        <div className="content">
          <h6 className="category ">{name}</h6>
          <p className="description">Descripcion: {description}</p>
        </div>
      </div>
      <Link to={`/client/${id}`} className="text-center">
        <p className="btn btn-success">Detalles</p>
      </Link>
    </div>
  );
};
