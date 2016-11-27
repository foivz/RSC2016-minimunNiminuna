const API = 'http://139.59.158.214:8080';

export function send (method, endpoint, data, headers, success, error) {
    let url = `${API}${endpoint}`;
    data = JSON.stringify(data);

    let options = {};

    options.method = method;
    options.body = data;
    if (headers != undefined && headers != null) options.headers = headers;

    fetch(url, options)
        .then( (res) => {
            if(res.status >= 200 && res.status < 300)
                return res;
            else {
                let error = new Error(res.statusText);
                error.res = res;
                throw error;
            }
        })
        .then( (res) => {
            return res.json();
        })
        .then(success)
        .catch(error)
}