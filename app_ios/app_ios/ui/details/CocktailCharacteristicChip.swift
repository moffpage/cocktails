
import shared
import UIKit
import SnapKit

final class CocktailCharacteristicChip: UIView {
    private let iconName: String?
    
    private let iconView = UIImageView()
    private let textView = UILabel()
    
    init(text: String, iconName: String?) {
        self.iconName = iconName
        super.init(frame: .zero)
        addCategoryIcon()
        addText()
        textView.text = text
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func layoutSubviews() {
        super.layoutSubviews()
        clipOval()
    }
    
    private func addCategoryIcon() {
        if let iconName = iconName {
            addSubview(iconView)
            iconView.image = UIImage(imageLiteralResourceName: iconName)
            iconView.snp.makeConstraints { make in
                make.size.equalTo(24)
                make.centerY.equalToSuperview()
                make.leading.equalToSuperview().inset(16)
            }
        }
    }
    
    private func addText() {
        addSubview(textView)
        textView.snp.makeConstraints { make in
            make.leading.equalToSuperview().inset(iconName == nil ? 16 : 48)
            make.trailing.equalToSuperview().inset(16)
            make.verticalEdges.equalToSuperview()
        }
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
