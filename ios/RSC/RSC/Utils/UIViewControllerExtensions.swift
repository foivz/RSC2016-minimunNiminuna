//
//  UIViewControllerExtensions.swift
//  PokeDex
//
//  Created by Infinum on 20/07/16.
//  Copyright © 2016 infinumSA. All rights reserved.
//

import Foundation
import UIKit
import SVProgressHUD
extension UIViewController {
    func showAlert(title:String, message:String) {
        let alertVC = UIAlertController(title: title, message: message, preferredStyle: .alert)
        
        let okAction = UIAlertAction(title: "ok", style: .default, handler: nil)
        alertVC.addAction(okAction)
        self.present(alertVC, animated: true, completion: nil)
    }
    func showActionSheet(title:String,actions: [UIAlertAction]) {
        let alertVC = UIAlertController(title: title, message: nil, preferredStyle: .actionSheet)
        for action in actions {
            alertVC.addAction(action)
        }
        
        self.present(alertVC, animated: true, completion:nil)
    }
    func showSpinner(){
        SVProgressHUD.show()
    }
    func showSpinnerWithSuccessStatus(status:String){
        SVProgressHUD.show(withStatus: status)
    }
    func hideSpinner(){
        SVProgressHUD.dismiss()
    }
    func showSpinnerWithErrorStatus(error: String){
        SVProgressHUD.showError(withStatus: error)
    }
}
