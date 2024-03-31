
import shared
import UIKit
import SnapKit

final class CocktailDetailsView: UIView {
    private let backIconView: UIView = {
        let closeImageView = UIImageView(
            frame: CGRect(
                origin: CGPoint(x: 8, y: 8),
                size: CGSize(
                    width: 24,
                    height: 24
                )
            )
        )
        closeImageView.image = UIImage(imageLiteralResourceName: "back")
        closeImageView.tintColor = .white
        
        let surfaceView = UIView()
        surfaceView.clip(to: RoundedCornerShape(cornerRadius: 20.0))
        surfaceView.addSubview(closeImageView)
        
        return surfaceView
    }()
    
    private let cocktailImageView = UIImageView()
    
    private let cocktailTitleView: UILabel = {
        let label = UILabel()
        label.textAlignment = .center
        return label
    }()
    
    private let instructionsLabel: UILabel = {
        let label = UILabel()
        label.text = CoreStrings.shared.instructions.desc().localized()
        label.backgroundColor = .clear
        return label
    }()
    
    private let instructionsTextView: UITextView = {
        let textView = UITextView()
        textView.backgroundColor = .clear
        return textView
    }()
    
    private let characteristicsView: UIStackView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 16
        return stackView
    }()
    
    private let onBackClick: (() -> Void)?
    
    init(onBackClick: @escaping () -> Void) {
        self.onBackClick = onBackClick
        super.init(frame: .zero)
        addSubviews()
        constrainSubviews()
        addOnBackClickBehavior()
        themeProvider.register(observer: self)
    }
    
    override init(frame: CGRect) {
        fatalError("init(onBackClick: () -> Void) must be used")
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func bind(model: CocktailDetailsComponentModel) {
        cocktailImageView.kf.setImage(
            with: URL(string: model.imageUrl)
        ) { [unowned self] result in
            if case .success(_) = result {
                self.addGradient()
            }
        }
        cocktailTitleView.text = model.cocktailName
        instructionsTextView.text = model.preparationInstruction
        
        let alcoholicSign: StringResource
        if model.isAlcoholic {
            alcoholicSign = CoreStrings.shared.alcoholic
        } else {
            alcoholicSign = CoreStrings.shared.nonAlcoholic
        }
        
        if let category = model.category {
            bindCharacteristics(
                text: model.glassType,
                iconName: category.iconName
            )
            bindCharacteristics(
                text: alcoholicSign.desc().localized(),
                iconName: nil
            )
        }
    }
    
    private func addSubviews() {
        addSubview(cocktailImageView)
        addSubview(characteristicsView)
        addSubview(instructionsLabel)
        addSubview(instructionsTextView)
        addSubview(backIconView)
        cocktailImageView.addSubview(cocktailTitleView)
    }
    
    private func constrainSubviews() {
        backIconView.snp.makeConstraints { make in
            make.top.equalTo(safeAreaLayoutGuide.snp.top).inset(8)
            make.size.equalTo(40)
            make.leading.equalToSuperview().inset(16)
        }
        
        cocktailImageView.snp.makeConstraints { make in
            make.top.width.equalToSuperview()
            make.height.equalTo(cocktailImageView.snp.width)
        }
        
        cocktailTitleView.snp.makeConstraints { make in
            make.bottom.equalTo(cocktailImageView.snp.bottom).inset(16)
            make.horizontalEdges.equalToSuperview()
        }
        
        instructionsLabel.snp.makeConstraints { make in
            make.top.equalTo(characteristicsView.snp.bottom).offset(36)
            make.width.equalTo(140)
            make.height.equalTo(24)
            make.leading.equalToSuperview().inset(16)
        }
        
        characteristicsView.snp.makeConstraints { make in
            make.width.lessThanOrEqualToSuperview()
            make.height.equalTo(40)
            make.center.equalToSuperview()
        }
        
        instructionsTextView.snp.makeConstraints { make in
            make.top.equalTo(instructionsLabel.snp.bottom)
            make.bottom.equalToSuperview()
            make.horizontalEdges.equalToSuperview().inset(8)
        }
    }
    
    private func addGradient() {
        let gradientLayer = CAGradientLayer()
        gradientLayer.frame = CGRect(
            x: 0,
            y: 0,
            width: cocktailImageView.bounds.width,
            height: cocktailImageView.bounds.height
        )
        gradientLayer.locations = [NSNumber(value: (2.0/3.0)), 1]
        gradientLayer.colors = [
            UIColor.clear.cgColor,
            themeProvider.theme.colors.background.cgColor
        ]
        cocktailImageView.layer.insertSublayer(gradientLayer, at: 0)
    }
    
    private func addOnBackClickBehavior() {
        backIconView.addGestureRecognizer(
            UITapGestureRecognizer(
                target: self,
                action: #selector(onBackClicked)
            )
        )
    }
    
    private func bindCharacteristics(text: String, iconName: String?) {
        characteristicsView.addArrangedSubview(
            CocktailCharacteristicChip(
                text: text,
                iconName: iconName
            )
        )
    }
    
    @objc
    private func onBackClicked() {
        self.onBackClick?()
    }
}

extension CocktailDetailsView: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.background
        backIconView.backgroundColor = theme.colors.primary.withAlphaComponent(0.3)
        cocktailTitleView.textColor = theme.colors.onBackground
        instructionsLabel.textColor = theme.colors.primary
        instructionsTextView.textColor = theme.colors.onBackground
        cocktailTitleView.setTextStyle(style: theme.typography.h1)
        instructionsLabel.setTextStyle(style: theme.typography.h3)
        instructionsTextView.setTextStyle(style: theme.typography.h4)
    }
}

private extension CocktailDetailsComponentDrinkCategory {
    var iconName: String {
        switch self {
        case .beer: return "beer"
        case .shot: return "shot"
        case .soft: return "soft_drink"
        case .cocoa: return "cocoa"
        case .shake: return "shake"
        case .punch: return "punch"
        case .coffee: return "coffee"
        case .other: return "other"
        case .liqueur: return "liqueur"
        case .cocktail: return "cocktail"
        case .ordinary: return "regular_drink"
        default: return ""
        }
    }
}
