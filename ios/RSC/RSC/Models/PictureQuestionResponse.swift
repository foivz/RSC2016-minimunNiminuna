//
//  PictureQuestionResponse.swift
//  RSC
//
//  Created by MTLab on 27/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//
import Foundation
import Unbox
struct PictureQuestionResponse {
    let score: Float
}
extension PictureQuestionResponse: Unboxable {
    init(unboxer: Unboxer) throws {
       self.score =  try unboxer.unbox(key: "images.classifiers.classes")
    }
}
