//
//  QRJoinInViewController.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import UIKit
import AVFoundation
import Alamofire
import Unbox
class QRJoinInViewController:  UIViewController, AVCaptureMetadataOutputObjectsDelegate {
    var captureSession: AVCaptureSession!
    var previewLayer: AVCaptureVideoPreviewLayer!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.navigationItem.setHidesBackButton(true, animated:true)
        title = "Capture Quiz QR"
        view.backgroundColor = UIColor.black
        captureSession = AVCaptureSession()
        
        let videoCaptureDevice = AVCaptureDevice.defaultDevice(withMediaType: AVMediaTypeVideo)
        let videoInput: AVCaptureDeviceInput
        
        do {
            videoInput = try AVCaptureDeviceInput(device: videoCaptureDevice)
        } catch {
            return
        }
        
        if (captureSession.canAddInput(videoInput)) {
            captureSession.addInput(videoInput)
        } else {
            failed()
            return
        }
        
        let metadataOutput = AVCaptureMetadataOutput()
        
        if (captureSession.canAddOutput(metadataOutput)) {
            captureSession.addOutput(metadataOutput)
            
            metadataOutput.setMetadataObjectsDelegate(self, queue: DispatchQueue.main)
            metadataOutput.metadataObjectTypes = [AVMetadataObjectTypeQRCode]
        } else {
            failed()
            return
        }
        
        previewLayer = AVCaptureVideoPreviewLayer(session: captureSession);
        previewLayer.frame = view.layer.bounds;
        previewLayer.videoGravity = AVLayerVideoGravityResizeAspectFill;
        view.layer.addSublayer(previewLayer);
        
        captureSession.startRunning();
    }
    
    func failed() {
        showAlert(title: "Scanning bit supported", message: "")
        captureSession = nil
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        if (captureSession?.isRunning == false) {
            captureSession.startRunning();
        }
    }
    
    override func viewWillDisappear(_ animated: Bool) {
        super.viewWillDisappear(animated)
        
        if (captureSession?.isRunning == true) {
            captureSession.stopRunning();
        }
    }
    
    func captureOutput(_ captureOutput: AVCaptureOutput!, didOutputMetadataObjects metadataObjects: [Any]!, from connection: AVCaptureConnection!) {
        captureSession.stopRunning()
        
        if let metadataObject = metadataObjects.first {
            let readableObject = metadataObject as! AVMetadataMachineReadableCodeObject;
            
            AudioServicesPlaySystemSound(SystemSoundID(kSystemSoundID_Vibrate))
            found(code: readableObject.stringValue);
        }
        
    }
    func getQuizById (id: String) {
        let url = URL(string: AppSpecificConstants.serverAddr+"/quiz/"+id)
        
        showSpinner()
        
        Alamofire.request(url!, method: .get, encoding: URLEncoding.default).responseJSON { (response) in
            switch response.result {
            
            case .success(let value):
                print(value)
                    do {
                        let quiz:Quiz = try unbox(dictionary: value as! UnboxableDictionary)
                        let teams: [Team] = try unbox(dictionary: value as! UnboxableDictionary, atKey: "teams")
                        let prizes: [Prize] = try unbox(dictionary: value as! UnboxableDictionary, atKey: "prizes")
                        let questions: [Question] = try unbox(dictionary: value as! UnboxableDictionary, atKey: "questions")
                        let vc = self.storyboard!.instantiateViewController(withIdentifier: StoryboardConstants.ChooseTeamViewController) as! ChooseTeamTableViewController
                        vc.quizData = QuizWrapper(quiz: quiz,teams: teams,prizes: prizes,questions: questions)
                        self.hideSpinner()
                        self.navigationController!.pushViewController(vc, animated: true)
                    }
                    catch {
                       print("An error occured: \(error)")
                        self.showAlert(title: "Error fetching quiz from server", message: "Please try again with valid QR code")
                        self.hideSpinner()
                        self.captureSession.startRunning()
                    }
                
            case .failure(let error):
                print(error)
                self.showAlert(title: "Comunication error", message: "Please try again")
                self.hideSpinner()
                self.captureSession.startRunning()
            }
        }
    }
    func found(code: String) {
        var actions = [UIAlertAction]()
        actions.append(UIAlertAction(title: "Proceed to quiz", style: .default){ _ in
            print("qr code is \(code)")
            self.getQuizById(id: code)
        })
        actions.append(UIAlertAction.init(title: "Cancel", style: .cancel){
             _ in
                self.captureSession.startRunning()
        })
        showActionSheet(title: "Quiz spotted!", actions: actions)
        
        
    }
    
    override var prefersStatusBarHidden: Bool {
        return true
    }
    
    override var supportedInterfaceOrientations: UIInterfaceOrientationMask {
        return .portrait
    }
    
}
