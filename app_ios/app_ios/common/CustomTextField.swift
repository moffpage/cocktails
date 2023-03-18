//
//  CustomTextField.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

class CustomTextField: UITextField {
    private let padding: CGFloat = 8
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func becomeFirstResponder() -> Bool {
        let isFirstResponder = super.becomeFirstResponder()
        
        if isFirstResponder && text?.isEmpty == false {
            rightViewMode = .always
        } else {
            rightViewMode = .never
        }
        
        return isFirstResponder
    }
    
    override func resignFirstResponder() -> Bool {
        let resignedAsResponder = super.resignFirstResponder()
        
        if resignedAsResponder {
            rightViewMode = .never
        }
        
        return resignedAsResponder
    }
    
    override func textRect(forBounds bounds: CGRect) -> CGRect {
        var textRect = super.textRect(forBounds: bounds)
        textRect.origin.x += padding
        return textRect
    }

    override func editingRect(forBounds bounds: CGRect) -> CGRect {
        var editingRect = super.editingRect(forBounds: bounds)
        editingRect.origin.x += padding
        return editingRect
    }
    
    override func leftViewRect(forBounds bounds: CGRect) -> CGRect {
        var leftViewRect = super.leftViewRect(forBounds: bounds)
        leftViewRect.origin.x += padding
        return leftViewRect
    }
    
    override func rightViewRect(forBounds bounds: CGRect) -> CGRect {
        var rightViewRect = super.rightViewRect(forBounds: bounds)
        rightViewRect.origin.x -= padding
        return rightViewRect
    }
}

extension CustomTextField: Themeable {
    func apply(theme: any Theme) {
        clip(to: theme.shapes.medium)
        tintColor = theme.colors.primary
        textColor = theme.colors.onBackground
        backgroundColor = theme.colors.surface
        leftView?.tintColor = theme.colors.onSurface
        rightView?.tintColor = theme.colors.onSurface
    }
}
