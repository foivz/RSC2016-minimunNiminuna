//
//  QuizWrapper.swift
//  RSC
//
//  Created by MTLab on 27/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation

struct QuizWrapper{ //also a manager for turns
    
    let quiz: Quiz
    let teams: [Team]
    let prizes: [Prize]
    let questions: [Question]
    
    var currentQuestionNo: Int = 1
    
    init(quiz:Quiz, teams: [Team], prizes: [Prize], questions: [Question]) {
        self.quiz = quiz
        self.teams = teams
        self.prizes = prizes
        self.questions = questions
    }
    
    mutating func nextQuestion(){
        currentQuestionNo += 1
    }
}
