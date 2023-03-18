//
//  CocktailCharacteristicChip.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

class CocktailCharacteristicChip: UIView {
    private let text: String
    private let iconName: String?
    
    private let iconView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    private let textView = {
        let textView = UILabel()
        textView.backgroundColor = .clear
        textView.translatesAutoresizingMaskIntoConstraints = false
        return textView
    }()
    
    init(text: String, iconName: String?) {
        self.text = text
        self.iconName = iconName
        super.init(frame: .zero)
        themeProvider.register(observer: self)
    }
    
    override init(frame: CGRect) {
        self.text = ""
        self.iconName = nil
        super.init(frame: frame)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        clipOval()
        addCategoryIcon()
        addText()
        textView.text = text
    }
    
    private func addCategoryIcon() {
        if let iconName = iconName {
            addSubview(iconView)
            iconView.image = UIImage(imageLiteralResourceName: iconName)
            iconView.widthAnchor.constraint(equalToConstant: 24).isActive = true
            iconView.heightAnchor.constraint(equalToConstant: 24).isActive = true
            iconView.centerYAnchor.constraint(equalTo: centerYAnchor).isActive = true
            iconView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 16).isActive = true
        }
    }
    
    private func addText() {
        addSubview(textView)
        textView.topAnchor.constraint(equalTo: topAnchor).isActive = true
        textView.bottomAnchor.constraint(equalTo: bottomAnchor).isActive = true
        textView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: iconName == nil ? 16 : 48).isActive = true
        textView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -16).isActive = true
    }
}

extension CocktailCharacteristicChip: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.surface
        iconView.tintColor = theme.colors.primary
        textView.textColor = iconName == nil ? theme.colors.secondary : theme.colors.primary
        textView.setTextStyle(style: theme.typography.h4)
    }
}
