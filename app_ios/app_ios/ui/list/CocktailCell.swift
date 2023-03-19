//
//  CocktailCell.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
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
    
    override func layoutSubviews() {
        super.layoutSubviews()
        setupImageView()
        setupTitleLabel()
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
