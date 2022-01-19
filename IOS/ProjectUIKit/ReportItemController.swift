//
//  techItemController.swift
//  ProjectUIKit
//
//  Created by Samia El Ulabi on 1/18/22.
//

import UIKit

class ReportItemController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    var choice: String?
    private let tableView: UITableView = {
        let table = UITableView()
        let nib = UINib(nibName: "ReportTableViewCell", bundle: nil)
        
        table.register(nib, forCellReuseIdentifier: "ReportTableViewCell")
        return table
    }()
    
    private let items: [String]
    init(items: [String]){
        self.items = items
        super.init(nibName: nil, bundle: nil)
        
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    

    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        /*let nib = UINib(nibName: "ReportTableViewCell", bundle: nil)
        tableview.register(UITableViewCell.self, forCellReuseIdentifier: "Cell") */
        view.backgroundColor = .systemBackground
        view.addSubview(tableView)
         
        tableView.delegate = self
        tableView.dataSource = self        // Do any additional setup after loading the view.
    }
    
    override func viewDidLayoutSubviews() {
        super.viewDidLayoutSubviews()
        tableView.frame = view.bounds
    }
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return items.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ReportTableViewCell" , for: indexPath) as! ReportTableViewCell
        
        cell.reportLabel.text = items[indexPath.row]
        if (choice == "IDs"){
            cell.img.image = UIImage(named: "idpic")}
        else if (choice == "Technology"){
            cell.img.image = UIImage(named: "tech")}
        else if (choice == "Keys"){
            cell.img.image = UIImage(named: "key")}
        else{
            cell.img.image = UIImage(named: "other")}
        return cell
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        let alertController:UIAlertController = UIAlertController(title: "Report Item", message: "Report request has been sent to Admin, You will be notified soon!", preferredStyle: UIAlertController.Style.alert)
        let alertAction:UIAlertAction = UIAlertAction(title: "OK", style: UIAlertAction.Style.default, handler:nil)
        alertController.addAction(alertAction)
        present(alertController, animated: true, completion: nil)
        
        
        
    }

}
