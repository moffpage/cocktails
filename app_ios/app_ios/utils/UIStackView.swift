//
//  UIStackView.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 19.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

extension UIStackView {
    func addArrangedSubviews(_ subviews: UIView...) {
        for subview in subviews {
            addArrangedSubview(subview)
        }
    }
}
