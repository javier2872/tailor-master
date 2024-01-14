import React, { useState } from "react";
import { updateATailors } from "../services/http.service";

export const EditAvailabilityTailor = ({
  talorID,
  editAvailability,
  allAvailabilities,
  updateAvailabilities,
}) => {
  const [availabilityUpdate, setAvailabilityUpdate] =
    useState(editAvailability);

  const clickHandler = () => {
    if (
      availabilityUpdate.length > 0 &&
      availabilityUpdate != editAvailability
    ) {
      allAvailabilities[
        allAvailabilities.findIndex((e) => e == editAvailability)
      ] = availabilityUpdate;
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
      id="editAvailabilityTailor"
      tabindex="-1"
      aria-labelledby="editAvailabilityTailorLabel"
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
                <label> Trabajo: </label>
                <input
                  placeholder="Tipo de trabajo"
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
