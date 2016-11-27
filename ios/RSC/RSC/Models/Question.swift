//
//  Questions.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation
//"idQuestion": 5,
//    "question": "Upload a picture of triangle",
//    "answer": "Triangle",
//    "allAnswers": null,


import Foundation
import Unbox
struct Question {
    let idQuestion: Int
    let question: String
    let answer: String
    let allAnswers: String?
    let category: QuestionCategory
}
extension Question: Unboxable {
    init(unboxer: Unboxer) throws {
        self.idQuestion = try unboxer.unbox(key: "idQuestion")
        self.question = try unboxer.unbox(key: "question")
         self.answer = try unboxer.unbox(key: "answer")
         self.allAnswers = unboxer.unbox(key: "allAnswers")
         self.category = try unboxer.unbox(key: "cat")
    }
}
