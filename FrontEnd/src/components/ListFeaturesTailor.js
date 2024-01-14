import React, { useState, useEffect } from "react";
import { CreateSpecialityTailor } from "./CreateSpecialityTailor";
import { EditSpecialityTailor } from "./EditSpecialityTailor";
import { CreateAvailabilityTailor } from "./CreateAvailabilityTailor";
import { EditAvailabilityTailor } from "./EditAvailabilityTailor";
import { updateATailors } from "../services/http.service";

export const ListFeaturesTailor = ({ tailor }) => {
  //to update the specialty table
  const [tailorSpecialties, setTailorSpecialties] = useState(
    tailor.specialties
  );

  //to update the specialty table
  const [tailorAvailabilities, setTailorAvailabilities] = useState(
    tailor.availability
  );

  useEffect(() => {
    setTailorSpecialties(tailorSpecialties);
    setTailorAvailabilities(tailorAvailabilities);
  }, [tailorSpecialties, tailorAvailabilities ]);

  const clickDeleteSpecialtyHandler = (deleteSpeciality) => {
    let deletedSpeciality = tailor.specialties.filter(
      (e) => e != deleteSpeciality
    );
    let UpdatedSpeciality = {
      specialties: deletedSpeciality,
    };
    updateATailors(tailor.id, UpdatedSpeciality);
    setTailorSpecialties(deletedSpeciality);
    //carga la pagina despues de borrar
    window.location.reload();
  };

  const clickDeleteAvailabilityHandler = (deleteAvailability) => {
    let deletedAvailability = tailor.availability.filter(
      (e) => e != deleteAvailability
    );
    let UpdatedAvailability = {
      availability: deletedAvailability,
    };
    updateATailors(tailor.id, UpdatedAvailability);
    setTailorAvailabilities(deletedAvailability);
    //carga la pagina despues de borrar
    //window.location.reload();
  };
  return (
    <div id="table_List_Features_Tailor" className="search-results">
      <div className="container">
        <table className="table table-success table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Trabajo</th>
              <th scope="col">Precio (aproximado)</th>
              <th scope="col">Accion</th>
            </tr>
          </thead>
          <tbody>
            {tailor.specialties?.map((speciality, index) => (
              <tr key={speciality.name}>
                <th scope="row">{index}</th>
                <td>{speciality.name}</td>
                <td>{speciality.price}&#8364;</td>
                <td>
                  <button
                    type="button"
                    class="btn btn-dark"
                    data-bs-toggle="modal"
                    data-bs-target="#editSpecialityTailor"
                  >
                    Editar
                  </button>
                  <EditSpecialityTailor
                    talorID={tailor.id}
                    editSpeciality={speciality}
                    allSpecialities={tailor.specialties}
                    updateSpecialties={setTailorSpecialties}
                  ></EditSpecialityTailor>
                  <button
                    type="button"
                    class="btn btn-dark"
                    onClick={() => clickDeleteSpecialtyHandler(speciality)}
                  >
                    Borrar
                  </button>
                </td>
              </tr>
            ))}
            <tr>
              <th scope="row"></th>
              <td> </td>
              <td> </td>
              <td>
                <button
                  id="addSpeciality_List_Features_Tailor"
                  type="button"
                  class="btn btn-dark"
                  data-bs-toggle="modal"
                  data-bs-target="#createSpecialityTailor"
                >
                  Añadir
                </button>
                <CreateSpecialityTailor
                  talorID={tailor.id}
                  allSpecialities={tailor.specialties}
                  updateSpecialties={setTailorSpecialties}
                ></CreateSpecialityTailor>
              </td>
            </tr>
          </tbody>
        </table>
      </div>
      <div className="container">
        <table className="table table-success table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Disponibilidad</th>
              <th scope="col">Accion</th>
            </tr>
          </thead>
          <tbody>
            {tailor.availability?.map((available, index) => (
              <tr key={index}>
                <th scope="row">{index}</th>
                <td>{available}</td>
                <td>
                  <button
                    type="button"
                    class="btn btn-dark"
                    data-bs-toggle="modal"
                    data-bs-target="#editAvailabilityTailor"
                  >
                    Editar
                  </button>
                  <EditAvailabilityTailor
                    talorID={tailor.id}
                    editAvailability={available}
                    allAvailabilities={tailor.availability}
                    updateAvailabilities={setTailorAvailabilities}
                  ></EditAvailabilityTailor>
                  <button
                    type="button"
                    class="btn btn-dark"
                    onClick={() => clickDeleteAvailabilityHandler(available)}
                  >
                    Borrar
                  </button>
                </td>
              </tr>
            ))}
            <tr>
              <th scope="row"></th>
              <th></th>
              <th>
                <button
                  id="addAvailability_List_Features_Tailor"
                  type="button"
                  class="btn btn-dark"
                  data-bs-toggle="modal"
                  data-bs-target="#createAvailabilityTailor"
                >
                  Añadir
                </button>
                <CreateAvailabilityTailor
                  talorID={tailor.id}
                  allAvailabilities={tailor.availability}
                  updateAvailabilities={setTailorAvailabilities}
                ></CreateAvailabilityTailor>
              </th>
            </tr>
          </tbody>
        </table>
      </div>
    </div>
  );
};
