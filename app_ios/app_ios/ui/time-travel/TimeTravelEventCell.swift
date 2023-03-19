//
//  TimeTravelEventCell.swift
//  Vlife Task
//
//  Created by Artur Mavlyuchenko on 19.03.2023.
//  Copyright © 2023 orgName. All rights reserved.
//

import common
import UIKit

internal class TimeTravelEventCell: UITableViewCell {
    private let titleLabel = {
        let label = UILabel()
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    private let debugButton = {
        let button = UIButton()
        button.setImage(UIImage(imageLiteralResourceName: "play") , for: .normal)
        button.translatesAutoresizingMaskIntoConstraints = false
        return button
    }()
    
    private let descriptionLabel = {
        let label = UILabel()
        label.numberOfLines = 3
        label.translatesAutoresizingMaskIntoConstraints = false
        return label
    }()
    
    var onDebugClick: ((Int64) -> Void)?
    
    private var event: TimeTravelEvent?
    
    override init(style: UITableViewCell.CellStyle, reuseIdentifier: String?) {
        super.init(style: style, reuseIdentifier: reuseIdentifier)
        themeProvider.register(observer: self)
        separatorInset = .zero
        addDebugButton()
        addTitle()
        addDescription()
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func bind(with model: TimeTravelEvent, selected: Bool) {
        self.event = model
        contentView.backgroundColor = selected ? themeProvider.theme.colors.primary : themeProvider.theme.colors.surface
        titleLabel.text = model.storeName
        debugButton.isHidden = model.type == .state
        debugButton.addTarget(self, action: #selector(onDebugButtonClick), for: .touchUpInside)
        descriptionLabel.text = String((model.value as AnyObject).description?.prefix(200) ?? "")
    }
    
    private func addTitle() {
        contentView.addSubview(titleLabel)
        titleLabel.topAnchor.constraint(equalTo: contentView.topAnchor, constant: 8).isActive = true
        titleLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 8).isActive = true
        titleLabel.trailingAnchor.constraint(equalTo: debugButton.leadingAnchor).isActive = true
    }
    
    private func addDebugButton() {
        contentView.addSubview(debugButton)
        debugButton.topAnchor.constraint(equalTo: contentView.topAnchor).isActive = true
        debugButton.widthAnchor.constraint(equalToConstant: 32).isActive = true
        debugButton.heightAnchor.constraint(equalToConstant: 32).isActive = true
        debugButton.trailingAnchor.constraint(equalTo: contentView.trailingAnchor).isActive = true
    }
    
    private func addDescription() {
        contentView.addSubview(descriptionLabel)
        descriptionLabel.topAnchor.constraint(equalTo: titleLabel.bottomAnchor, constant: 4).isActive = true
        descriptionLabel.bottomAnchor.constraint(equalTo: contentView.bottomAnchor, constant: -8).isActive = true
        descriptionLabel.leadingAnchor.constraint(equalTo: contentView.leadingAnchor, constant: 8).isActive = true
        descriptionLabel.trailingAnchor.constraint(equalTo: contentView.trailingAnchor, constant: -8).isActive = true
    }
    
    @objc
    private func onDebugButtonClick() {
        if let event = self.event {
            onDebugClick?(event.id)
        }
    }
}

extension TimeTravelEventCell: Themeable {
    func apply(theme: any Theme) {
        contentView.backgroundColor = theme.colors.surface
        titleLabel.setTextStyle(style: theme.typography.h4)
        descriptionLabel.setTextStyle(style: theme.typography.body1)
    }
}
