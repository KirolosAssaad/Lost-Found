//
//  ReportTableViewCell.swift
//  ProjectUIKit
//
//  Created by Samia El Ulabi on 1/19/22.
//

import UIKit

class ReportTableViewCell: UITableViewCell {
    

  
    @IBOutlet weak var img: UIImageView!
    @IBOutlet weak var reportLabel: UILabel!
    
    @IBOutlet weak var repbutton: UIButton!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        // Initialization code
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)

        // Configure the view for the selected state
    }
    
}
