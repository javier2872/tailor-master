import axios from "axios";

export const getAllTailors = async () => {
    const http = axios.create({
        baseURL: "http://localhost:8088",
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