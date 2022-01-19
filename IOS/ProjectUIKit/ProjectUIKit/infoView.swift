//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit

class infoView: UIViewController {


    
    @IBOutlet weak var phonenum: UILabel!
    @IBOutlet weak var doneButton: UIButton!
    @IBOutlet weak var callButton: UIButton!
    override func viewDidLoad() {
        super.viewDidLoad()
    }
    @IBAction func callPhone(_ sender: Any) {
        if let url = URL(string: "tel://\(phonenum.text!)"),
           
            UIApplication.shared.canOpenURL(url) {
            UIApplication.shared.open(url, options: [:], completionHandler: nil)
        }
    }
    
}

