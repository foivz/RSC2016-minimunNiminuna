//
//  QuizViewController.swift
//  RSC
//
//  Created by MTLab on 26/11/2016.
//  Copyright © 2016 minimun. All rights reserved.
//

import UIKit

class QuizViewController: UIViewController {
    @IBOutlet weak var questionNoTxtField: UITextField!
    @IBOutlet weak var questionTxtView: UITextView!
    enum questionCats : Int{
        case textinput = 1
        case checkbox = 2
        case multiple = 3
        case multimediaChoice = 4
        case multimediaUpload = 5
    }
    var manager: QuizWrapper?
    var currentController: UIViewController?
    private lazy var questionViewController: QuestionPictureViewController = {
        // Load Storyboard
        let storyboard = UIStoryboard(name: "Main", bundle: Bundle.main)
        
        // Instantiate View Controller
        var viewController = storyboard.instantiateViewController(withIdentifier: StoryboardConstants.QuestionPictureViewController) as! QuestionPictureViewController
        
        // Add View Controller as Child View Controller
        self.add(asChildViewController: viewController)
        
        return viewController
    }()

    private func add(asChildViewController viewController: UIViewController) {
        // Add Child View Controller
        addChildViewController(viewController)
        
        // Add Child View as Subview
        view.addSubview(viewController.view)
        
        // Configure Child View
        viewController.view.frame = view.bounds
        viewController.view.autoresizingMask = [.flexibleWidth, .flexibleHeight]
        
        // Notify Child View Controller
        viewController.didMove(toParentViewController: self)
    }
    private func remove(asChildViewController viewController: UIViewController) {
        // Notify Child View Controller
        viewController.willMove(toParentViewController: nil)
        
        // Remove Child View From Superview
        viewController.view.removeFromSuperview()
        
        // Notify Child View Controller
        viewController.removeFromParentViewController()
    }
    private func displayQuestion() {
        questionTxtView.text = "Question nr.\(manager!.currentQuestionNo)"
        questionTxtView.text =  manager!.questions[manager!.currentQuestionNo].question
    }
    private func displayAppropriateController(question: Question) {
        if let currentC = currentController {
            remove(asChildViewController: currentC)
        }
        switch question.category.idCat {
            case questionCats.multimediaUpload.rawValue:
            currentController = questionViewController
            
            questionViewController.question = manager!.questions[manager!.currentQuestionNo]
            add(asChildViewController: questionViewController)
            default:
            currentController = questionViewController
            add(asChildViewController: questionViewController)
            }
        }
    func respondToSwipeGesture(gesture: UIGestureRecognizer) {
        
        if let swipeGesture = gesture as? UISwipeGestureRecognizer {
            switch swipeGesture.direction {
            case UISwipeGestureRecognizerDirection.left:
                play()
            default:
                break
            }
        }
    }
    private func play (){
        if manager!.currentQuestionNo<manager!.questions.count {
            displayAppropriateController(question: manager!.questions[ manager!.currentQuestionNo])
            displayQuestion()
            manager!.nextQuestion()
        }
        else {end()}
    }
    private func end(){
        
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        if UserDefaults.standard.bool(forKey: "didNoticedUserAboutSwipe") == false{
            showAlert(title: "Hello player!", message: "To skip on next question swipe left")
            UserDefaults.standard.set(true, forKey: "didNoticedUserAboutSwipe")
        }
        var swipeLeft = UISwipeGestureRecognizer(target: self, action: "respondToSwipeGesture:")
        swipeLeft.direction = UISwipeGestureRecognizerDirection.left
        self.view.addGestureRecognizer(swipeLeft)
        play()
    }

    }
