
import shared
import UIKit

class CocktailsListViewController: UIViewController {
    var cocktails: [CocktailsListComponentCocktailModel] = [] {
        didSet {
            UIView.performWithoutAnimation {
                collectionView.reloadSections(IndexSet(integer: 1))
            }
        }
    }
    
    private var headerView: CocktailsHeaderView?
    
    private let errorView = ErrorView(
        frame: CGRect(
            origin: .zero,
            size: CGSize(
                width: 345,
                height: 200
            )
        )
    )
    
    private let loadingView = LoadingView()
    
    private let refreshControl = UIRefreshControl()
    
    private lazy var collectionView = {
        let collectionViewLayout = UICollectionViewFlowLayout()
        collectionViewLayout.sectionHeadersPinToVisibleBounds = false
        let collectionView = UICollectionView(
            frame: .zero,
            collectionViewLayout: collectionViewLayout
        )
        collectionView.delegate = self
        collectionView.dataSource = self
        collectionView.registerClassForCell(CocktailCell.self)
        collectionView.registerClassForHeaderView(EmptyHeader.self)
        collectionView.registerClassForHeaderView(CocktailsHeaderView.self)
        collectionView.showsVerticalScrollIndicator = false
        collectionView.translatesAutoresizingMaskIntoConstraints = false
        return collectionView
    }()
    
    private let component: CocktailsListComponent
    
    init(component: CocktailsListComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        themeProvider.register(observer: self)
        setupErrorView()
        setupLoadingViews()
        setupCollectionView()
        addTapGestureListeners()
        component.model.observe { [unowned self] model in
            self.bindModel(model: model)
        }
    }
    
    private func bindModel(model: CocktailsListComponentModel) {
        cocktails = model.cocktails
        errorView.isHidden = !model.isError
        headerView?.selectedSegmentIndex = model.listsAlcoholicCocktails ? 1 : 0
        loadingView.isAnimating = model.isLoading
        collectionView.isHidden = model.isError || (model.isLoading && cocktails.isEmpty)
        refreshControl.setRefreshing(model.isRefreshing)
    }
    
    private func setupErrorView() {
        view.addSubview(errorView)
        errorView.center = view.center
        errorView.onRetry = { [unowned self] in self.component.reload() }
    }
    
    private func setupLoadingViews() {
        view.addSubview(loadingView)
        loadingView.layer.zPosition = 1
        refreshControl.addTarget(self, action: #selector(refresh), for: .valueChanged)
        collectionView.refreshControl = refreshControl
    }
    
    private func setupCollectionView() {
        view.addSubview(collectionView)
        collectionView.topAnchor.constraint(equalTo: view.topAnchor).isActive = true
        collectionView.bottomAnchor.constraint(equalTo: view.bottomAnchor).isActive = true
        collectionView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 8).isActive = true
        collectionView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -8).isActive = true
    }
    
    @objc
    private func refresh() {
        component.reload()
    }
    
    @objc
    private func dismissKeyboard() {
        view.endEditing(true)
    }
    
    private func addTapGestureListeners() {
        let singleTapGestureRecognizer = UITapGestureRecognizer(target: self, action: #selector(dismissKeyboard))
        singleTapGestureRecognizer.cancelsTouchesInView = false
        collectionView.addGestureRecognizer(singleTapGestureRecognizer)
    }
}

extension CocktailsListViewController: Themeable {
    func apply(theme: any Theme) {
        view.backgroundColor = theme.colors.background
        collectionView.backgroundColor = theme.colors.background
    }
}

extension CocktailsListViewController: UICollectionViewDelegate {
    func collectionView(
        _ collectionView: UICollectionView,
        didSelectItemAt indexPath: IndexPath
    ) {
        let cocktail = cocktails[indexPath.row]
        component.showCocktail(cocktailId: cocktail.id)
    }
}

extension CocktailsListViewController: UICollectionViewDelegateFlowLayout {
    func collectionView(
        _ collectionView: UICollectionView,
        layout collectionViewLayout: UICollectionViewLayout,
        sizeForItemAt indexPath: IndexPath
    ) -> CGSize {
        let itemWidth = (collectionView.frame.width - 8) / 2.0
        return CGSize(width: itemWidth, height: itemWidth)
    }
    
    func collectionView(
        _ collectionView: UICollectionView,
        layout collectionViewLayout: UICollectionViewLayout,
        minimumLineSpacingForSectionAt section: Int
    ) -> CGFloat { return 8 }
    
    func collectionView(
        _ collectionView: UICollectionView,
        layout collectionViewLayout: UICollectionViewLayout,
        minimumInteritemSpacingForSectionAt section: Int
    ) -> CGFloat { return 8 }
    
    func collectionView(
        _ collectionView: UICollectionView,
        viewForSupplementaryElementOfKind kind: String,
        at indexPath: IndexPath
    ) -> UICollectionReusableView {
        if (kind == UICollectionView.elementKindSectionHeader) {
            if (indexPath.section == 0) {
                let headerView: CocktailsHeaderView = collectionView.dequeueHeaderView(for: indexPath)
                headerView.onSearchQueryCleared = { [unowned self] in
                    self.component.clearSearch()
                }
                headerView.onSearchQueryChanged = { [unowned self] searchQuery in
                    self.component.findCocktail(searchQuery: searchQuery)
                }
                headerView.onFirstSegmentClicked = { [unowned self] in
                    self.component.displayNonAlcoholicCocktails()
                }
                headerView.onSecondSegmentClicked = { [unowned self] in
                    self.component.displayAlcoholicCocktails()
                }
                self.headerView = headerView
                return headerView
            } else {
                let headerView: EmptyHeader = collectionView.dequeueHeaderView(for: indexPath)
                return headerView
            }
        } else {
            fatalError("This should not be called")
        }
    }
    
    func collectionView(
        _ collectionView: UICollectionView,
        layout collectionViewLayout: UICollectionViewLayout,
        referenceSizeForHeaderInSection section: Int) -> CGSize
    {
        if (section == 0) {
            return CGSize(
                width: collectionView.bounds.size.width,
                height: 160
            )
        } else {
            return .zero
        }
    }
}

extension CocktailsListViewController: UICollectionViewDataSource {
    func collectionView(
        _ collectionView: UICollectionView,
        numberOfItemsInSection section: Int
    ) -> Int { return section == 1 ? cocktails.count : 0 }
    
    func collectionView(
        _ collectionView: UICollectionView,
        cellForItemAt indexPath: IndexPath
    ) -> UICollectionViewCell {
        let cocktail = cocktails[indexPath.row]
        let cell: CocktailCell = collectionView.dequeueReusableCell(for: indexPath)
        cell.bind(cocktail: cocktail)
        return cell
    }
    
    func numberOfSections(in collectionView: UICollectionView) -> Int { return 2 }
}

private class EmptyHeader : UICollectionReusableView { }
