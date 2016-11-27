//
//  QuestionPictureViewController.swift
//  RSC
//
//  Created by MTLab on 27/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation
import UIKit
import Alamofire

class QuestionPictureViewController: UIViewController {
    let imagePickerController = UIImagePickerController()
    
    @IBOutlet weak var photoImgView: UIImageView!
    var question : Question? {
        didSet {
            print("i got new question")
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        saveButton.backgroundColor = navigationController?.navigationBar.barTintColor
        self.navigationItem.setHidesBackButton(true, animated: false)
    }
    func presentImagePicker(){
        var actions = [UIAlertAction]()
        self.imagePickerController.delegate = self
        if UIImagePickerController.isSourceTypeAvailable(.camera) {
            actions.append(UIAlertAction(title: "Camera", style: .default){ _ in
                self.imagePickerController.sourceType = .camera
                self.present(self.imagePickerController,animated: true)
            })
        }
        if UIImagePickerController.isSourceTypeAvailable(.savedPhotosAlbum) {
            actions.append(UIAlertAction(title: "Saved photos", style: .default){ _ in
                self.imagePickerController.sourceType = .savedPhotosAlbum
                
                self.present(self.imagePickerController,animated: true)
                
            })
        }
        if UIImagePickerController.isSourceTypeAvailable(.photoLibrary) {
            actions.append(UIAlertAction(title: "Photo library", style: .default){ _ in
                self.imagePickerController.sourceType = .photoLibrary
                self.present(self.imagePickerController,animated: true)
                
            })
        }
        actions.append(UIAlertAction.init(title: "Cancel", style: .cancel){
            _ in
            self.imagePickerController.dismiss(animated: true, completion: nil)
        })
        self.showActionSheet(title: "Where do you want to take pictures from", actions: actions)
        
    }
    @IBAction func onAddPicture(_ sender: UIButton) {
        presentImagePicker()
    }
    @IBOutlet weak var pictureButton: UIButton!
    
    @IBOutlet weak var saveButton: UIButton!
    @IBAction func onSaveAnswer(_ sender: UIButton) {
        let imageData = UIImagePNGRepresentation(photoImgView.image!)!
        let url = URL(string: AppSpecificConstants.serverAddr+"/speech/img")
        self.showSpinner()
        Alamofire.upload(multipartFormData: { (multipartFormData) in
            multipartFormData.append(imageData, withName: "file", fileName: "picture.png", mimeType: "image/jpeg")
        }, to: url!)
        { (result) in
            switch result {
            case .success(let upload, _, _):
                self.showSpinnerWithSuccessStatus(status: "Photo sent!")
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                      self.hideSpinner()
                       
                }
               
                upload.responseJSON { response in
                    
                    print(response.request)  // original URL request
                    print(response.response) // URL response
                    print(response.data)     // server data
                    print(response.result)   // result of response serialization
                        //                        self.showSuccesAlert()
                     if let JSON = response.result.value {
                        print("JSON: \(JSON)")
                    }
                }
                
            case .failure(let encodingError):
                self.showSpinnerWithErrorStatus(error:"Photo did not upload (encoding error")
                DispatchQueue.main.asyncAfter(deadline: .now() + 0.5) {
                    self.hideSpinner()
                }
                print(encodingError)
            }
            
        }
    }
}

extension QuestionPictureViewController: UIImagePickerControllerDelegate,  UINavigationControllerDelegate {
    
    //not getting called?
    private func imagePickerController(picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [String : AnyObject]) {
        let chosenImage = info[UIImagePickerControllerOriginalImage] as! UIImage 
        photoImgView.contentMode = .scaleAspectFit
        photoImgView.image = chosenImage
        dismiss(animated: true, completion: nil)
    }
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        dismiss(animated: true, completion: nil)
    }
}
