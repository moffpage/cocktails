
import shared
import UIKit
import SnapKit

final class CocktailsListView: UIView {
    lazy var collectionView: UICollectionView = {
        let collectionViewLayout = UICollectionViewFlowLayout()
        collectionViewLayout.sectionHeadersPinToVisibleBounds = false
        let collectionView = UICollectionView(
            frame: .zero,
            collectionViewLayout: collectionViewLayout
        )
        collectionView.registerClassForCell(CocktailCell.self)
        collectionView.registerClassForHeaderView(CocktailsHeaderView.self)
        collectionView.showsVerticalScrollIndicator = false
        return collectionView
    }()
    
    private let errorView = ErrorView()
    
    private let loadingView: LoadingView = {
        let view = LoadingView()
        view.layer.zPosition = 1
        return view
    }()
    
    private let onRefresh: (() -> Void)?
    private let onRefetch: (() -> Void)?
    
    init(
        onRefresh: @escaping () -> Void,
        onRefetch: @escaping () -> Void
    ) {
        self.onRefresh = onRefresh
        self.onRefetch = onRefetch
        super.init(frame: .zero)
        addSubviews()
        constrainSubviews()
        addOnRefreshBehavior()
        addOnRefetchBehavior()
        addKeyboardDismissOnOutsideClick()
        themeProvider.register(observer: self)
    }
    
    override init(frame: CGRect) {
        fatalError("init(onRefresh: () -> Void, onRefetch: () -> Void) must be used")
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    func bind(model: CocktailsListComponentModel) {
        errorView.isHidden = !model.isError
        loadingView.isAnimating = model.isLoading
        collectionView.isHidden = model.isError || (model.isLoading && model.cocktails.isEmpty)
        collectionView.refreshControl?.setRefreshing(model.isRefreshing)
    }
    
    func reloadList(at section: Int) {
        UIView.performWithoutAnimation {
            collectionView.reloadSections(IndexSet(integer: section))
        }
    }
    
    private func addSubviews() {
        addSubview(errorView)
        addSubview(loadingView)
        addSubview(collectionView)
    }
    
    private func constrainSubviews() {
        errorView.snp.makeConstraints { make in
            make.width.equalTo(345)
            make.height.equalTo(200)
            make.center.equalToSuperview()
        }
        
        loadingView.snp.makeConstraints { make in
            make.edges.equalToSuperview()
        }
        
        collectionView.snp.makeConstraints { make in
            make.verticalEdges.equalToSuperview()
            make.horizontalEdges.equalToSuperview().inset(8)
        }
    }
    
    private func addOnRefreshBehavior() {
        let refreshControl = UIRefreshControl()
        refreshControl.addTarget(self, action: #selector(onRefreshed), for: .valueChanged)
        collectionView.refreshControl = refreshControl
    }
    
    private func addOnRefetchBehavior() {
        errorView.onRetry = { [unowned self] in self.onRefetch?() }
    }
    
    private func addKeyboardDismissOnOutsideClick() {
        let singleTapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        singleTapGestureRecognizer.cancelsTouchesInView = false
        collectionView.addGestureRecognizer(singleTapGestureRecognizer)
    }
    
    @objc
    private func onRefreshed() {
        self.onRefresh?()
    }
    
    @objc
    private func dismissKeyboard() {
        endEditing(true)
    }
}

extension CocktailsListView: Themeable {
    func apply(theme: any Theme) {
        backgroundColor = theme.colors.background
        collectionView.backgroundColor = theme.colors.background
    }
}
