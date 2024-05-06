
import shared
import UIKit
import SwiftUI

struct StackView<ChildComponent: AnyObject, Content: View>: View {
    var onBack: () -> Void
    
    @StateValue
    var stackValue: ChildStack<AnyObject, ChildComponent>
    
    @ViewBuilder
    var childContent: (ChildComponent) -> Content
    
    private var stack: [Child<AnyObject, ChildComponent>] { stackValue.items }

    var body: some View {
        /* iOS 16.0 has an issue with swipe back see
        https://stackoverflow.com/questions/73978107/incomplete-swipe-back-gesture-causes-navigationpath-mismanagement */
        if #available(iOS 16.1, *) {
            NavigationStack(
                path: Binding(
                    get: { stack.dropFirst() },
                    set: { _ in onBack() }
                )
            ) {
                childContent(stack.first!.instance!)
                    .navigationDestination(for: Child<AnyObject, ChildComponent>.self) { child in
                        childContent(child.instance!)
                    }
            }
        } else {
            StackInteropView(
                onBack: onBack,
                components: stack.map { child in child.instance! },
                childContent: childContent
            )
        }
    }
}

private struct StackInteropView<Component: AnyObject, Content: View>: UIViewControllerRepresentable {
    var onBack: () -> Void
    var components: [Component]
    var childContent: (Component) -> Content
    
    func makeCoordinator() -> Coordinator {
        Coordinator(self)
    }
    
    func makeUIViewController(context: Context) -> UINavigationController {
        context.coordinator.syncChanges(self)
        
        let navigationController = UINavigationController(
            rootViewController: context.coordinator.viewControllers.first!
        )
        
        return navigationController
    }
    
    func updateUIViewController(_ navigationController: UINavigationController, context: Context) {
        context.coordinator.syncChanges(self)
        navigationController.setViewControllers(context.coordinator.viewControllers, animated: true)
    }
    
    private func createViewController(_ component: Component, _ coordinator: Coordinator) -> NavigationItemHostingController {
        let controller = NavigationItemHostingController(rootView: childContent(component))
        controller.coordinator = coordinator
        controller.component = component
        controller.onBack = onBack
        return controller
    }
    
    class Coordinator: NSObject {
        var parent: StackInteropView<Component, Content>
        var viewControllers = [NavigationItemHostingController]()
        var preservedComponents = [Component]()
        
        init(_ parent: StackInteropView<Component, Content>) {
            self.parent = parent
        }
        
        func syncChanges(_ parent: StackInteropView<Component, Content>) {
            self.parent = parent
            
            let componentsCount = max(preservedComponents.count, parent.components.count)
            
            for component in 0 ..< componentsCount {
                if (component >= parent.components.count) {
                    viewControllers.removeLast()
                } else if (component >= preservedComponents.count) {
                    viewControllers.append(parent.createViewController(parent.components[component], self))
                } else if (parent.components[component] !== preservedComponents[component]) {
                    viewControllers[component] = parent.createViewController(parent.components[component], self)
                }
            }
            
            preservedComponents = parent.components
        }
    }
    
    class NavigationItemHostingController: UIHostingController<Content> {
        fileprivate(set) weak var coordinator: Coordinator?
        fileprivate(set) var component: Component?
        fileprivate(set) var onBack: (() -> Void)?
        
        override func viewDidAppear(_ animated: Bool) {
            super.viewDidAppear(animated)
            
            guard let components = coordinator?.preservedComponents else { return }
            guard let index = components.firstIndex(where: { $0 === component }) else { return }
            
            if (index < components.count - 1) {
                onBack?()
            }
        }
    }
}
