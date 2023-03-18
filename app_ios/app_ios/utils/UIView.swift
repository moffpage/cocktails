//
//  UIView.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 17.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

extension UIView {
    func clip(to shape: RoundedCornerShape) {
        clipsToBounds = true
        layer.cornerRadius = shape.cornerRadius
    }
    
    func clipOval() {
        clipsToBounds = true
        layer.cornerRadius = bounds.height / 2
    }
}
