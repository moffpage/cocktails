//
//  CocktailsHeaderView.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 10.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

class CocktailsHeaderView: UICollectionReusableView {    
    var selectedSegmentIndex: Int = 0 {
        didSet {
            segmentedControl.selectedSegmentIndex = selectedSegmentIndex
        }
    }
    
    var onSearchQueryCleared: (() -> Void)?
    var onSearchQueryChanged: ((String) -> Void)?
    var onFirstSegmentClicked: (() -> Void)?
    var onSecondSegmentClicked: (() -> Void)?
    
    private let titleLabel = {
        let label = UILabel()
        label.text = Strings.shared.cocktails.desc().localized()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let searchBar = {
        let searchBar = CustomTextField()

        let searchIcon = UIImage(imageLiteralResourceName: "magnifyingglass")
        let searchIconView = UIImageView(image: searchIcon)
        searchIconView.frame = CGRect(x: 8, y: 0, width: 16, height: 16)
        
        let clearIcon = UIImage(imageLiteralResourceName: "close")
        let clearIconView = UIImageView(image: clearIcon)
        clearIconView.isUserInteractionEnabled = true
        clearIconView.frame = CGRect(x: 0, y: 0, width: 16, height: 16)
        
        searchBar.leftView = searchIconView
        searchBar.rightView = clearIconView
        searchBar.leftViewMode = .always
        searchBar.translatesAutoresizingMaskIntoConstraints = false
        return searchBar
    }()
    
    private let segmentedControl = {
        let control = CustomSegmentControl(
            items: [
                Strings.shared.nonAlcoholic.desc().localized(),
                Strings.shared.alcoholic.desc().localized(),
            ]
        )

        control.translatesAutoresizingMaskIntoConstraints = false
        control.layer.borderWidth = 2
        return control
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
        addTitle()
        addSearch()
        addFilterSegments()
    }
    
    private func addTitle() {
        addSubview(titleLabel)
        titleLabel.topAnchor.constraint(equalTo: topAnchor).isActive = true
        titleLabel.heightAnchor.constraint(equalToConstant: 40).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        titleLabel.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
    }
    
    private func addSearch() {
        addSubview(searchBar)
        let clearAction = UITapGestureRecognizer(target: self, action: #selector(searchQueryCleared))
        searchBar.attributedPlaceholder = NSAttributedString(
            string: Strings.shared.search.desc().localized(),
            attributes: [NSAttributedString.Key.foregroundColor: themeProvider.theme.colors.onSurface]
        )
        searchBar.rightView?.addGestureRecognizer(clearAction)
        searchBar.addTarget(self, action: #selector(searchQueryDidChange(textField:)), for: .editingChanged)
        searchBar.topAnchor.constraint(
            equalTo: titleLabel.bottomAnchor,
            constant: 20
        ).isActive = true
        searchBar.heightAnchor.constraint(equalToConstant: 36).isActive = true
        searchBar.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        searchBar.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
    }
    
    private func addFilterSegments() {
        addSubview(segmentedControl)
        segmentedControl.addTarget(self, action: #selector(segmentTapped(_:)), for: .valueChanged)
        segmentedControl.selectedSegmentIndex = selectedSegmentIndex
        segmentedControl.topAnchor.constraint(
            equalTo: searchBar.bottomAnchor,
            constant: 16
        ).isActive = true
        segmentedControl.heightAnchor.constraint(equalToConstant: 36).isActive = true
        segmentedControl.leadingAnchor.constraint(equalTo: leadingAnchor).isActive = true
        segmentedControl.trailingAnchor.constraint(equalTo: trailingAnchor).isActive = true
    }
    
    @objc
    private func segmentTapped(_ control: UISegmentedControl) {
        switch (segmentedControl.selectedSegmentIndex) {
        case 0:
            onFirstSegmentClicked?()
        case 1:
            onSecondSegmentClicked?()
        default: break
        }
    }
    
    @objc
    private func searchQueryCleared() {
        if (searchBar.text?.isEmpty == true) { return }
        onSearchQueryCleared?()
        searchBar.rightViewMode = .never
        searchBar.text = ""
    }
    
    @objc
    private func searchQueryDidChange(textField: UITextField) {
        searchBar.rightViewMode = textField.text?.isEmpty == true ? .never : .always
        onSearchQueryChanged?(textField.text ?? "")
    }
}

extension CocktailsHeaderView: Themeable {
    func apply(theme: any Theme) {
        searchBar.apply(theme: theme)
        segmentedControl.apply(theme: theme)
        titleLabel.textColor = theme.colors.onBackground
        titleLabel.setTextStyle(style: theme.typography.h1)
    }
}
