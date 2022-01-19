//
//  FindTableViewCell.swift
//  ProjectUIKit
//
//  Created by Samia El Ulabi on 1/17/22.
//

import UIKit

class FindTableViewCell: UITableViewCell {
    
    @IBOutlet var mylabel: UILabel!
    @IBOutlet var myimage: UIImageView!
    

    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
