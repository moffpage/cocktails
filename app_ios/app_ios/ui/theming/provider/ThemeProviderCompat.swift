
import Foundation

final class ThemeProviderCompat: ThemeProvider {
    private var observers: NSHashTable<AnyObject> = NSHashTable.weakObjects()
    
    static let shared = ThemeProviderCompat()
    
    lazy var theme: any Theme = AppTheme.light(
        shapes: ThemeProviderCompat.shared.theme.shapes,
        typography: ThemeProviderCompat.shared.theme.typography
    ) {
        didSet {
            notifyObservers()
        }
    }
    
    private init() {
    }
    
    func setTheme(_ theme: any Theme) {
        self.theme = theme
    }
    
    func register<Observer>(observer: Observer) where Observer: Themeable {
        observer.apply(theme: theme)
        self.observers.add(observer)
    }
    
    private func notifyObservers() {
        DispatchQueue.main.async {
            self.observers.allObjects
                .compactMap { observer in observer as? Themeable }
                .forEach { observer in observer.apply(theme: self.theme) }
        }
    }
}
