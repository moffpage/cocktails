
import UIKit

public extension NSObject {
  static var reuseIdentifier: String {
    return NSStringFromClass(self)
  }
}

extension UICollectionView {
    func registerClassForCell(_ cellClass: UICollectionViewCell.Type) {
        register(cellClass, forCellWithReuseIdentifier: cellClass.reuseIdentifier)
    }
    
    func registerClassForHeaderView(_ viewClass: UICollectionReusableView.Type) {
        register(
            viewClass,
            forSupplementaryViewOfKind: UICollectionView.elementKindSectionHeader,
            withReuseIdentifier: viewClass.reuseIdentifier
        )
    }
    
    func dequeueReusableCell<Cell: UICollectionViewCell>(for indexPath: IndexPath) -> Cell {
      guard let cell = dequeueReusableCell(
        withReuseIdentifier: Cell.self.reuseIdentifier,
        for: indexPath) as? Cell else {
          fatalError("You are trying to dequeue \(Cell.self) which is not registered")
      }
      return cell
    }

    func dequeueHeaderView<HeaderView: UICollectionReusableView>(for indexPath: IndexPath) -> HeaderView {
        guard let view = dequeueReusableSupplementaryView(
            ofKind: UICollectionView.elementKindSectionHeader,
            withReuseIdentifier: HeaderView.reuseIdentifier,
            for: indexPath
        ) as? HeaderView else {
            fatalError("You are trying to dequeue \(HeaderView.self) which is not registered")
        }
        return view
    }
}
