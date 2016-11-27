//
//  QuestionCategory.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation


import Foundation
import Unbox
struct QuestionCategory {
    let idCat: Int
    let name: String
    let duration: Int
}
extension QuestionCategory: Unboxable {
    init(unboxer: Unboxer) throws {
        self.idCat = try unboxer.unbox(key: "idCat")
        self.name = try unboxer.unbox(key: "name")
        self.duration = try unboxer.unbox(key: "time")
    }
}
