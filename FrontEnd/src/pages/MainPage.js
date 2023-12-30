import React from "react";
import { Link } from "react-router-dom";

export const MainPage = () => {
  return (
    <div className="card card-main text-center">
      <div className="card-header text-light">
        <h5>Fitting</h5>
      </div>
      <div className="card-body">
        <form>
          <div className="mb-3">
            <Link to={`/client`}>
              <button>Cliente</button>
            </Link>
          </div>
          <div className="mb-3">
            <Link to={`/tailor`}>
              <button>Sastre/a</button>
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
};
