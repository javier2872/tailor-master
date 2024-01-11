import React, { useState, useEffect, useRef } from "react";

// extraemos la propiedad specialties
export const TablePrice = ({ specialties, addNewItem }) => {
  // donde guardaremos el numero de items que queremos
  const [newItems, setNewItems] = useState({ name: "", number: "0" });

  //para no ejecutar el useEffect en la primera renderización y asi no guardar el primer estado en memoria
  const isFirstRender = useRef(true);

  //guardamos cuando se produzca un cambio en el pedido
  const handleChange = (ev) => {
    const items1 = { name: ev.target.id, number: ev.target.value };
    setNewItems(items1);
  };

  // cuando se modifique newItems, llamaremos a la funcion addNewItems y cambiaremos el useRef de estado
  useEffect(() => {
    if (isFirstRender.current) {
      isFirstRender.current = false;
      return;
    }
    addNewItem(newItems.name, newItems.number);
  }, [newItems]);

  return (
    <table className="table table-success table-striped">
      <thead>
        <tr>
          <th scope="col">#</th>
          <th scope="col">Trabajo</th>
          <th scope="col">Precio (aproximado)</th>
          <th scope="col">Cantidad</th>
        </tr>
      </thead>
      <tbody>
        {specialties?.map((specity, index) => (
          <tr key={specity.name}>
            <th scope="row">{index}</th>
            <td>{specity.name}</td>
            <td>{specity.price}&#8364;</td>
            <td>
              <select
                id={specity.name}
                defaultValue={newItems.number}
                className="form-select"
                aria-label="Default select example"
                onChange={(ev) => handleChange(ev)}
              >
                <option value="0">0</option>
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
              </select>
            </td>
          </tr>
        ))}
      </tbody>
    </table>
  );
};
