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

extension UITextField {
    func setTextStyle(style textStyle: TextStyle) {
        font = textStyle.fontFamily.fonts.first?.resource.uiFont(withSize: textStyle.fontSize)
    }
}

extension String {
    func image(textStyle: TextStyle, size: CGSize? = nil) -> UIImage {
        let nsString = self as NSString
        let attributes: [NSAttributedString.Key: Any] = [
            .font: textStyle.fontFamily.fonts[0].resource.uiFont(withSize: textStyle.fontSize)
        ]
        let size = size ?? nsString.size(withAttributes: attributes)
        return UIGraphicsImageRenderer(size: size).image { _ in
            nsString.draw(in: CGRect(origin: .zero, size: size),
                                    withAttributes: attributes)
        }
    }
}
