
import shared
import UIKit
import SnapKit

final class CocktailsListViewController: UIViewController, ViewHolder {
    typealias RootViewType = CocktailsListView
    
    var cocktails: [CocktailsListComponentCocktailModel] = [] {
        didSet {
            self.rootView.reloadList(at: 1)
        }
    }
    
    private let component: CocktailsListComponent
    
    init(component: CocktailsListComponent) {
        self.component = component
        super.init(nibName: nil, bundle: nil)
    }
    
    required init?(coder: NSCoder) {
        fatalError("init(coder:) has not been implemented")
    }
    
    override func loadView() {
        view = CocktailsListView(
            onRefresh: { [unowned self] in
                self.component.reload()
            },
            onRefetch: { [unowned self] in
                self.component.refetchCocktails()
            }
        )
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        bindView()
        component.model.observe { [unowned self] model in
            self.cocktails = model.cocktails
            self.rootView.bind(model: model)
        }
    }
    
    private func bindView() {
        rootView.collectionView.delegate = self
        rootView.collectionView.dataSource = self
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
                headerView.onSegmentClicked = { [unowned self] index in
                    if index == 0 {
                        self.component.displayNonAlcoholicCocktails()
                    } else {
                        self.component.displayAlcoholicCocktails()
                    }
                }
                headerView.onSearchQueryCleared = { [unowned self] in
                    self.component.clearSearch()
                }
                headerView.onSearchQueryChanged = { [unowned self] searchQuery in
                    self.component.findCocktail(searchQuery: searchQuery)
                }
                return headerView
            } else {
                return UICollectionReusableView()
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
