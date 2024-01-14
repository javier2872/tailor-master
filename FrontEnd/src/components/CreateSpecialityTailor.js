import React, { useState } from "react";
import { updateATailors } from "../services/http.service";

export const CreateSpecialityTailor = ({
  talorID,
  allSpecialities,
  updateSpecialties,
}) => {
  //state for type of service
  const [service, setService] = useState("");
  //state for type of service
  const [price, setPrice] = useState(0);

  const clickHandler = (event) => {
    event.preventDefault();
    setPrice(parseInt(price));
    if (service.length > 0 && price >= 0) {
      allSpecialities.push({
        name: service,
        price: price,
      });
      let speciality = {
        specialties: allSpecialities,
      };
      updateATailors(talorID, speciality);
      updateSpecialties(speciality);
    }
  };

  return (
    <div
      class="modal fade"
      id="createSpecialityTailor"
      tabindex="-1"
      aria-labelledby="createSpecialityTailorLabel"
      aria-hidden="true"
    >
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title">Añadir servicio</h5>
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
                  value={service}
                  onChange={(e) => setService(e.target.value)}
                />
              </div>
              <div className="form-group">
                <label> Precio: </label>
                <input
                  placeholder="Precio"
                  className="form-control"
                  value={price}
                  onChange={(e) => setPrice(e.target.value)}
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
