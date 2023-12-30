import React, { useState, useEffect, useRef } from "react";

// extraemos la propiedad schedule
export const TableSchedule = ({ schedule, addNewdate }) => {
  // donde guardaremos la fecha seleccionada
  const [newSchedule, setNewSchedule] = useState("");

  //para no ejecutar el useEffect en la primera renderización y asi no guardar el primer estado en memoria
  const isFirstRender = useRef(true);
  //guardamos cuando se produzca un cambio en el checkbox
  const handleOnClick = (ev) => {
    // hacemos que solo se pueda seleccionar una fecha
    var myCheckbox = document.getElementsByName("myCheckbox");
    Array.prototype.forEach.call(myCheckbox, function (el) {
      el.checked = false;
    });
    ev.target.checked = true;
    setNewSchedule(ev.target.value);
  };

  // cuando se modifique newItems, llamaremos a la funcion
  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return;
    }
    addNewdate(newSchedule);
  }, [newSchedule]);

  return (
    <table className="table table-success table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Disponibilidad</th>
          <th scope="col">Seleccionar</th>
        </tr>
      </thead>
      <tbody>
        {schedule?.map((available, index) => (
          <tr key={index}>
            <th scope="row">{index}</th>
            <td>{available}</td>
            <td>
              <div className="form-check">
                <input
                  className="form-check-input"
                  name="myCheckbox"
                  type="checkbox"
                  value={available}
                  onClick={(ev) => handleOnClick(ev)}
                ></input>
                <label className="form-check-label" htmlFor="flexCheckDefault">
                  Elegida
                </label>
              </div>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
