import React, {useState} from "react";
import { Link } from "react-router-dom";
import { useStorageMemory } from "../hooks/useStorageMemory";

export const ModalLogin = props => {
const [showModal, setShowModal] = props.functions;

const [tailor, setTailor] = useState("");

  //cuando accemos click, significa que ya hemos terminado y se guarda en memoria local
  const handleClick = () => {
    localStorage.setItem('tailor', JSON.stringify(tailor));
  };


return (<div open={showModal} >  
        <div className="modal-dialog modal-sm">
          <div className="modal-content">
            <div className="modal-header">
              <h4 className="modal-title">Login</h4>
            </div>
            <div className="modal-body">
            
                <div className="form-group">
                  <label htmlFor="inputUserName">Sastre</label>
                  <input className="form-control" placeholder="Sastre" type="text" id="inputSastre" onChange={(e) => setTailor(e.target.value)}/>
                </div>
              
            </div>
            <div className="modal-footer"> 
            <Link to={`/tailor`}>
                 <button type="submit" onClick={() => {handleClick();}} >Entrar</button>
            </Link>            
              <button id ="btnHideModal" type="button" onClick={() => setShowModal(!showModal)}>Cerrar</button></div>
          </div>

        </div>
        </div>  
    
    );}