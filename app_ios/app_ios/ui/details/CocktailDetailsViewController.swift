
import shared
import UIKit

class CocktailDetailsViewController: UIViewController {
    private let closeIconView = {
        let closeImageView = UIImageView(
            frame: CGRect(
                x: 8,
                y: 8,
                width: 24,
                height: 24
            )
        )
        closeImageView.image = UIImage(imageLiteralResourceName: "back")
        closeImageView.tintColor = .white
        
        let surfaceView = UIView()
        surfaceView.clip(to: RoundedCornerShape(cornerRadius: 20.0))
        surfaceView.translatesAutoresizingMaskIntoConstraints = false
        surfaceView.addSubview(closeImageView)
        
        return surfaceView
    }()
    
    private let cocktailImageView = {
        let imageView = UIImageView()
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    private let cocktailTitleView = {
        let label = UILabel()
        label.textAlignment = .center
        label.backgroundColor = .clear
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let instructionsLabel = {
        let label = UILabel()
        label.text = CoreStrings.shared.instructions.desc().localized()
        label.backgroundColor = .clear
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let instructionsTextView = {
        let textView = UITextView()
        textView.backgroundColor = .clear
        textView.translatesAutoresizingMaskIntoConstraints = false
        return textView
    }()
    
    private let characteristicsView = {
        let stackView = UIStackView()
        stackView.axis = .horizontal
        stackView.spacing = 16
        stackView.translatesAutoresizingMaskIntoConstraints = false
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
        dismiss(animated: true) {
            self.component.navigateBack()
        }
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
        cocktailImageView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        cocktailImageView.leadingAnchor.constraint(equalTo: view.leadingAnchor).isActive = true
        cocktailImageView.trailingAnchor.constraint(equalTo: view.trailingAnchor).isActive = true
        cocktailImageView.heightAnchor.constraint(
            equalTo: cocktailImageView.widthAnchor,
            multiplier: 1
        ).isActive = true
        cocktailImageView.kf.setImage(with: URL(string: cocktailImageUrl))
        addGradient()
    }
    
    private func bindTitleView(cocktailName: String) {
        cocktailImageView.addSubview(cocktailTitleView)
        cocktailTitleView.text = cocktailName
        cocktailTitleView.bottomAnchor.constraint(
            equalTo: cocktailImageView.bottomAnchor,
            constant: -16
        ).isActive = true
        cocktailTitleView.heightAnchor.constraint(equalToConstant: 34).isActive = true
        cocktailTitleView.leadingAnchor.constraint(equalTo: cocktailImageView.leadingAnchor).isActive = true
        cocktailTitleView.trailingAnchor.constraint(equalTo: cocktailImageView.trailingAnchor).isActive = true
    }
    
    private func bindInstructions(text: String) {
        addInstructionsLabel()
        view.addSubview(instructionsTextView)
        instructionsTextView.text = text
        instructionsTextView.topAnchor.constraint(
            equalTo: instructionsLabel.bottomAnchor
        ).isActive = true
        instructionsTextView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        instructionsTextView.leadingAnchor.constraint(
            equalTo: view.leadingAnchor,
            constant: 8
        ).isActive = true
        instructionsTextView.trailingAnchor.constraint(
            equalTo: view.trailingAnchor,
            constant: -8
        ).isActive = true
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
        view.addSubview(closeIconView)
        let tapGestureRecognizer = UITapGestureRecognizer(
            target: self,
            action: #selector(navigateBack)
        )
        closeIconView.addGestureRecognizer(tapGestureRecognizer)
        closeIconView.topAnchor.constraint(
            equalTo: view.safeAreaLayoutGuide.topAnchor,
            constant: 8
        ).isActive = true
        closeIconView.leadingAnchor.constraint(
            equalTo: view.leadingAnchor,
            constant: 16
        ).isActive = true
        closeIconView.widthAnchor.constraint(equalToConstant: 40).isActive = true
        closeIconView.heightAnchor.constraint(equalToConstant: 40).isActive = true
    }
    
    private func addInstructionsLabel() {
        view.addSubview(instructionsLabel)
        instructionsLabel.topAnchor.constraint(equalTo: characteristicsView.bottomAnchor, constant: 36).isActive = true
        instructionsLabel.heightAnchor.constraint(equalToConstant: 24).isActive = true
        instructionsLabel.leadingAnchor.constraint(
            equalTo: view.leadingAnchor,
            constant: 8
        ).isActive = true
        instructionsLabel.widthAnchor.constraint(equalToConstant: 140).isActive = true
    }
    
    private func addCharacteristicsView() {
        view.addSubview(characteristicsView)
        characteristicsView.widthAnchor.constraint(lessThanOrEqualTo: view.widthAnchor).isActive = true
        characteristicsView.heightAnchor.constraint(equalToConstant: 40).isActive = true
        characteristicsView.centerYAnchor.constraint(equalTo: view.centerYAnchor).isActive = true
        characteristicsView.centerXAnchor.constraint(equalTo: view.centerXAnchor).isActive = true
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
        closeIconView.backgroundColor = theme.colors.primary.withAlphaComponent(0.3)
        cocktailTitleView.textColor = theme.colors.onBackground
        instructionsLabel.textColor = theme.colors.primary
        instructionsTextView.textColor = theme.colors.onBackground
        cocktailTitleView.setTextStyle(style: theme.typography.h1)
        instructionsLabel.setTextStyle(style: theme.typography.h3)
        instructionsTextView.setTextStyle(style: theme.typography.h4)
    }
}
