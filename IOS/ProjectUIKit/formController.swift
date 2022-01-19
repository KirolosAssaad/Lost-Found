//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit

class formController: UIViewController {
    
    @IBOutlet weak var imgButton: UIButton!
    
    @IBOutlet weak var A2: UITextField!
    @IBOutlet weak var Q2: UILabel!
    @IBOutlet weak var A1: UITextField!
    @IBOutlet weak var Q1: UILabel!
    @IBOutlet weak var imgOut: UIImageView!
    @objc func handleBackgroundTap(_ sender: UITapGestureRecognizer){
        self.A1.resignFirstResponder();
        self.A2.resignFirstResponder();
    }
    var id: String?
    var choice: String?
    
    @IBAction func addt(_ sender: UIButton) {
        self.showToast(message: "Item Added!", font: .systemFont(ofSize: 17.0))
        
        let url = URL(string: "http://192.168.100.39:3000/addObject")!
        var request = URLRequest(url: url)
        request.httpMethod = "POST"
        
       // getting values from text fields
                      let name = "name"
                      let description = "description"
                      let ID = id
                      let category = choice;
      
        if(choice == nil){
            choice = "IDs"
        }
        print(choice)
        var postparameters = "name"+name
        postparameters += "&description="+description
        postparameters += "&image="+"imgPath"
        postparameters += "&SID="+id!
        postparameters += "&category="+choice!
      
                      //adding the parameters to request body
                      request.httpBody = postparameters.data(using: String.Encoding.utf8)
        
        NSURLConnection.sendAsynchronousRequest(request, queue: OperationQueue.main) {(response, data, error) in
            guard let data = data else { return }
            
            
            var getData = String(data: data, encoding: .utf8)!
            
        }
        
        let urlAnswer = URL(string: "http://192.168.100.39:3000/addAnswer")!
        var requestAnswer = URLRequest(url: urlAnswer)
        requestAnswer.httpMethod = "POST"
        
       // getting values from text fields
        let answer1 = A1.text
        let answer2 = A2.text
         
        //print(A1.text)
        var postparametersAnswer = "answer1="+answer1!
        postparametersAnswer += "&answer2="+answer2!
        postparametersAnswer += "&answer3="+"x"
      
                      //adding the parameters to request body
                      requestAnswer.httpBody = postparametersAnswer.data(using: String.Encoding.utf8)
        
        NSURLConnection.sendAsynchronousRequest(requestAnswer, queue: OperationQueue.main) {(response, data, error) in
            guard let data = data else { return }
            
            
            var getData = String(data: data, encoding: .utf8)!}
    }
    @IBOutlet weak var add: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
        imgOut.backgroundColor = .secondarySystemBackground
        if(choice == nil){
            choice = "IDs"
        }
        if (choice == "IDs"){
            Q1.text = "What is the first name on the ID?"
            Q2.text = "What is the ID number?"
        }
        else if(choice == "Technology"){
            Q1.text = "What is the model of the product?"
            Q2.text = "Does it have any unique features?"        }
        else if (choice == "Keys"){
            Q1.text = "How many keys does it have?"
            Q2.text = "Does it have a keychain? If so, what is on it"        }
        else{
            Q1.text = "What is the product?"
            Q2.text = "What is the color of the product?"        }
        let tapRecognizer = UITapGestureRecognizer(target:self, action: #selector(self.handleBackgroundTap(_:)))
        tapRecognizer.cancelsTouchesInView = false
        self.view.isUserInteractionEnabled = true
        self.view.addGestureRecognizer(tapRecognizer)
        NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillShow), name: UIResponder.keyboardWillShowNotification, object: nil)
                      NotificationCenter.default.addObserver(self, selector: #selector(ViewController.keyboardWillHide), name: UIResponder.keyboardWillHideNotification, object: nil)
        
      
    }
    @IBAction func camButton(_ sender: UIButton) {
        let picker = UIImagePickerController()
        picker.sourceType = .camera
        picker.delegate = self
        present(picker, animated: true)
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

extension formController: UIImagePickerControllerDelegate, UINavigationControllerDelegate{
    
    func imagePickerControllerDidCancel(_ picker: UIImagePickerController) {
        picker.dismiss(animated: true, completion: nil)
    }
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        picker.dismiss(animated: true, completion: nil)
        guard let image = info[UIImagePickerController.InfoKey.originalImage] as? UIImage else{
            return
        }
        imgOut.image = image
    }
}

extension UIViewController {

func showToast(message : String, font: UIFont) {

    let toastLabel = UILabel(frame: CGRect(x: self.view.frame.size.width/2 - 75, y: self.view.frame.size.height-100, width: 150, height: 35))
    toastLabel.backgroundColor = UIColor.black.withAlphaComponent(0.6)
    toastLabel.textColor = UIColor.white
    toastLabel.font = font
    toastLabel.textAlignment = .center;
    toastLabel.text = message
    toastLabel.alpha = 1.0
    toastLabel.layer.cornerRadius = 10;
    toastLabel.clipsToBounds  =  true
    self.view.addSubview(toastLabel)
    UIView.animate(withDuration: 4.0, delay: 0.1, options: .curveEaseOut, animations: {
         toastLabel.alpha = 0.0
    }, completion: {(isCompleted) in
        toastLabel.removeFromSuperview()
    })
}
}
