//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit
import Foundation

extension String {
    func index(from: Int) -> Index {
        return self.index(startIndex, offsetBy: from)
    }

    func substring(from: Int) -> String {
        let fromIndex = index(from: from)
        return String(self[fromIndex...])
    }

    func substring(to: Int) -> String {
        let toIndex = index(from: to)
        return String(self[..<toIndex])
    }

    func substring(with r: Range<Int>) -> String {
        let startIndex = index(from: r.lowerBound)
        let endIndex = index(from: r.upperBound)
        return String(self[startIndex..<endIndex])
    }
}

class ViewController: UIViewController {

    @IBOutlet weak var keyIcon: UIImageView!
    @IBOutlet weak var signIn: UIButton!
    @IBOutlet weak var emailText: UITextField!
    @IBOutlet weak var passText: UITextField!
    @IBOutlet weak var mailIcon: UIImageView!
    
    @IBAction func loginAuth(_ sender: Any) {
        let url = URL(string: "http://192.168.100.39:3000/userVerify")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
       // getting values from text fields
                      let username=emailText.text
                      let password = passText.text
      
                      //creating the post parameter by concatenating the keys and values from text field
                      let postParameters = "username="+username!+"&password="+password!;
      
                      //adding the parameters to request body
                      request.httpBody = postParameters.data(using: String.Encoding.utf8)
      
        
        NSURLConnection.sendAsynchronousRequest(request, queue: OperationQueue.main) {(response, data, error) in
            guard let data = data else { return }
            
            
            var getData = String(data: data, encoding: .utf8)!
            var SID = getData.substring(to: 17)
           SID = SID.substring(from: 8)
            
                if (getData != "[]"){
                    let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
                    let vc = storyBoard.instantiateViewController(withIdentifier: "choiceController") as! choiceController
                    vc.id = SID
                    self.navigationController?.pushViewController(vc, animated: true)
                }
                else{
                    self.showToast(message: "User not found!", font: .systemFont(ofSize: 17.0))
                }
            print(getData)
            
             
            
            print(SID)
            
        }
        
    }
        

    @objc func handleBackgroundTap(_ sender: UITapGestureRecognizer){
        self.emailText.resignFirstResponder();
        self.passText.resignFirstResponder();
    }
    override func viewDidLoad() {
        super.viewDidLoad()
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

