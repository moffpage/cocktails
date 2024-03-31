
import shared
import UIKit
import SnapKit
import Lottie

final class LoadingView: UIView {
    private let animationView: LottieAnimationView = {
        let animationView = LottieAnimationView(
            filePath: Animations.shared.cocktail.path
        )
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
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
        addLottieAnimationView()
    }
    
    init() {
        super.init(frame: UIScreen.main.bounds)
        themeProvider.register(observer: self)
        addLottieAnimationView()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addLottieAnimationView() {
        addSubview(animationView)
        animationView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }
}

extension LoadingView: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.background.withAlphaComponent(0.5)
    }
}
