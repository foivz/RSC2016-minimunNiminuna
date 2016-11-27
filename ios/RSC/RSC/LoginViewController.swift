//
//  ViewController.swift
//  RSC
//
//  Created by MTLab on 24/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import UIKit
import Spring
import FBSDKCoreKit
import FBSDKLoginKit
import Alamofire
class LoginViewController: UIViewController {
    
    @IBOutlet weak var appImgView: SpringImageView!
    @IBOutlet weak var coverButtonTwitter: UIButton!
    @IBOutlet weak var coverButtonFace: UIButton!
    @IBOutlet weak var buttonFb: FBSDKLoginButton?
    var continuetToNext: Bool = false
    
    
    func sendUserInfoToServer(){
         let url = AppSpecificConstants.serverAddr + "/persons/id"
         let params = ["userID:": FBSDKAccessToken.current().userID]
          Alamofire.request(url, method: .get, parameters: params, encoding: URLEncoding.default, headers: nil).responseJSON { (response:DataResponse<Any>) in
            
            switch(response.result) {
            case .success(_):
                if let data = response.result.value{
                    print(response.result.value)
                }
                break
                
            case .failure(_):
                print(response.result.error)
                break
                
            }
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        navigationController?.navigationBar.barTintColor = UIColor.uiColorFromHex(rgbValue: UIConstants.colorMain, alpha: 1)
         navigationController?.navigationBar.tintColor = UIColor.white
        animateLaunchIcon()
                if (FBSDKAccessToken.current() != nil){
            print ("Token:"+FBSDKAccessToken.current().tokenString)
            print("user id:"+FBSDKAccessToken.current().userID)
         self.navigationController!.pushViewController(self.storyboard!.instantiateViewController(withIdentifier: StoryboardConstants.QRJoinInViewController) as UIViewController, animated: true)
            
        }
            let button = FBSDKLoginButton()
        
                       // Optional: Place the button in the center of your view.
            buttonFb = button
            buttonFb!.isHidden = true
            buttonFb!.delegate = self
            buttonFb!.center = self.coverButtonFace.center
            buttonFb!.readPermissions = ["public_profile", "email"]
            self.view.addSubview(buttonFb!)
        
        }
    
    @IBAction func onFbLogin(_ sender: UIButton) {
        buttonFb!.sendActions(for: .touchUpInside)
        
    }
    func animateLaunchIcon () {
        appImgView.animation = "fadeInDown"
        appImgView.delay = 0.3
        appImgView.duration = 1.0
        appImgView.curve = "easeInOut"
        appImgView.animate()
        appImgView.delay=1.0
          
    }
}
extension LoginViewController: FBSDKLoginButtonDelegate {
    func loginButton(_ loginButton: FBSDKLoginButton!, didCompleteWith result: FBSDKLoginManagerLoginResult!, error: Error!) {
        print("User Logged In")
        
        if ((error) != nil)
        {
            print(error)
        }
        else if result.isCancelled {
            print(result.debugDescription)
            //print("tokencic:",result.token)
            //print("[debug] cancelled, todo: fix this bug")
            self.navigationController!.pushViewController(self.storyboard!.instantiateViewController(withIdentifier: StoryboardConstants.QRJoinInViewController) as! QRJoinInViewController, animated: true)
            }else {
            print ("token:",result.token.tokenString)
            print("sucess")
            //sendUserInfoToServer()
            self.navigationController!.pushViewController(self.storyboard!.instantiateViewController(withIdentifier: StoryboardConstants.QRJoinInViewController) as! QRJoinInViewController, animated: true)
        }
    }
    
    func loginButtonDidLogOut(_ loginButton: FBSDKLoginButton!) {
        print("User Logged Out")
    }
}
