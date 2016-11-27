//
//  UIColorExtensions.swift
//  PokeDex
//
//  Created by FOI on 10/07/16.
//  Copyright © 2016 infinumSA. All rights reserved.
//

import UIKit

extension UIColor {
    
    static func uiColorFromHex (rgbValue:UInt32, alpha:Double=1.0) -> UIColor {
        let red = CGFloat((rgbValue & 0xFF0000) >> 16)/256.0
        let green = CGFloat((rgbValue & 0xFF00) >> 8)/256.0
        let blue = CGFloat(rgbValue & 0xFF)/256.0
        return UIColor(red:red, green:green, blue:blue, alpha:CGFloat(alpha))
    }
    
    convenience init(fromHex rgbValue: UInt32, alpha:Double=1.0) {
        let red = CGFloat((rgbValue & 0xFF0000) >> 16)/256.0
        let green = CGFloat((rgbValue & 0xFF00) >> 8)/256.0
        let blue = CGFloat(rgbValue & 0xFF)/256.0
        self.init(red:red, green:green, blue:blue, alpha:CGFloat(alpha))
    }
}