import React, { useState, useEffect } from "react";
import { updateATailors } from "../services/http.service";

export const EditSpecialityTailor = ({talorID, editSpeciality, allSpecialities}) => {
  
  const [service, setService] = useState(editSpeciality['name']);
  //state for type of service
  const [price, setPrice] = useState(parseInt(editSpeciality['price']));

  const clickHandler = () => {
    setPrice(parseInt(price));
    if((service.length>0 && price>=0 ) && ( service!= editSpeciality['name'] || price != parseInt(editSpeciality['price']))){
      allSpecialities[allSpecialities.findIndex((e) => e == editSpeciality)]= {
        name: service,
        price: price
      }
      let speciality = {
        specialties: allSpecialities,
      };
      updateATailors(talorID, speciality);
      console.log("Select is clicked");
    }
   
    }

  return (
          <div class="modal fade" id="editSpecialityTailor" tabindex="-1" aria-labelledby="EditSpecialityTailorLabel" aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <h5 class="modal-title">Añadir servicio</h5>
                  <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
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
                  <button type="button" className="btn btn-dark" onClick={clickHandler} data-bs-dismiss="modal">
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
