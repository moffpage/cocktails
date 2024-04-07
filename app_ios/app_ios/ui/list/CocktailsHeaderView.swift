
import shared
import UIKit
import SnapKit

final class CocktailsHeaderView: UICollectionReusableView {    
    var selectedSegmentIndex: Int = 0 {
        didSet {
            segmentedControl.selectedSegmentIndex = selectedSegmentIndex
        }
    }
    
    var onSegmentClicked: ((Int) -> Void)?
    var onSearchQueryCleared: (() -> Void)?
    var onSearchQueryChanged: ((String) -> Void)?
    
    private let titleLabel = UILabel(text: CommonStrings.shared.cocktails)
    
    private let searchBar: TextField = {
        let searchBar = TextField()

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
        return searchBar
    }()
    
    private lazy var segmentedControl: SegmentControl = {
        let textStyle = themeProvider.theme.typography.h4
        let control = SegmentControl(
            items: [
                CommonStrings.shared.nonAlcoholic.desc().localized().image(textStyle: textStyle),
                CommonStrings.shared.alcoholic.desc().localized().image(textStyle: textStyle),
            ]
        )
        control.layer.borderWidth = 2
        return control
    }()
    
    override init(frame: CGRect) {
        super.init(frame: frame)
        addTitle()
        addSearch()
        addFilterSegments()
        themeProvider.register(observer: self)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    private func addTitle() {
        addSubview(titleLabel)
        titleLabel.snp.makeConstraints { make in
            make.top.equalToSuperview()
            make.height.equalTo(40)
            make.horizontalEdges.equalToSuperview()
        }
    }
    
    private func addSearch() {
        addSubview(searchBar)
        let clearAction = UITapGestureRecognizer(target: self, action: #selector(searchQueryCleared))
        searchBar.attributedPlaceholder = NSAttributedString(
            string: UiComponentsStrings.shared.search.desc().localized(),
            attributes: [NSAttributedString.Key.foregroundColor: themeProvider.theme.colors.onSurface]
        )
        searchBar.rightView?.addGestureRecognizer(clearAction)
        searchBar.addTarget(self, action: #selector(searchQueryDidChange(textField:)), for: .editingChanged)
        searchBar.snp.makeConstraints { make in
            make.top.equalTo(titleLabel.snp.bottom).offset(20)
            make.height.equalTo(36)
            make.horizontalEdges.equalToSuperview()
        }
    }
    
    private func addFilterSegments() {
        addSubview(segmentedControl)
        segmentedControl.addTarget(self, action: #selector(segmentTapped(_:)), for: .valueChanged)
        segmentedControl.selectedSegmentIndex = selectedSegmentIndex
        segmentedControl.snp.makeConstraints { make in
            make.top.equalTo(searchBar.snp.bottom).offset(16)
            make.height.equalTo(36)
            make.horizontalEdges.equalToSuperview()
        }
    }
    
    @objc
    private func segmentTapped(_ control: UISegmentedControl) {
        onSegmentClicked?(control.selectedSegmentIndex)
    }
    
    @objc
    private func searchQueryCleared() {
        if searchBar.text?.isEmpty == true { return }
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
        titleLabel.textColor = theme.colors.onBackground
        titleLabel.setTextStyle(style: theme.typography.h1)
    }
}
