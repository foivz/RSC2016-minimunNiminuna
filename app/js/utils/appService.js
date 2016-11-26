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
    var url = '/speech';
    send('GET', url, undefined, undefined, (res) => success(res));
}

