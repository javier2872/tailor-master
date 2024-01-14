import axios from "axios";

export const getAllTailors = async () => {
    const http = axios.create({
        baseURL: "http://localhost:8762/sastreria-fitting",
        headers: {
        "Content-type": "application/json"
        },
        responseType: "json",
        mode: "cors",
    });
    return await http.get("/tailor")
    .then( (resp) => { return   resp.data })
    .catch(e => {console.log(e); });
}

export const getATailors = async (idTailor) => {
    let url = "/tailor/"+idTailor;
    const http = axios.create({
        baseURL: "http://localhost:8762/sastreria-fitting",
        headers: {
        "Content-type": "application/json"
        },
        responseType: "json",
        mode: "cors"
    });
    return await http.get(url)
    .then( (resp) => { return   resp.data })
    .catch(e => {console.log(e); });
   }

export const updateATailors = async (idTailor, updated) => {
    let url = "/tailor/"+idTailor;
    const http = axios.create({
        baseURL: "http://localhost:8762/sastreria-fitting",
        headers: {
        "Content-type": "application/json"
        },
        responseType: "json",
        mode: "cors"
    });
    return await http.patch(url, updated)
    .then( (resp) => { return   resp.data })
    .catch(e => {console.log(e); });
}


export const addJob = async (newJob) => {
    const http = axios.create({
        baseURL: "http://localhost:8762/trabajo-fitting",
        headers: {
        "Content-type": "application/json"
        },
        responseType: "json",
        mode: "cors",
    });

    return await http.post("/job", newJob)
    .then( (resp) => { return   resp.data })
    .catch(e => {console.log(e); });
   }

export const allJobsATailor = async (idTailor) => {
    let url = "/job/tailor/"+idTailor;
    const http = axios.create({
        baseURL: "http://localhost:8762/trabajo-fitting",
        headers: {
        "Content-type": "application/json"
        },
        responseType: "json",
        mode: "cors",
    });

    return await http.get(url)
    .then( (resp) => { return   resp.data })
    .catch(e => {console.log(e); });
}