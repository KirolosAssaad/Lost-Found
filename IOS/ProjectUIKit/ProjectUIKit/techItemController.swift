//
//  techItemController.swift
//  ProjectUIKit
//
//  Created by Samia El Ulabi on 1/18/22.
//

import UIKit

class techItemController: UIViewController, UITableViewDelegate, UITableViewDataSource {
    
    var choice: String?
    
    @IBOutlet weak var tableview: UITableView!
    
    private let tableView: UITableView = {
        let table = UITableView()
        let nib = UINib(nibName: "FindTableViewCell", bundle: nil)
        
        table.register(nib, forCellReuseIdentifier: "FindTableViewCell")
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
        /*let nib = UINib(nibName: "FindTableViewCell", bundle: nil)
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
        let cell = tableView.dequeueReusableCell(withIdentifier: "FindTableViewCell" , for: indexPath) as! FindTableViewCell
        
        cell.mylabel.text = items[indexPath.row]
        if (choice == "IDs"){
            cell.myimage.image = UIImage(named: "idpic")}
        else if (choice == "Technology"){
            cell.myimage.image = UIImage(named: "tech")}
        else if (choice == "Keys"){
            cell.myimage.image = UIImage(named: "key")}
        else{
            cell.myimage.image = UIImage(named: "other")}
        
        return cell
        
    }
    
    func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        tableView.deselectRow(at: indexPath, animated: true)
        
        let storyBoard: UIStoryboard = UIStoryboard(name: "Main", bundle: nil)
        let vc = storyBoard.instantiateViewController(withIdentifier: "verifyController") as! verifyController
        vc.choice = choice
        self.navigationController?.pushViewController(vc, animated: true)
        
        
        
    }
    

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
