import {send} from './APIService';

export function fetchQuizes(success) {
    var url = '/quiz/';
    send('GET', url, undefined, undefined, (res) => success(res));
}

export function fetchQuizByID(quizID, success) {
    var url = '/quiz/' + quizID;
    send('GET', url, undefined, undefined, (res) => success(res));
}

export function sendAudio(audio, success) {
    var url = '/speech/';

    var reader = new FileReader();
    reader.addEventListener("loadend", function() {

            var base64FileData = reader.result.toString();
            var base64 = base64FileData.substring(22);

            var data = {
                data: base64
            };

            var headers = {
                'Content-type': 'application/json'
            };

            send('POST', url, data, headers, (res) => success(res), (err) => console.log(err));
        });

    reader.readAsDataURL(audio);


}

