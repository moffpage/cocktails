
import shared
import UIKit
import SnapKit
import Kingfisher

final class CocktailCell: UICollectionViewCell {
    private let imageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        return imageView
    }()
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.textAlignment = .center
        return label
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        setupImageView()
        setupTitleLabel()
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func prepareForReuse() {
        super.prepareForReuse()
        imageView.image = nil
        imageView.kf.cancelDownloadTask()
        titleLabel.text = nil
    }
    
    func bind(cocktail: CocktailsListComponentCocktailModel) {
        imageView.kf.setImage(
            with: URL(string: cocktail.imageUrl),
            placeholder: themeProvider.theme.mode == .light ?
                UiComponentImages.shared.cocktailPlaceholderLight.toUIImage() :
                UiComponentImages.shared.cocktailPlaceholderDark.toUIImage()
        )
        titleLabel.text = cocktail.name
    }
    
    private func setupTitleLabel() {
        imageView.addSubview(titleLabel)
        titleLabel.snp.makeConstraints { make in
            make.center.equalToSuperview()
            make.bottom.equalToSuperview()
        }
    }
    
    private func setupImageView() {
        contentView.addSubview(imageView)
        imageView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
    }
}

extension CocktailCell: Themeable {
    func apply(theme: any Theme) {
        clip(to: theme.shapes.large)
        titleLabel.textColor = theme.colors.onPrimary
        titleLabel.setTextStyle(style: theme.typography.h4)
    }
}
