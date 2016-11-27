//
//  Prize.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//


import Foundation
import Unbox
struct Prize {
    let idPrize: String
    let name: String
}
extension Prize: Unboxable {
    init(unboxer: Unboxer) throws {
        self.idPrize = try unboxer.unbox(key: "idPrize")
        self.name = try unboxer.unbox(key: "name")
    }
}
