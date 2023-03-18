//
//  TextStyle.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 17.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

extension UILabel {
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension UITextView {
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension UISegmentedControl {
    func setTextStyle(style textStyle: TextStyle) {
        setTitleTextAttributes(
            [.font: textStyle.fontFamily.fonts[0].resource.uiFont(withSize: textStyle.fontSize)],
            for: .normal
        )
    }
}
