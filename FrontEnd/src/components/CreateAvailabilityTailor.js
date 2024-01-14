import React, { useState } from "react";
import { updateATailors } from "../services/http.service";

export const CreateAvailabilityTailor = ({
  talorID,
  allAvailabilities,
  updateAvailabilities,
}) => {
  //state for type of availabilty
  const [availabilityUpdate, setAvailabilityUpdate] = useState("");

  const clickHandler = (event) => {
    event.preventDefault();
    if (availabilityUpdate.length > 0) {
      allAvailabilities.push(availabilityUpdate);
      let availabilityUpdated = {
        availability: allAvailabilities,
      };
      updateATailors(talorID, availabilityUpdated);
      updateAvailabilities(availabilityUpdated);
    }
  };

  return (
    <div
      class="modal fade"
      id="createAvailabilityTailor"
      tabindex="-1"
      aria-labelledby="createAvailabilityTailorLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Añadir disponibilidad</h5>
            <button
              type="button"
              class="btn-close"
              data-bs-dismiss="modal"
              aria-label="Close"
            ></button>
          </div>
          <div class="modal-body">
            <form>
              <div className="form-group">
                <label> Fecha: </label>
                <input
                  placeholder="Fecha disponible"
                  className="form-control"
                  value={availabilityUpdate}
                  onChange={(e) => setAvailabilityUpdate(e.target.value)}
                />
              </div>
            </form>
          </div>
          <div class="modal-footer">
            <button
              type="button"
              className="btn btn-dark"
              onClick={clickHandler}
              data-bs-dismiss="modal"
            >
              Guardar
            </button>
            <button
              type="button"
              className="btn btn-dark"
              data-bs-dismiss="modal"
            >
              Cancelar
            </button>
          </div>
        </div>
      </div>
    </div>
  );
};
