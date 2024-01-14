import React, { useState, useEffect } from "react";

export const ListJobsTailor = ({ jobs }) => {
  return (
    <div id="table_List_Jobs_Tailor" className="search-results">
      <div className="container">
        <table className="table table-success table-striped">
          <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col">Cliente</th>
              <th scope="col">Trabajo</th>
              <th scope="col">Fecha</th>
            </tr>
          </thead>
          <tbody>
            {jobs?.map((job, index) => (
              <tr key={index}>
                <th scope="row">{index}</th>
                <th>{job.client[0].name}</th>
                <th>
                  {job.item?.map((jobItem, index) => (
                    <div key={index}>
                      Elección: {jobItem.name} | Cantidad: {jobItem.number}
                    </div>
                  ))}
                </th>
                <th> {job.date}</th>
              </tr>
            ))}
          </tbody>
        </table>
      </div>
    </div>
  );
};
