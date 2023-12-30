import React, { useState, useEffect } from "react";

import data from "../resources/client_data.json";

export const TailorPage = () => {
  const [dataTailor, setDataTailor] = useState(data);

  const [searchTailor, setSearchTailor] = useState("");

  const [showTable, setShowTable] = useState();

  const filteredTailors = dataTailor.filter((tailor) => {
    return tailor.id
      .toString()
      .toLowerCase()
      .includes(searchTailor.toLowerCase());
  });

  const handleChange = (e) => {
    setSearchTailor(e.target.value);
    filteredTailors.length > 0
      ? setShowTable(!showTable)
      : setShowTable(showTable);
  };

  useEffect(() => {
    setSearchTailor("");
  }, [filteredTailors]);

  const Results = () => (
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
            {filteredTailors[0].specialties?.map((specity, index) => (
              <tr key={specity.name}>
                <th scope="row">{index}</th>
                <td>{specity.name}</td>
                <td>{specity.price}&#8364;</td>
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
              <td> <input></input></td>
              <td><input></input></td>
              <td>
                <button type="button" class="btn btn-dark">
                  Añadir
                </button>
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
            {filteredTailors[0].availability?.map((available, index) => (
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
              <th><input></input></th>
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

  return (
    <section className="garamond">
      <div className="navy georgia ma0 grow">
        <h2 className="f2">TAILOR</h2>
      </div>

      <div className="pa2 container">
        <input
          className="pa3 bb br3 grow b--none bg-lightest-blue ma3"
          type="search"
          placeholder="Buscar Sastre/a"
          onChange={handleChange}
        />
      </div>
      <div className="container">{showTable && <Results />}</div>
    </section>
  );
};
