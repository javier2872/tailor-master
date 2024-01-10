import React, { useEffect, useState } from "react";
import { useLocation } from "react-router-dom";

export const NavBar = ({ children = "" ,title}) => {
  // definimos esta constante, para comprobar en que path estamos
  const location = useLocation();
  // hook para controlar si enseñamos los boton de busqueda, ya que solo se muestran en la pantalla principal
  const [showComponent, setShowComponent] = useState(true);

  // Cuando se cargue la pagina comprobara y enseñara los searcher
  useEffect(() => {
    const re = /[a-z]+/;
    setShowComponent(location.pathname.match(re) ? true : false);
  }, []);

  return (
    <div>
      <nav className="bg-light">
        <a className="navbar-brand fw-bold fs-1">FITTING</a>
      </nav>
      <nav className="navbar navbar-expand-lg bg-light">
        <div className="container-fluid">
          <a className="navbar-brand fw-bold fs-3">{title}</a>
          <button
            className="navbar-toggler"
            type="button"
            data-bs-toggle="collapse"
            data-bs-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent"
            aria-expanded="false"
            aria-label="Toggle navigation"
          ></button>
          <div id="navbarSupportedContent">
            <ul className="navbar-nav me-auto mb-2 mb-lg-0">
              <li className="nav-item">{showComponent && children}</li>
            </ul>
          </div>
        </div>
      </nav>
    </div>
  );
};
