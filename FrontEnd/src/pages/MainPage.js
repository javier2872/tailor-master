import React, {useState} from "react";
import { Link } from "react-router-dom";
import { ModalLogin } from "../components/ModalLogin";

export const MainPage = () => {
  const [showModal, setShowModal] = useState(false);
  const toggleOpen = () => setShowModal(!showModal);
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
          <div className="mb-3" >
              <button type="button" id="button-login-tailor" onClick={toggleOpen} >Sastre/a</button>
              {showModal && <ModalLogin functions={[showModal, setShowModal]}  />}
          </div>
        </form>
      </div>
    </div>
  );
};
