//
//  ViewController.swift
//  ProjectUIKit
//
//  Created by Mohi El Ulabi on 1/17/22.
//

import UIKit

class findController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var choice: String?
    @IBOutlet weak var tableview: UITableView!
    struct category {
        let title: String
        let items: [String]
    }
    
    private let data: [category] = [
        category(title: "IDs", items: ["ID 1" , "ID 2", "ID 3"]),
        category(title: "Technology", items: ["Airpods" , "Laptop", "phone", "speaker" ]),
        category(title: "Keys", items: ["car keys" , "house keys"]),
        category(title: "Other", items: ["Bag" , "Purse", "wallet"])
    ]

    let mydata = ["IDs" ,"Technology","Keys","Other"]
    let dataImg = ["id", "tech", "key", "other"]
    override func viewDidLoad() {
        super.viewDidLoad()
        let nib = UINib(nibName: "FindTableViewCell", bundle: nil)
        tableview.register(nib, forCellReuseIdentifier: "FindTableViewCell")
        tableview.delegate = self
        tableview.dataSource = self
      
    }

    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return data.count
    }
    

    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "FindTableViewCell" , for: indexPath) as! FindTableViewCell
        
        cell.mylabel.text = data[indexPath.row].title
        cell.myimage.image = UIImage(named: dataImg[indexPath.row])
        return cell
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
    let Category = data[indexPath.row]
        choice = mydata[indexPath.row]
        let vc = techItemController(items: Category.items)
        vc.title = Category.title
        vc.choice = choice
        navigationController?.pushViewController(vc, animated: true)
           
        }
   
}
