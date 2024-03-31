
import shared
import UIKit
import SnapKit

class CocktailDetailsViewController: UIViewController {
    private let backIconView = {
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
    
    private let cocktailTitleView = {
        let label = UILabel()
        label.textAlignment = .center
        return label
    }()
    
    private let instructionsLabel = {
        let label = UILabel()
        label.text = CoreStrings.shared.instructions.desc().localized()
        label.backgroundColor = .clear
        return label
    }()
    
    private let instructionsTextView = {
        let textView = UITextView()
        textView.backgroundColor = .clear
        return textView
    }()
    
    private let characteristicsView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 16
        return stackView
    }()
    
    private let component: CocktailDetailsComponent
    
    init(component: CocktailDetailsComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        themeProvider.register(observer: self)
        component.model.observe { [unowned self] model in
            self.bindView(with: model)
        }
    }
    
    @objc
    private func navigateBack() {
        self.component.navigateBack()
    }
    
    private func bindView(with model: CocktailDetailsComponentModel) {
        bindImageView(cocktailImageUrl: model.imageUrl)
        bindTitleView(cocktailName: model.cocktailName)
        
        addDismissButton()
        addCharacteristicsView()
        
        let alcoholicSign: String
        if model.isAlcoholic {
            alcoholicSign = CoreStrings.shared.alcoholic.desc().localized()
        } else {
            alcoholicSign = CoreStrings.shared.nonAlcoholic.desc().localized()
        }
        
        if let category = model.category {
            bindCharacteristics(
                text: model.glassType,
                iconName: category.iconName
            )
            bindCharacteristics(
                text: alcoholicSign,
                iconName: nil
            )
        }

        bindInstructions(text: model.preparationInstruction)
    }
    
    private func bindImageView(cocktailImageUrl: String) {
        view.addSubview(cocktailImageView)
        cocktailImageView.snp.makeConstraints { make in
            make.top.equalToSuperview()
            make.width.equalToSuperview()
            make.height.equalTo(cocktailImageView.snp.width)
        }
        cocktailImageView.kf.setImage(with: URL(string: cocktailImageUrl))
        addGradient()
    }
    
    private func bindTitleView(cocktailName: String) {
        cocktailImageView.addSubview(cocktailTitleView)
        cocktailTitleView.text = cocktailName
        cocktailTitleView.snp.makeConstraints { make in
            make.bottom.equalTo(cocktailImageView.snp.bottom).inset(16)
            make.horizontalEdges.equalToSuperview()
        }
    }
    
    private func bindInstructions(text: String) {
        addInstructionsLabel()
        view.addSubview(instructionsTextView)
        instructionsTextView.text = text
        instructionsTextView.snp.makeConstraints { make in
            make.top.equalTo(instructionsLabel.snp.bottom)
            make.bottom.equalToSuperview()
            make.horizontalEdges.equalToSuperview().inset(8)
        }
    }
    
    private func bindCharacteristics(text: String, iconName: String?) {
        let chip = CocktailCharacteristicChip(text: text, iconName: iconName)
        characteristicsView.addArrangedSubview(chip)
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
    
    private func addDismissButton() {
        view.addSubview(backIconView)
        let tapGestureRecognizer = UITapGestureRecognizer(
            target: self,
            action: #selector(navigateBack)
        )
        backIconView.addGestureRecognizer(tapGestureRecognizer)
        backIconView.snp.makeConstraints { make in
            make.top.equalTo(view.safeAreaLayoutGuide.snp.top).inset(8)
            make.size.equalTo(40)
            make.leading.equalToSuperview().inset(16)
        }
    }
    
    private func addInstructionsLabel() {
        view.addSubview(instructionsLabel)
        instructionsLabel.snp.makeConstraints { make in
            make.top.equalTo(characteristicsView.snp.bottom).offset(36)
            make.width.equalTo(140)
            make.height.equalTo(24)
            make.leading.equalToSuperview().inset(16)
        }
    }
    
    private func addCharacteristicsView() {
        view.addSubview(characteristicsView)
        characteristicsView.snp.makeConstraints { make in
            make.width.lessThanOrEqualToSuperview()
            make.height.equalTo(40)
            make.center.equalToSuperview()
        }
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

extension CocktailDetailsViewController: Themeable {
    func apply(theme: any Theme) {
        view.backgroundColor = theme.colors.background
        backIconView.backgroundColor = theme.colors.primary.withAlphaComponent(0.3)
        cocktailTitleView.textColor = theme.colors.onBackground
        instructionsLabel.textColor = theme.colors.primary
        instructionsTextView.textColor = theme.colors.onBackground
        cocktailTitleView.setTextStyle(style: theme.typography.h1)
        instructionsLabel.setTextStyle(style: theme.typography.h3)
        instructionsTextView.setTextStyle(style: theme.typography.h4)
    }
}
