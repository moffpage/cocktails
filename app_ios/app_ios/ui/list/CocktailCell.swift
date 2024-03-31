
import shared
import UIKit
import Kingfisher

class CocktailCell: UICollectionViewCell {
    private let imageView: UIImageView = {
        let imageView = UIImageView()
        imageView.contentMode = .scaleAspectFit
        imageView.translatesAutoresizingMaskIntoConstraints = false
        return imageView
    }()
    
    private let titleLabel: UILabel = {
        let label = UILabel()
        label.numberOfLines = 0
        label.lineBreakMode = .byWordWrapping
        label.textAlignment = .center
        label.translatesAutoresizingMaskIntoConstraints = false
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
        imageView.kf.setImage(with: URL(string: cocktail.imageUrl))
        titleLabel.text = cocktail.name
    }
    
    private func setupTitleLabel() {
        contentView.addSubview(titleLabel)
        titleLabel.bottomAnchor.constraint(equalTo: imageView.bottomAnchor).isActive = true
        titleLabel.centerYAnchor.constraint(equalTo: imageView.centerYAnchor).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: imageView.leadingAnchor).isActive = true
        titleLabel.trailingAnchor.constraint(equalTo: imageView.trailingAnchor).isActive = true
    }
    
    private func setupImageView() {
        contentView.addSubview(imageView)
        imageView.topAnchor.constraint(equalTo: contentView.topAnchor).isActive = true
        imageView.bottomAnchor.constraint(equalTo: contentView.bottomAnchor).isActive = true
        imageView.leadingAnchor.constraint(equalTo: contentView.leadingAnchor).isActive = true
        imageView.trailingAnchor.constraint(equalTo: contentView.trailingAnchor).isActive = true
    }
}

extension CocktailCell: Themeable {
    func apply(theme: any Theme) {
        clip(to: theme.shapes.large)
        titleLabel.textColor = theme.colors.onPrimary
        titleLabel.setTextStyle(style: theme.typography.h4)
    }
}
