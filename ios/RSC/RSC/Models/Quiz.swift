//
//  Quiz.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//
//{
//    "idQuiz": 1,
//    "name": "History Quiz 1. Gimnazija",
//    "beginDate": "26.11.2016.",
//    "endDate": "26.11.2016",
//    "beginTime": "18:00",
//    "endTime": "19:00",
//    "location": {
//        "lat": 46.308483,
//        "lng": 16.340031
//    },
//    "creator": {
//        "idPerson": 1,
//        "name": "kreso",
//        "surname": "kresic",
//        "isModerator": true,
//        "credentials": {
//            "username": "kresendo",
//            "password": "kreso"
//        },
//        "categories": []
//    },
//    "prizes": [
//    {
//    "idPrize": 2,
//    "name": "Plus +10 points at the end of semester"
//    },
//    {
//    "idPrize": 4,
//    "name": "100 ingame golds"
//    },
//    {
//    "idPrize": 8,
//    "name": "5% discount at Links d.o.o"
//    }
//    ],
//    "questions": [
//    {
//    "idQuestion": 1,
//    "question": "Who was the first USA president?",
//    "answer": "a",
//    "allAnswers": "a;b;c;d",
//    "cat": {
//    "idCat": 2,
//    "name": "Checkbox",
//    "time": 60
//    }
//    },
//    {
//    "idQuestion": 2,
//    "question": "When did the WW1 start?",
//    "answer": "1941",
//    "allAnswers": null,
//    "cat": {
//    "idCat": 1,
//    "name": "Text Input",
//    "time": 60
//    }
//    },
//    {
//    "idQuestion": 3,
//    "question": "Which countries were involved in WW2?",
//    "answer": "Italy;Russia",
//    "allAnswers": "Sweden;Italy;Mexico;Russia",
//    "cat": {
//    "idCat": 3,
//    "name": "Multiple choice",
//    "time": 70
//    }
//    },
//    {
//    "idQuestion": 4,
//    "question": "Which of the following pictures showes Abraham Lincoln",
//    "answer": "g",
//    "allAnswers": "e;f;g;h",
//    "cat": {
//    "idCat": 4,
//    "name": "Multimedia choice",
//    "time": 40
//    }
//    },
//    {
//    "idQuestion": 5,
//    "question": "Upload a picture of triangle",
//    "answer": "Triangle",
//    "allAnswers": null,
//    "cat": {
//    "idCat": 5,
//    "name": "Multimedia upload",
//    "time": 100
//    }
//    }
//    ],
//    "teams": [
//    {
//    "idTeam": 1,
//    "name": "Minimun Niminuna",
//    "description": "Pravi hackaton majstori",
//    "icon": {
//    "idIcon": 1,
//    "url": "placeholder1"
//    },
//    "members": [
//    {
//    "idPerson": 7,
//    "name": "hrvoje",
//    "surname": "hrco",
//    "isModerator": false,
//    "credentials": {
//    "username": "hrvoje",
//    "password": "hrvoje"
//    },
//    "categories": []
//    },
//    {
//    "idPerson": 8,
//    "name": "danijel",
//    "surname": "danko",
//    "isModerator": false,
//    "credentials": {
//    "username": "danijel",
//    "password": "danijel"
//    },
//    "categories": []
//    }
//    ],
//    "teamCategories": []
//    }
//    ],
//    "category": {
//        "id": 2,
//        "name": "History"
//    }
//}

//
//    "idQuiz": 1,
//    "name": "History Quiz 1. Gimnazija",
//    "beginDate": "26.11.2016.",
//    "endDate": "26.11.2016",
//    "beginTime": "18:00",
//    "endTime": "19:00",
//    "location": {
//        "lat": 46.308483,
//        "lng": 16.340031
//    }
import Foundation
import Unbox
struct Quiz {
    let idQuiz: Int
    let name: String
    let beginDate: String
    let finishDate: String
    let beginTime: String
    let endTime: String
    let location: Location
}
extension Quiz: Unboxable {
    init(unboxer: Unboxer) throws {
        self.idQuiz = try unboxer.unbox(key: "idQuiz")
        self.name = try unboxer.unbox(key: "name")
        self.beginDate = try unboxer.unbox(key: "beginDate")
        self.finishDate = try unboxer.unbox(key: "endDate")
        self.beginTime = try unboxer.unbox(key: "beginTime")
        self.endTime = try unboxer.unbox(key: "endTime")
        self.location = try unboxer.unbox(key: "location")
    }
}
