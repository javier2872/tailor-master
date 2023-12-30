import React from "react";
import { Card } from "./Card";

// extraemos la propiedad clients
export const ClientTable = ({ clients }) => {
  return (
    <div id="client_table" className="container bootstrap snippets bootdeys">
      <div className="row">
        {clients?.map((client) => (
          <div className="col-md-4 col-sm-6 content-card">
            <div className="card-big-shadow" key={client.id}>
              <Card
                id={client.id}
                name={client.name}
                description={client.description}
              ></Card>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
