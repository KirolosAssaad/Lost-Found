//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit


class verifyController: UIViewController {

    var choice : String?
    @IBOutlet weak var verifyButton: UIButton!
    @IBOutlet weak var A2: UITextField!
    @IBOutlet weak var Q2: UILabel!
    @IBOutlet weak var Q1: UILabel!
    @IBOutlet weak var A1: UITextField!
    @IBAction func verify(_ sender: UIButton) {
        self.showToast(message: "Wrong!", font: .systemFont(ofSize: 17.0))
    }
    @objc func handleBackgroundTap(_ sender: UITapGestureRecognizer){
        self.A1.resignFirstResponder();
        self.A2.resignFirstResponder();
    }
    
    
    @IBOutlet weak var AddImg: UIImageView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        if (choice == "IDs"){
            Q1.text = "What is the first name on the ID?"
            Q2.text = "What is the ID number?"
            AddImg.image = UIImage(named:"idpic")
        }
        else if(choice == "Technology"){
            Q1.text = "What is the model of the product?"
            Q2.text = "Does it have any unique features?"
            AddImg.image = UIImage(named:"tech")
        }
        else if (choice == "Keys"){
            Q1.text = "How many keys does it have?"
            Q2.text = "Does it have a keychain? If so, what is on it"
            AddImg.image = UIImage(named:"key")
        }
        else{
            Q1.text = "What is the product?"
            Q2.text = "What is the color of the product?"
            AddImg.image = UIImage(named:"other")
        }
        let tapRecognizer = UITapGestureRecognizer(target:self, action: #selector(self.handleBackgroundTap(_:)))
        tapRecognizer.cancelsTouchesInView = false
        self.view.isUserInteractionEnabled = true
        self.view.addGestureRecognizer(tapRecognizer)
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
                      NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
    }

    @objc func keyboardWillShow(notification: NSNotification) {
            
        guard let keyboardSize = (notification.userInfo?[UIResponder.keyboardFrameEndUserInfoKey] as? NSValue)?.cgRectValue else {
           return
        }
      
      self.view.frame.origin.y = 0 - keyboardSize.height
    }

    @objc func keyboardWillHide(notification: NSNotification) {
      self.view.frame.origin.y = 0
    }
}



