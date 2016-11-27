//
//  QuestionPictureViewController.swift
//  RSC
//
//  Created by MTLab on 27/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import Foundation
import UIKit

class QuestionPictureViewController: UIViewController {
    var question : Question? {
        didSet {
            print("i got new question")
        }
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    @IBAction func onAddPicture(_ sender: UIButton) {
    }
    @IBOutlet weak var pictureButton: UIButton!
    
}
