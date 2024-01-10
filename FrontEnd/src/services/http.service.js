import axios from "axios";

class  httpRequestsToAPI  {
    static async getAllTailors() {
        const http = axios.create({
            baseURL: "http://localhost:8088",
            headers: {
            "Content-type": "application/json"
            },
            responseType: "json",
            mode: "cors",
        });
        return await http.get("/tailor")
        .then( (resp) => {console.log("getAllTailors " + resp.data); return  resp.data })
        .catch(e => {console.log(e); });     
    }
}
export default httpRequestsToAPI;