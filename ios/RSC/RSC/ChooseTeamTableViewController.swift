//
//  AddMoreStuffViewController.swift
//  PokeDex
//
//  Created by Infinum on 25/07/16.
//  Copyright © 2016 infinumSA. All rights reserved.
//

import Foundation
import UIKit


class ChooseTeamTableViewController: UITableViewController {
    
    
    var quizData: QuizWrapper? {
        didSet {
            tableView.reloadData()
        }
    }
 
    
    override func viewDidLoad() {
        //tableView.reloadData()
    }
    override func numberOfSections(in tableView: UITableView) -> Int {
       return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        if let data = quizData {
            return data.teams.count
        }
        return 0
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        let cell = tableView.dequeueReusableCell(withIdentifier:StoryboardConstants.ChooseTeamCellID , for: indexPath)
        cell.textLabel?.text = quizData!.teams[indexPath.row].name
        cell.detailTextLabel?.text = "No. of players:\(quizData!.teams[indexPath.row].members!.count)"
        return cell
    }
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        let vc = storyboard?.instantiateViewController(withIdentifier: StoryboardConstants.QuizViewController) as! QuizViewController
        vc.manager = quizData
        navigationController!.pushViewController(vc, animated: true)
    }
    
}

