
import shared
import UIKit

class ErrorView: UIView {
    
    var onRetry: (() -> Void)?
    
    private let errorIconView = {
        let iconView = UIImageView(image: UIImage(imageLiteralResourceName: "error"))
        iconView.translatesAutoresizingMaskIntoConstraints = false
        return iconView
    }()
    
    private let errorLabel = {
        let label = UILabel()
        label.text = UiComponentsStrings.shared.errorOccurred.desc().localized()
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let retryButton = {
        let button = UIButton()
        button.setTitle(UiComponentsStrings.shared.retry.desc().localized(), for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        addErrorIcon()
        addErrorText()
        addRetryButton()
    }
    
    private func addErrorIcon() {
        addSubview(errorIconView)
        errorIconView.widthAnchor.constraint(equalToConstant: 48).isActive = true
        errorIconView.heightAnchor.constraint(equalToConstant: 48).isActive = true
        errorIconView.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
        errorIconView.topAnchor.constraint(equalTo: topAnchor, constant: 32).isActive = true
    }
    
    private func addErrorText() {
        addSubview(errorLabel)
        errorLabel.topAnchor.constraint(equalTo: errorIconView.bottomAnchor, constant: 16).isActive = true
        errorLabel.widthAnchor.constraint(equalToConstant: 280).isActive = true
        errorLabel.heightAnchor.constraint(equalToConstant: 20).isActive = true
        errorLabel.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
    }
    
    private func addRetryButton() {
        addSubview(retryButton)
        retryButton.addTarget(self, action: #selector(retryAction), for: .touchUpInside)
        retryButton.topAnchor.constraint(equalTo: errorLabel.bottomAnchor, constant: 16).isActive = true
        retryButton.widthAnchor.constraint(equalToConstant: 168).isActive = true
        retryButton.heightAnchor.constraint(equalToConstant: 32).isActive = true
        retryButton.centerXAnchor.constraint(equalTo: centerXAnchor).isActive = true
    }
    
    @objc
    private func retryAction() {
        onRetry?()
    }
}

extension ErrorView: Themeable {
    func apply(theme: any Theme) {
        clip(to: theme.shapes.large)
        retryButton.clipOval()
        backgroundColor = theme.colors.surface
        errorLabel.textColor = theme.colors.onBackground
        errorLabel.setTextStyle(style: theme.typography.h3)
        retryButton.backgroundColor = theme.colors.primary
        retryButton.titleLabel?.textColor = theme.colors.onPrimary
        retryButton.titleLabel?.setTextStyle(style: theme.typography.body1)
        errorIconView.tintColor = theme.colors.error
    }
}
