import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useStorageMemory } from "../hooks/useStorageMemory";

export const ModalLogin = (props) => {
  const [showModal, setShowModal] = props.functions;

  const [tailor, setTailor] = useState("");

  //cuando accemos click, significa que ya hemos terminado y se guarda en memoria local
  const handleClick = () => {
    sessionStorage.setItem("tailor", JSON.stringify(tailor));
  };

  return (
    <div id="modal_login" open={showModal}>
      <div className="modal-dialog modal-sm">
        <div className="modal-content">
          <div className="modal-header">
            <h4 className="modal-title">Login</h4>
          </div>
          <div className="modal-body">
            <div className="form-group">
              <label htmlFor="inputUserName">ID de Sastre</label>
              <input
                className="form-control"
                placeholder="ID de sastre"
                type="text"
                id="idTailor_modal_login"
                onChange={(e) => setTailor(e.target.value)}
              />
            </div>
          </div>
          <div className="modal-footer">
            <Link to={`/tailor`}>
              <button
                id="buttonSubmit_modal_login"
                type="submit"
                onClick={() => {
                  handleClick();
                }}
              >
                Entrar
              </button>
            </Link>
            <button
              id="buttonHideModal_modal_login"
              type="button"
              onClick={() => setShowModal(!showModal)}
            >
              Cerrar
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
