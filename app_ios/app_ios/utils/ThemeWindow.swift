
import UIKit

final class ThemeWindow: UIWindow {
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func motionBegan(_ motion: UIEvent.EventSubtype, with event: UIEvent?) {
        if #available(iOS 13.0, *) {
            if motion == .motionShake {
                let currentTheme = themeProvider.theme
                let newTheme = currentTheme.mode == .dark ?
                    AppTheme.light(
                        shapes: currentTheme.shapes,
                        typography: currentTheme.typography
                    ) :
                    AppTheme.dark(
                        shapes: currentTheme.shapes,
                        typography: currentTheme.typography
                    )
                UIView.transition(
                    with: self,
                    duration: 0.5,
                    options: .transitionCrossDissolve,
                    animations: {
                        ThemeProviderCompat.shared.setTheme(newTheme)
                    },
                    completion: nil
                )
            }
        }
    }
}

extension ThemeWindow: Themeable {
    func apply(theme: any Theme) {
        if #available(iOS 13.0, *) {
            overrideUserInterfaceStyle = theme.mode == .dark ? .dark : .light
        }
    }
}
