
import SwiftUI

struct PullRefreshView: UIViewRepresentable {
    private let action: () -> Void
    private let isRefreshing: Bool
    
    init(
        action: @escaping () -> Void,
        isShowing: Bool
    ) {
        self.action = action
        self.isRefreshing = isShowing
    }
    
    class Coordinator {
        private let action: () -> Void
        
        init(action: @escaping () -> Void) {
            self.action = action
        }
        
        @objc
        func onValueChanged() {
            action()
        }
    }
    
    func makeUIView(context: UIViewRepresentableContext<PullRefreshView>) -> UIView {
        return UIView()
    }
    
    func updateUIView(_ uiView: UIView, context: UIViewRepresentableContext<PullRefreshView>) {
        DispatchQueue.main.async {
            guard let viewHost = uiView.superview?.superview,
                  let scrollView = scrollView(root: viewHost) else { return }
            
            if let refreshControl = scrollView.refreshControl {
                if isRefreshing {
                    refreshControl.beginRefreshing()
                } else {
                    refreshControl.endRefreshing()
                }
                return
            }
            
            let refreshControl = UIRefreshControl()
            refreshControl.addTarget(
                context.coordinator,
                action: #selector(Coordinator.onValueChanged),
                for: .valueChanged
            )
            scrollView.refreshControl = refreshControl
        }
    }
    
    func makeCoordinator() -> Coordinator {
        return Coordinator(action: action)
    }
    
    private func scrollView(root: UIView) -> UIScrollView? {
        for subview in root.subviews {
            if let scrollView = subview as? UIScrollView {
                return scrollView
            } else if let scrollView = scrollView(root: subview) {
                return scrollView
            }
        }
        return nil
    }
}
