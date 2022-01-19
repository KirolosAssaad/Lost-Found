//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit

class addController: UIViewController, UIPickerViewDelegate, UIPickerViewDataSource {
    var choice : String?
    var id : String?
    @IBAction func submitButton(_ sender: Any) {
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyBoard.instantiateViewController(withIdentifier: "formController") as! formController
        vc.choice = choice
        vc.id = id
        self.navigationController?.pushViewController(vc, animated: true)
    }
    func numberOfComponents(in pickerView: UIPickerView) -> Int {
        return 1
    }
    
    func pickerView(_ pickerView: UIPickerView, numberOfRowsInComponent component: Int) -> Int {
        return pickerData.count
    }
    
    func pickerView(_ pickerView: UIPickerView, titleForRow row: Int, forComponent component: Int) -> String? {
        return pickerData[row]
    }
    
    func pickerView(_ pickerView: UIPickerView, didSelectRow row: Int, inComponent component: Int) {
        let value = pickerData[row]
        choice = value
    }

    @IBOutlet weak var pickerAdd: UIPickerView!
    var pickerData: [String] = [String]()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        self.pickerAdd.delegate = self
        self.pickerAdd.dataSource = self
        pickerData = ["IDs", "Technology", "Keys", "Other"]
      
    }

   
}


