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
    var url = 'http://139.59.158.214:8080/speech/';
    var data = new FormData();
    data.append('file', audio, "test.wav");

    fetch(url, {
        method: 'POST',
        body: data
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}

export function sendImage(image, success) {
    var url = 'http://139.59.158.214:8080/speech/img';
    var data = new FormData();
    data.append('file', image, image.name);

    fetch(url, {
        method: 'POST',
        body: data
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}

export function createTeam(data, success) {
    var url = 'http://139.59.158.214:8080/team/create';

    fetch(url, {
        method: 'POST',
        body: data,
        headers: {
            'Content-Type': 'application/json'
        }
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}

export function getIcons(success) {
    var url = 'http://139.59.158.214:8080/icon/';

    fetch(url, {
        method: 'GET'
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}


export function getQuestions(success) {
    var url = 'http://139.59.158.214:8080/question/';

    fetch(url, {
        method: 'GET'
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}

export function getAwards(success) {
    var url = 'http://139.59.158.214:8080/prize/';

    fetch(url, {
        method: 'GET'
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}

export function setQuizPoints(data, success) {
    var url = 'http://139.59.158.214:8080/stat/' + gameID + "/team/" + teamID + "/" + score;

    fetch(url, {
        method: 'GET',
    }).then((res) => {
        return res.json();
    }).then(success).catch((err) => {
        console.log(err);
    });
}
