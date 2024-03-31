
import shared
import UIKit
import Lottie

class LoadingView: UIView {
    private let animationView = {
        let animationView = LottieAnimationView(name: Animations.shared.cocktail.fileName)
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
    
    convenience init() {
        self.init(frame: UIScreen.main.bounds)
    }
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
        addLottieAnimationView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addLottieAnimationView() {
        addSubview(animationView)
        animationView.center = center
    }
}

extension LoadingView: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.background.withAlphaComponent(0.5)
    }
}
