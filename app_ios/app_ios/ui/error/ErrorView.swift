
import shared
import UIKit
import SnapKit

class ErrorView: UIView {
    
    var onRetry: (() -> Void)?
    
    private let errorIconView = UIImageView(image: UIImage(imageLiteralResourceName: "error"))
    
    private let errorLabel = {
        let label = UILabel()
        label.text = UiComponentsStrings.shared.errorOccurred.desc().localized()
        label.textAlignment = .center
        return label
    }()
    
    private let retryButton = {
        let button = UIButton()
        button.setTitle(UiComponentsStrings.shared.retry.desc().localized(), for: .normal)
        return button
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addErrorIcon()
        addErrorText()
        addRetryButton()
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addErrorIcon() {
        addSubview(errorIconView)
        errorIconView.snp.makeConstraints { make in
            make.top.equalToSuperview().inset(32)
            make.size.equalTo(48)
            make.centerX.equalToSuperview()
        }
    }
    
    private func addErrorText() {
        addSubview(errorLabel)
        errorLabel.snp.makeConstraints { make in
            make.top.equalTo(errorIconView.snp.bottom).offset(16)
            make.width.equalTo(280)
            make.centerX.equalToSuperview()
        }
    }
    
    private func addRetryButton() {
        addSubview(retryButton)
        retryButton.addTarget(self, action: #selector(retryAction), for: .touchUpInside)
        retryButton.snp.makeConstraints { make in
            make.top.equalTo(errorLabel.snp.bottom).offset(16)
            make.width.equalTo(168)
            make.height.equalTo(32)
            make.centerX.equalToSuperview()
        }
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
