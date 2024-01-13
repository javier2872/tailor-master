import React, { useState, useEffect } from "react";

export const CreateEspecialtyTailor = () => {
  
  //state for type of service
  const [service, setService] = useState("");
  //state for type of service
  const [price, setPrice] = useState(0);

  return (
          <div class="modal fade" id="createEspecialtyTailor" tabindex="-1" aria-labelledby="createEspecialtyTailorLabel" aria-hidden="true">
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
                  <button type="button" className="btn btn-dark">
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
