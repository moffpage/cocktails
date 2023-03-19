//
//  LoadingView.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 11.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import UIKit
import Lottie

class LoadingView: UIView {
    private let animationView = {
        let animationView = LottieAnimationView(name: "cocktail_animation")
        animationView.loopMode = .loop
        animationView.contentMode = .scaleAspectFit
        return animationView
    }()
    
    var isAnimating: Bool = false {
        didSet {
            if isAnimating {
                isHidden = false
                animationView.play()
            } else {
                isHidden = true
                animationView.stop()
            }
        }
    }
    
    init() {
        super.init(frame: UIScreen.main.bounds)
        themeProvider.register(observer: self)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        addSubview(animationView)
        animationView.center = center
    }
}

extension LoadingView: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.background.withAlphaComponent(0.5)
    }
}
