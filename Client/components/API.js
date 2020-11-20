import axios from 'axios';

export async function sendPostRequest(location, requestBody)
{
    try { return await axios.post("/api/"+location, requestBody) }
    catch(error) { return null; }
}