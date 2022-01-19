//
//  notifyViewtable.swift
//  ProjectUIKit
//
//  Created by Mariam Elsaqa on 19/01/2022.
//

import UIKit

class notifyViewtable: UIViewController {
    
    @IBOutlet weak var idswitch: UISwitch!
    @IBOutlet weak var techSwitch: UISwitch!
    @IBOutlet weak var otherswitch: UISwitch!
    @IBOutlet weak var keysswitch: UISwitch!
    @IBAction func idOn(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "switchState3")
    }
    @IBAction func techON(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "switchState2")
    }
    @IBAction func keyOn(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "switchState1")
    }
    @IBAction func otherOn(_ sender: UISwitch) {
        UserDefaults.standard.set(sender.isOn, forKey: "switchState")
    }
    override func viewDidLoad() {
        super.viewDidLoad()
        idswitch.isOn = UserDefaults.standard.bool(forKey: "switchState3")
        techSwitch.isOn = UserDefaults.standard.bool(forKey: "switchState2")
        keysswitch.isOn = UserDefaults.standard.bool(forKey: "switchState1")
        otherswitch.isOn = UserDefaults.standard.bool(forKey: "switchState3")
    }
}
