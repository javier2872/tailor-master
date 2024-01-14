import axios from "axios";

// nos devuelve todos tailors
export const getAllTailors = async () => {
  const http = axios.create({
    baseURL: "http://localhost:8762/sastreria-fitting",
    headers: {
      "Content-type": "application/json",
    },
    responseType: "json",
    mode: "cors",
  });
  return await http
    .get("/tailor")
    .then((resp) => {
      return resp.data;
    })
    .catch((e) => {
      console.log(e);
    });
};

// nos devuelve un tailor con un id en especifico
export const getATailors = async (idTailor) => {
  let url = "/tailor/" + idTailor;
  const http = axios.create({
    baseURL: "http://localhost:8762/sastreria-fitting",
    headers: {
      "Content-type": "application/json",
    },
    responseType: "json",
    mode: "cors",
  });
  return await http
    .get(url)
    .then((resp) => {
      return resp.data;
    })
    .catch((e) => {
      console.log(e);
    });
};

// actualiza parcialmente un tailor; specialties o/y availability
export const updateATailors = async (idTailor, updated) => {
  let url = "/tailor/" + idTailor;
  const http = axios.create({
    baseURL: "http://localhost:8762/sastreria-fitting",
    headers: {
      "Content-type": "application/json",
    },
    responseType: "json",
    mode: "cors",
  });
  return await http
    .patch(url, updated)
    .then((resp) => {
      return resp.data;
    })
    .catch((e) => {
      console.log(e);
    });
};

// añade un job
export const addJob = async (newJob) => {
  const http = axios.create({
    baseURL: "http://localhost:8762/trabajo-fitting",
    headers: {
      "Content-type": "application/json",
    },
    responseType: "json",
    mode: "cors",
  });

  return await http
    .post("/job", newJob)
    .then((resp) => {
      return resp.data;
    })
    .catch((e) => {
      console.log(e);
    });
};

// nos da todos los jobs de un tailor
export const allJobsATailor = async (idTailor) => {
  let url = "/job/tailor/" + idTailor;
  const http = axios.create({
    baseURL: "http://localhost:8762/trabajo-fitting",
    headers: {
      "Content-type": "application/json",
    },
    responseType: "json",
    mode: "cors",
  });

  return await http
    .get(url)
    .then((resp) => {
      return resp.data;
    })
    .catch((e) => {
      console.log(e);
    });
};
