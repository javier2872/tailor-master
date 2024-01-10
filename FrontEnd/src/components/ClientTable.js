import React from "react";
import { Card } from "./Card";
// extraemos la propiedad tailor
export  const ClientTable = ({ tailors }) => {
  return (
    <div id="tailor_table" className="container bootstrap snippets bootdeys">
      <div className="row">
        {tailors?.map((tailor) => (
          <div className="col-md-4 col-sm-6 content-card">
            <div className="card-big-shadow" key={tailor.id}>
              <Card
                id={tailor.id}
                name={tailor.name}
                description={tailor.description}
              ></Card>
            </div>
          </div>
        ))}
      </div>
    </div>
  );
};
