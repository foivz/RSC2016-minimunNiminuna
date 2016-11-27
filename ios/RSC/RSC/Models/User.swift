//
//  User.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation
import Unbox

struct User {
    let userName: String
    let userId: Int
    let surname: String
    let isModerator: Bool
}
extension User: Unboxable {
    init(unboxer: Unboxer) throws {
        self.userName = try unboxer.unbox(key: "name")
        self.userId = try unboxer.unbox(key: "idPerson")
        self.surname = try unboxer.unbox(key: "surname")
        self.isModerator = try unboxer.unbox(key: "isModerator")
        
    }
}
