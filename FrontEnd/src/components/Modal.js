import React from "react";
import { Link } from "react-router-dom";

export const Modal = () => {
  //cuando accemos click, significa que ya hemos terminado y se limpia la memoria local
  const handleClick = () => {
    localStorage.clear();
  };

  return (
    <div
      className="modal fade"
      id="finishModal"
      tabindex="-1"
      aria-labelledby="finishModalLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header bg-success text-light bg-opacity-50r">
            <h5 className="modal-title">Pedido Tramitando</h5>
            <button
              type="button"
              className="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div className="modal-body">
            <p>Gracias por confiar en nosotros.</p>
            <p>En breve recibirá más información.</p>
          </div>
          <div className="modal-footer">
            <Link to={`/`}>
              <button
                type="button"
                onClick={() => handleClick()}
                className="btn btn-success font-weight-bold"
                data-bs-dismiss="modal"
              >
                Aceptar
              </button>
            </Link>
          </div>
        </div>
      </div>
    </div>
  );
};
