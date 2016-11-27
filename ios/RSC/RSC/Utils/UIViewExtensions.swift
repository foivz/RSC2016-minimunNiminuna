//
//  UIViewExtensions.swift
//  PokeDex
//
//  Created by Infinum on 20/07/16.
//  Copyright © 2016 infinumSA. All rights reserved.
//

import Foundation
import UIKit
extension UIView {
    func roundTheViewWithColor(color:CGColor){
        self.layer.cornerRadius = self.frame.size.width / 2;
        self.clipsToBounds = true
        self.layer.borderWidth = 1.0
        self.layer.borderColor = color
    }
  
}