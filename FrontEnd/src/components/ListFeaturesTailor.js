import React, { useState, useEffect } from "react";
import { CreateSpecialityTailor } from "./CreateSpecialityTailor";
import { EditSpecialityTailor } from "./EditSpecialityTailor";
export const ListFeaturesTailor = ({ tailor }) => {

  //state for type of service
  const [tailorSpecialties, setTailorSpecialties] = useState(tailor.specialties);
  //state for type of service
  const [price, setPrice] = useState(0);

    return (           

        <div id="results" className="search-results">
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
                    <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#editSpecialityTailor">
                      Editar
                    </button>
                    <EditSpecialityTailor talorID={tailor.id} editSpeciality={speciality} allSpecialities= {tailor.specialties} updateSpecialties ={ setTailorSpecialties}></EditSpecialityTailor>
                    <button type="button" class="btn btn-dark">
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
                  <button type="button" class="btn btn-dark" data-bs-toggle="modal" data-bs-target="#createSpecialityTailor">
                    Añadir
                  </button>
                  <CreateSpecialityTailor talorID={tailor.id} allSpecialities= {tailor.specialties} updateSpecialties ={ setTailorSpecialties}></CreateSpecialityTailor>
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
                    <button type="button" class="btn btn-dark">
                      Editar
                    </button>
                    <button type="button" class="btn btn-dark">
                      Borrar
                    </button>
                  </td>
                </tr>
              ))}
              <tr>
                <th scope="row"></th>
                <th>
                  <input></input>
                </th>
                <th>
                  <button type="button" class="btn btn-dark">
                    Añadir
                  </button>
                </th>
              </tr>
            </tbody>
          </table>
        </div>
        <div className="container">
          <table className="table table-success table-striped">
            <thead>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Trabajo</th>
                <th scope="col">Fecha</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <th scope="col">#</th>
                <th scope="col">Trabajo</th>
                <th scope="col">Fecha</th>
              </tr>
            </tbody>
          </table>
        </div>
      </div>

      );
};