//
//  TimeTravelButton.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 19.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit

internal class TimeTravelButton: UIButton {
    private let imageName: String
    
    init(imageName: String) {
        self.imageName = imageName
        super.init(frame: .zero)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        setImage(UIImage(imageLiteralResourceName: imageName), for: .normal)
    }
}

extension TimeTravelButton: Themeable {
    func apply(theme: any Theme) {
        tintColor = theme.colors.secondary
    }
}
