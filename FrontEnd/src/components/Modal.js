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
      id="modal_finish"
      tabIndex="-1"
      aria-labelledby="finishModalLabel"
      aria-hidden="true"
    >
      <div className="modal-dialog">
        <div className="modal-content">
          <div className="modal-header bg-success text-light bg-opacity-50r">
            <h5 id="title_modal_finish" className="modal-title">
              Pedido Tramitando
            </h5>
          </div>
          <div id="body_modal_finish" className="modal-body">
            <p>Gracias por confiar en nosotros.</p>
            <p>En breve recibirá más información.</p>
          </div>
          <div className="modal-footer">
            <Link to={`/`}>
              <button
                id="button_submit_modal_finish"
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
