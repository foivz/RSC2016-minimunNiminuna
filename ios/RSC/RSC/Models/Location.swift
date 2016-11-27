//
//  Location.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation
import Unbox
struct Location {
    let lat: Float
    let lng: Float
}
extension Location: Unboxable {
    init(unboxer: Unboxer) throws {
        self.lat = try unboxer.unbox(key: "lat")
        self.lng = try unboxer.unbox(key: "lng")
    }
}
