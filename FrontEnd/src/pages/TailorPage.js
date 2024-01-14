import React, { useState, useEffect } from "react";
import { NavBar } from "../components/NavBar";
import data from "../resources/tailor_data.json";
import { getATailors } from "../services/http.service";
import { allJobsATailor } from "../services/http.service";
import { ListFeaturesTailor } from "../components/ListFeaturesTailor";
import { ListJobsTailor } from "../components/ListJobsTailor";

export const TailorPage = () => {
  // configuración del hooks useState
  const [dataTailor, setDataTailor] = useState([]);
  const [title, setTitle] = useState("");
  const [showTable, setShowTable] = useState(false);
  const [jobTailor, setJobTailor] = useState([]);

  useEffect(() => {
    getATailors(JSON.parse(sessionStorage.getItem("tailor"))).then((d) =>
      setDataTailor(d)
    );
  }, []);

  useEffect(() => {
    if (!dataTailor) {
      setShowTable(!showTable);
    } else {
      setShowTable(showTable);
      setTitle("Sastre: " + dataTailor.name);
    }
    allJobsATailor(dataTailor.id).then((d) => setJobTailor(d));
  }, [dataTailor]);

  if (!dataTailor)
    return (
      <div
        id="errorMessage_Tailor_page"
        className="container alert alert-success"
        role="alert"
      >
        No existe usuario
      </div>
    );

  return (
    <section className="garamond">
      <NavBar title={title}></NavBar>
      <div className="container">
        {!showTable && (
          <ListFeaturesTailor tailor={dataTailor}></ListFeaturesTailor>
        )}
        {!showTable && <ListJobsTailor jobs={jobTailor}></ListJobsTailor>}
      </div>
    </section>
  );
};
