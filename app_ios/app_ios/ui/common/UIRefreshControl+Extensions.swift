
import UIKit

extension UIRefreshControl {
    func setRefreshing(_ isRefreshing: Bool) {
        if isRefreshing {
            self.beginRefreshing()
        } else {
            self.endRefreshing()
        }
    }
}
