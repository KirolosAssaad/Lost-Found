//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit

class choiceController: UIViewController {
    var id: String?
    @IBOutlet weak var notifybtn: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
            notifybtn.layer.masksToBounds = true
        notifybtn.layer.cornerRadius = notifybtn.frame.width/2

    }
    
    @IBAction func addButton(_ sender: Any) {
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyBoard.instantiateViewController(withIdentifier: "addController") as! addController
        
        vc.id = id
        self.navigationController?.pushViewController(vc, animated: true)
    }
    
}

